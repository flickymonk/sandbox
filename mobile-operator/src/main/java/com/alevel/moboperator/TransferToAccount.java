package com.alevel.moboperator;

import com.alevel.moboperator.entity.Account;
import com.alevel.moboperator.util.HibernateHelper;
import com.alevel.moboperator.util.MonetaryAmount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferToAccount {

    private static final Logger log = LoggerFactory.getLogger(TransferToAccount.class.getName());

    public static void main(String[] args) {
        String phoneNumber = args[0];
        long amount = MonetaryAmount.parse(args[1]);

        log.info("Transferring {} to {}", MonetaryAmount.toString(amount), phoneNumber);

        SessionFactory sessionFactory = HibernateHelper.createSessionFactory();
        Session session = sessionFactory.openSession();

        try(sessionFactory; session) {
            session.beginTransaction();

            Account account = session.bySimpleNaturalId(Account.class).load(phoneNumber);

            if (account == null) {
                log.error("Account {} does not exist", phoneNumber);
                session.getTransaction().commit();
                return;
            }

            account.setBalance(account.getBalance() + amount);
            session.update(account);

            log.info("Account {} updated, balance = {}", phoneNumber, MonetaryAmount.toString(account.getBalance()));

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error during fund transfer", e);
            session.getTransaction().rollback();
        }
    }
}
