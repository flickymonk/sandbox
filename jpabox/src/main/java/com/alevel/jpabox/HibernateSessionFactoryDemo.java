package com.alevel.jpabox;

import com.alevel.jpabox.entity.Guild;
import com.alevel.jpabox.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

public class HibernateSessionFactoryDemo {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(HibernateSessionFactoryDemo.class);

        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();

                Query deletePlayers = session.createQuery("delete Player");
                deletePlayers.executeUpdate();

                Query<Player> listPlayers = session.createQuery("from Player", Player.class);
                List<Player> players = listPlayers.list();
                System.out.println(players);

                Query deleteGuilds = session.createQuery("delete Guild");
                deleteGuilds.executeUpdate();

                Guild alevel = new Guild("A Level");
                session.saveOrUpdate(alevel);

                Player p1 = new Player("void", 0, alevel);
                alevel.addPlayer(p1);
                session.saveOrUpdate(p1);
                session.saveOrUpdate(alevel);

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
