package com.alevel.textboard.dao.comment;

import com.alevel.textboard.dao.DataAccessException;
import com.alevel.textboard.model.Comment;
import com.alevel.textboard.model.Persisted;
import com.alevel.textboard.model.Thread;
import com.alevel.textboard.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class JDBCCommentDAO implements CommentDAO {

    private final Connection connection;

    public JDBCCommentDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Comment> findByAuthorId(Long authorId) {
        return null;
    }

    @Override
    public List<Comment> findByThreadId(Long threadId) {
        return null;
    }

    @Override
    public List<Comment> findByThreadIdAndAuthorId(Long threadId, Long authorId) {
        return null;
    }

    @Override
    public List<Comment> findByParentCommentId(Long parentCommentId) {
        return null;
    }

    @Override
    public List<Persisted<Long, Comment>> findAll() {
        String sql = "SELECT c.id, c.author_id, c.thread_id, c.parent_comment_id, c.text, u.nickname, u.email, t.title, t.text " +
                "FROM comments c " +
                "JOIN threads t ON c.thread_id = t.id " +
                "JOIN users u on c.author_id = u.id;";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            List<Persisted<Long, Comment>> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(mapRowToUser(resultSet));
            }
            return results;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Optional<Persisted<Long, Comment>> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Long save(Comment entity) {
        return null;
    }

    @Override
    public void update(Long id, Comment entity) {

    }

    @Override
    public void delete(Long id) {

    }

    private static Persisted<Long, Comment> mapRowToUser(
            ResultSet resultSet
    ) throws SQLException {

        User user = new User(
                resultSet.getString("email"),
                resultSet.getString("nickname")
        );

        Thread thread = new Thread(
                user,
                resultSet.getString("title"),
                resultSet.getString("t.text")
        );

        Long id = resultSet.getLong("id");

        long parentId = resultSet.getLong("parent_comment_id");

        Persisted<Long, Comment> parent = parentId == 0 ? null : new Persisted<>(parentId, null);

        Comment comment = new Comment(
                new Persisted<>(
                        resultSet.getLong("author_id"),
                        user
                ),
                new Persisted<>(
                        resultSet.getLong("thread_id"),
                        thread
                ),
                parent,
                resultSet.getString("c.text")
        );

        return new Persisted<>(
                id,
                comment
        );
    }
}
