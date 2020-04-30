package com.alevel.java.nix.mobileoperator;

import com.alevel.java.nix.mobileoperator.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Month;

public class MockDataLoader {

    private static final Logger log = LoggerFactory.getLogger(MockDataLoader.class);

    public static void main(String[] args) {

        Timestamps now = Timestamps.now();

        Tariff standard = new Tariff();
        standard.setName("Standard");
        standard.setDescription("Pre-paid monthly subscription");
        standard.setPricePerMonth(9000L);
        standard.setTimestamps(now);


        Client njrobinson = new Client();
        njrobinson.setUsername(new Username("Nickolas J. Robinson", "njrobinson"));
        njrobinson.setTimestamps(now);
        njrobinson.setBirthday(LocalDate.of(1990, Month.APRIL, 30));

        Account njPersonal = new Account();

        njPersonal.setPhoneNumber("+10993841389");
        njPersonal.setTariff(standard);
        njPersonal.setTimestamps(now);
        standard.getAccounts().add(njPersonal);
        njrobinson.addAccount(njPersonal);

        Account njBusiness = new Account();
        njBusiness.setPhoneNumber("+10993841388");
        njBusiness.setTariff(standard);
        njBusiness.setTimestamps(now);
        standard.getAccounts().add(njBusiness);
        njrobinson.addAccount(njBusiness);


        Configuration cfg = new Configuration().configure();

        try(SessionFactory sessionFactory = cfg.buildSessionFactory();
            Session session = sessionFactory.openSession()
        ) {

            try {
                session.beginTransaction();

                session.save(standard);
                session.save(njrobinson);

                session.getTransaction().commit();
            } catch (Exception e) {
                log.error("error when populating db with mock data", e);
                session.getTransaction().rollback();
            }


        }

    }

}
