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

        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Comment.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Post.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            EntityManager entityManager = sessionFactory.createEntityManager();

            UserRepository userRepository = new UserRepository(entityManager);

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                log.info("Saving default data");
                for (User defaultUser : StubDataSource.defaultUsers()) {
                    User saved = userRepository.save(defaultUser);
                    users.add(saved);
                }
            }
            transaction.commit();

            for (User user : users) {
                log.info("{}", user);
                for (Post post : user.getPosts()) {
                    log.info("-->{}", post);
                    for (Comment comment : post.getComments()) {
                        log.info("+++-->{}", comment);
                    }
                }
            }

            Optional<User> byEmail = userRepository.findByEmail("alpha.user@gmail.com");
            if (byEmail.isPresent()) {
                User alpha = byEmail.get();
                log.info("Alpha user: " + alpha);
            }

            Long lastUser = users.get(users.size() - 1).getId();
            EntityGraph<?> postsAndComments = entityManager.getEntityGraph("posts_and_comments");
            Map<String, Object> properties = new HashMap<>();
            properties.put("javax.persistence.fetchgraph", postsAndComments);
            User gamma = entityManager.find(User.class, lastUser, properties);
            log.info("Gamma user: " + gamma);
        }


    }

}
