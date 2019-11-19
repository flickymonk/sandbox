package com.alevel.textboard;

import com.alevel.textboard.dao.comment.CommentDAO;
import com.alevel.textboard.dao.comment.JDBCCommentDAO;
import com.alevel.textboard.model.Comment;
import com.alevel.textboard.model.Persisted;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SelectAllComments {
    public static void main(String[] args) throws IOException, SQLException {

        try(Connection c = ConnectionFactory.connect()) {
            CommentDAO commentDAO = new JDBCCommentDAO(c);
            List<Persisted<Long, Comment>> comments = commentDAO.findAll();
            for (Persisted<Long, Comment> comment : comments) {
                System.out.println(comment.getId());
            }
        }

    }
}
