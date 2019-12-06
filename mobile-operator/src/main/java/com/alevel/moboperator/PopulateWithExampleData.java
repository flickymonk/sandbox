package com.alevel.moboperator;

import com.alevel.moboperator.entity.Account;
import com.alevel.moboperator.entity.Customer;
import com.alevel.moboperator.entity.Name;
import com.alevel.moboperator.entity.Tariff;
import com.alevel.moboperator.util.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class PopulateWithExampleData {
    public static void main(String[] args) {

        Transaction tx = null;
        try (SessionFactory sessionFactory = HibernateHelper.createSessionFactory();
             Session session = sessionFactory.openSession()
        ) {
            tx = session.beginTransaction();

            Tariff basic = new Tariff("MO Basic", "Basic pre-payed plan", 5000);
            session.save(basic);

            Customer customer = new Customer(
                    new Name("Mike", "Johnson"),
                    LocalDate.of(1990, 6, 25)
            );
            Account account = new Account("+10582479847", customer, basic);
            session.save(customer);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }

    }
}
