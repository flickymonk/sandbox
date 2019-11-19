package com.alevel.textboard.dao.user;

import com.alevel.textboard.dao.DataAccessException;
import com.alevel.textboard.model.Persisted;
import com.alevel.textboard.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class JDBCUserDAO implements UserDAO {

    private final Connection connection;

    public JDBCUserDAO(Connection connection) {
        this.connection = connection;
    }

    private Optional<Persisted<Long, User>> findOne(PreparedStatement ps) throws SQLException {
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.first()) {
            return Optional.of(mapRowToUser(resultSet));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Persisted<Long, User>> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            return findOne(ps);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Optional<Persisted<Long, User>> findByNickname(String nickname) {
        String sql = "SELECT * FROM users WHERE nickname = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nickname);
            return findOne(ps);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Persisted<Long, User>> findAll() {
        String sql = "SELECT * FROM users;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            List<Persisted<Long, User>> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(mapRowToUser(resultSet));
            }
            return results;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Optional<Persisted<Long, User>> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            return findOne(ps);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Long save(User entity) {
        String sql = "INSERT INTO users (email, nickname) VALUES (?, ?);";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getNickname());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.first()) {
                return generatedKeys.getLong(1);
            } else {
                throw new DataAccessException("Could not retrieve generated key for user!");
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public void update(Long id, User entity) {
        String sql = "UPDATE users SET email = ?, nickname = ? WHERE id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getNickname());
            ps.setLong(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    private static Persisted<Long, User> mapRowToUser(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getString("email"),
                resultSet.getString("nickname")
        );
        return new Persisted<>(
                resultSet.getLong("id"),
                user
        );
    }
}
