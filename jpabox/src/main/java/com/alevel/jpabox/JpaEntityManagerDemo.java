package com.alevel.jpabox;

import com.alevel.jpabox.entity.Guild;
import com.alevel.jpabox.entity.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaEntityManagerDemo {

    private static final Logger log = LoggerFactory.getLogger(JpaEntityManagerDemo.class);

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try(SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            EntityManager entityManager = sessionFactory.createEntityManager();
            log.info("Creating initial data");
//            addPlayerAndGuild(entityManager);
            log.info("Retrieving all players of guild 1");
            findAndLogAllPlayersOfGuild1(entityManager);
            findAndLogAllPlayersOfGuild1(entityManager);
            log.info("looking for noobs");
            findNoobs(entityManager);
            log.info("find empty guilds");
            findEmptyGuilds(entityManager);
        }

    }

    private static void addPlayerAndGuild(EntityManager entityManager) {
        try {
            entityManager.getTransaction().begin();

            Player player = new Player();
            player.setName("Player One");
            player.setScore(0);

            entityManager.persist(player);

            Guild guild = new Guild("G1");
            guild.addPlayer(player);

            entityManager.persist(guild);
            entityManager.merge(player);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    private static void findAndLogAllPlayersOfGuild1(EntityManager entityManager) {
        Guild guildOne = entityManager.find(Guild.class, 1L);
        log.info("Here are all players of {}", guildOne.getName());
        for (Player player : guildOne.getPlayers()) {
            log.info("{} -> score: {}", player.getName(), player.getScore());
        }
    }

    private static void findNoobs(EntityManager entityManager) {
        TypedQuery<Player> query = entityManager.createQuery("from Player where score = 0", Player.class);
        List<Player> noobs = query.getResultList();
        for (Player noob : noobs) {
            log.info("{} is a noob", noob.getName());
        }
    }

    private static void findEmptyGuilds(EntityManager entityManager) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Guild> cq = cb.createQuery(Guild.class);
        Root<Guild> from = cq.from(Guild.class);
        CriteriaQuery<Guild> wherePlayersEmpty = cq.select(from).where(cb.isEmpty(from.get("players")));
        TypedQuery<Guild> query = entityManager.createQuery(wherePlayersEmpty);
        List<Guild> emptyGuilds = query.getResultList();
        log.info("We have {} empty guilds", emptyGuilds.size());
    }
}
