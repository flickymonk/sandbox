package com.alevel.java.nix.mobileoperator;

import com.alevel.java.nix.mobileoperator.entity.Account;
import com.alevel.java.nix.mobileoperator.entity.Client;
import org.hibernate.cfg.Configuration;
import org.hibernate.graph.RootGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FindAccountsForClient {
    private static final Logger log = LoggerFactory.getLogger(MockDataLoader.class);

    public static void main(String[] args) {
        final long clientId = 1;

        var cfg = new Configuration().configure();

        try(var sessionFactory = cfg.buildSessionFactory();
            var session = sessionFactory.openSession()
        ) {
            try {

                List<Account> accounts = session.byId(Client.class)
                        .with((RootGraph<Client>) session.getEntityGraph("client-accounts"))
                        .getReference(clientId)
                        .getAccounts();

                for (Account account : accounts) {
                    log.info(account.getPhoneNumber());
                }

            } catch (Exception e) {
                log.error("error when retrieving account's expesnses", e);
            }


        }
    }
}
