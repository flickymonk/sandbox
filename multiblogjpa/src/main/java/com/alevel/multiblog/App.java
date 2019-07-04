package com.alevel.multiblog;

import com.alevel.multiblog.entity.Comment;
import com.alevel.multiblog.entity.Post;
import com.alevel.multiblog.entity.User;
import com.alevel.multiblog.repository.UserRepository;
import com.alevel.multiblog.sources.StubDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            EntityManager entityManager = sessionFactory.createEntityManager();

            UserRepository userRepository = new UserRepository(entityManager);

            List<User> users = getUsers(entityManager);

            Optional<User> byEmail = userRepository.findByEmail("alpha.user@gmail.com");
            if (byEmail.isPresent()) {
                User alpha = byEmail.get();
                log.info("Alpha user: " + alpha);
                for (Post post : alpha.getPosts()) {
                    log.info("All comments under Gamma user's post");
                    for (Comment comment : post.getComments()) {
                        log.info("{}: {}", comment.getAuthor().getUsername(), comment.getText());
                    }
                }
            }

            Long lastUser = users.get(users.size() - 1).getId();

            getGammaUserById(userRepository, lastUser);

            getGammaUserByIdUsingEntityGraph(entityManager, lastUser);

        }
    }

    private static void getGammaUserByIdUsingEntityGraph(EntityManager entityManager, Long lastUser) {
        log.info("Pulling Gamma user using entity graph");
        EntityGraph<?> postsAndComments = entityManager.getEntityGraph("posts_and_comments");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", postsAndComments);
        User gamma = entityManager.find(User.class, lastUser, properties);
        if (gamma != null) {
            log.info("Gamma user: {}", gamma);
            log.info("Gamma user has {} posts", gamma.getPosts().size());
            log.info("Gamma user has {} comments",  gamma.getComments().size());
            boolean postLoaded = gamma.getComments().get(0).getPost() != null;
            log.info("Posts of comments loaded: {}", postLoaded);
        }
    }

    private static void getGammaUserById(UserRepository userRepository, Long id) {
        log.info("Pulling Gamma user");
        Optional<User> byId = userRepository.find(id);
        if (byId.isPresent()) {
            User gamma = byId.get();
            log.info("Gamma user: {}", gamma);
            log.info("Gamma user has {} posts", gamma.getPosts().size());
            log.info("Gamma user has {} comments",  gamma.getComments().size());
        }
    }

    private static List<User> getUsers(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<User> users = entityManager.createQuery("From User", User.class).getResultList();
        if (users.isEmpty()) {
            log.info("Saving default data");
            for (User user : StubDataSource.defaultUsers()) {
                entityManager.persist(user);
                users.add(user);
                for (Post post : user.getPosts()) {
                    entityManager.persist(post);
                    for (Comment comment : post.getComments()) {
                        entityManager.persist(comment);
                    }
                }
            }
        }
        transaction.commit();
        return users;
    }

}
