package com.alevel.moboperator;

import com.alevel.moboperator.entity.*;
import com.alevel.moboperator.util.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class PopulateWithExampleData {

    private static final Logger log = LoggerFactory.getLogger(PopulateWithExampleData.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateHelper.createSessionFactory();
        Session session = sessionFactory.openSession();
        try (sessionFactory; session) {
            session.beginTransaction();


            Tariff basic = new Tariff("MO Basic", "Basic pre-payed plan", 5000);
            session.save(basic);

            Customer customer = new Customer(
                    new Name("Mike", "Johnson"),
                    LocalDate.of(1990, 6, 25)
            );
            Account account = new Account("+10582479847", customer, basic);
            session.save(customer);

            Promotion signInBonus = new Promotion(
                    "Sign In +10",
                    "Get 10$ credit on switching to this tariff!",
                    LocalDate.now(),
                    LocalDate.now().plusWeeks(5)
            );
            signInBonus.getApplicableTo().add(basic);
            session.save(signInBonus);
            basic.getPromotions().add(signInBonus);
            session.update(basic);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while populating db with example data", e);
            session.getTransaction().rollback();
        }

    }
}
