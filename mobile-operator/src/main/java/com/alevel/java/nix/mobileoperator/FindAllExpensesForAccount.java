package com.alevel.java.nix.mobileoperator;

import com.alevel.java.nix.mobileoperator.entity.Account;
import com.alevel.java.nix.mobileoperator.entity.Expense;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FindAllExpensesForAccount {

    private static final Logger log = LoggerFactory.getLogger(MockDataLoader.class);

    public static void main(String[] args) {

        final long accountId = 1;

        var cfg = new Configuration().configure();

        try(var sessionFactory = cfg.buildSessionFactory();
            var session = sessionFactory.openSession()
        ) {
            try {

                List<Expense> expenseOperations = session
                        .load(Account.class, accountId)
                        .getExpenses();

                for (Expense expenseOperation : expenseOperations) {
                    log.info(
                            "Account {} expended {} at {}: {}",
                            accountId,
                            -expenseOperation.getAmount(),
                            expenseOperation.getTimestamp(),
                            expenseOperation.getDescription()
                    );
                }

            } catch (Exception e) {
                log.error("error when retrieving account's expesnses", e);
            }


        }

    }

}
