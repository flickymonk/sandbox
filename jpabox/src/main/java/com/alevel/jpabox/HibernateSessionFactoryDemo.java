package com.alevel.jpabox;

import com.alevel.jpabox.entity.Guild;
import com.alevel.jpabox.entity.Player;
import com.alevel.jpabox.entity.PlayerClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();

                Query<?> deletePlayerClasses = session.createQuery("delete PlayerClass", Player.class);
                deletePlayerClasses.executeUpdate();

                Query<?> deletePlayers = session.createQuery("delete Player", Player.class);
                deletePlayers.executeUpdate();

                Query<Player> listPlayers = session.createQuery("from Player", Player.class);
                List<Player> players = listPlayers.list();
                logger.info("Players: {}", players);

                Query<?> deleteGuilds = session.createQuery("delete Guild", Guild.class);
                deleteGuilds.executeUpdate();

                Guild alevel = new Guild("A Level");
                session.saveOrUpdate(alevel);

                Player p1 = new Player("void", 0, alevel);
                alevel.addPlayer(p1);
                session.saveOrUpdate(p1);
                session.saveOrUpdate(alevel);

                var playerClass = new PlayerClass();
                playerClass.setName("Warrior");
                p1.getClasses().add(playerClass);
                playerClass.getPlayers().add(p1);

                session.save(playerClass);

                Guild guildByOurId = session.load(Guild.class, alevel.getId());
                boolean onlyOurPlayer = guildByOurId.getPlayers().stream()
                        .allMatch(Predicate.isEqual(p1));
                logger.info("Do we have our player: {}", onlyOurPlayer);

                session.getTransaction().commit();

            } catch (Exception e) {
                session.getTransaction().rollback();
                logger.error("Error during transaction", e);
            }
        }

    }
}
