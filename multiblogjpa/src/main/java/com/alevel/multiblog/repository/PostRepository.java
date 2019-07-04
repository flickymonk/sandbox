package com.alevel.multiblog.repository;

import com.alevel.multiblog.entity.Post;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PostRepository extends AbstractRepository<Post, Long> {
    PostRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Post> findAll() {
        return findAll(Post.class);
    }

    @Override
    public Optional<Post> find(Long id) {
        return find(id, Post.class);
    }
}
