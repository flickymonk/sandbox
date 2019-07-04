package com.alevel.multiblog.sources;

import com.alevel.multiblog.entity.Comment;
import com.alevel.multiblog.entity.Post;
import com.alevel.multiblog.entity.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class StubDataSource {

    private StubDataSource() {
    }

    public static List<User> defaultUsers() {

        User alpha = new User("alpha.user@gmail.com", "Alpha");

        User beta = new User("beta.user@ukr.net", "Beta");
        User gamma = new User("gamma.user@protonmail.com", "Gamma");


        Post alphaPost1 = new Post(alpha, "hi all", "nice to meet you!");
        Post alphaPost2 = new Post(alpha, "introduction", "my name is Alpha, First User");
        Collections.addAll(alpha.getPosts(), alphaPost1, alphaPost2);

        Comment betaComment1 = new Comment(1, beta, alphaPost1, "Hey, Alpha");
        Comment betaComment2 = new Comment(2, beta, alphaPost1, "Nice to meet you too");
        Comment gammaComment3 = new Comment(3, gamma, alphaPost1, "Hi there");

        List<Post> gammaPosts = gamma.getPosts();
        for (int i = 0; i < 1000; i++) {
            Post gammaPost3 = new Post(gamma, "post #" + i, "I like to spam a lot");
            gammaPosts.add(gammaPost3);
        }

        Collections.addAll(beta.getComments(), betaComment1, betaComment2);
        Collections.addAll(gamma.getComments(), gammaComment3);
        Collections.addAll(alphaPost1.getComments(), betaComment1, betaComment2, gammaComment3);

        return Arrays.asList(alpha, beta, gamma);
    }

}
