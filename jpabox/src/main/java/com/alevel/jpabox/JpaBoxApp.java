package com.alevel.jpabox;

import com.alevel.jpabox.entity.Guild;
import com.alevel.jpabox.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class JpaBoxApp {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(JpaBoxApp.class);

        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Guild.class);
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        Transaction transaction = null;
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry)) {
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();

                Query deletePlayers = session.createQuery("delete Player");
                deletePlayers.executeUpdate();

                Query<Player> listPlayers = session.createQuery("from Player", Player.class);
                List<Player> players = listPlayers.list();
                System.out.println(players);

                Query deleteGuilds = session.createQuery("delete Guild");
                deleteGuilds.executeUpdate();

                Guild alevel = new Guild("A Level", Collections.emptyList());
                session.save(alevel);

                Player p1 = new Player("void", 0, alevel);
                session.save(p1);

                Guild guildByOurId = session.find(Guild.class, alevel.getId());
                boolean onlyOurPlayer = guildByOurId.getPlayers().stream()
                        .allMatch(Predicate.isEqual(p1));
                logger.info("Do we have our player: {}", onlyOurPlayer);

                transaction.commit();

            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.error("Error during transaction", e);
            }
        }

    }
}
