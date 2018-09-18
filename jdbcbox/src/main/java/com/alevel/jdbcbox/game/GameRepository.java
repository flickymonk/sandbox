package com.alevel.jdbcbox.game;

import com.alevel.jdbcbox.common.Repository;
import com.alevel.jdbcbox.common.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public final class GameRepository implements Repository<Game, Long> {

    private final Supplier<Connection> connectionSupplier;

    public GameRepository(Supplier<Connection> connectionSupplier) {
        this.connectionSupplier = connectionSupplier;
    }

    @Override
    public void save(Game entity) throws StorageException {
        Connection connection = connectionSupplier.get();
        try {
            //get initial autocommit property
            boolean autoCommit = connection.getAutoCommit();

            try {
                connection.setAutoCommit(false);

                String saveGame = "INSERT INTO games (mvp_id, score) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(saveGame)) {
                    preparedStatement.setLong(1, entity.getWinnerId());
                    preparedStatement.setLong(2, entity.getScore());
                    preparedStatement.executeUpdate();
                }

                String addPointsToPlayer = "UPDATE players SET score = score + ? WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(addPointsToPlayer)) {
                    preparedStatement.setLong(1, entity.getScore());
                    preparedStatement.setLong(2, entity.getWinnerId());
                    preparedStatement.executeUpdate();
                }

                String addParticipants = "INSERT INTO participants VALUES (?, LAST_INSERT_ID());";
                try (PreparedStatement preparedStatement = connection.prepareStatement(addParticipants)) {
                    for (Long player : entity.getPlayers()) {
                        preparedStatement.setLong(1, player);
                        preparedStatement.addBatch();
                    }
                    preparedStatement.executeBatch();
                }

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw new StorageException(e);
            } finally {
                connection.setAutoCommit(autoCommit);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public List<Game> list() throws StorageException {
        String sql = "SELECT id, mvp_id, score, player_id FROM games g INNER JOIN participants p ON g.id = p.game_id";
        try (PreparedStatement statement = connectionSupplier.get().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            LinkedList<Game> games = new LinkedList<>();
            Long currentId = null;
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                if (!id.equals(currentId)) {
                    currentId = id;
                    Long score = resultSet.getLong("score");
                    Long winnerId = resultSet.getLong("mvp_id");
                    games.add(new Game(currentId, winnerId, score, new LinkedList<>()));
                }
                games.getLast().getPlayers().add(resultSet.getLong("player_id"));
            }
            return games;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Game get(Long id) throws StorageException {
        String sql = "SELECT id, mvp_id, score, player_id FROM games g " +
                "INNER JOIN participants p ON g.id = p.game_id " +
                "WHERE id = ?";
        try (PreparedStatement statement = connectionSupplier.get().prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Game game = null;
            while (resultSet.next()) {
                if (game == null) {
                    Long gameId = resultSet.getLong("id");
                    Long score = resultSet.getLong("score");
                    Long winnerId = resultSet.getLong("mvp_id");
                    game = new Game(gameId, winnerId, score, new LinkedList<>());
                }
                game.getPlayers().add(resultSet.getLong("player_id"));
            }
            return game;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(Game entity) throws StorageException {
        String sql = "DELETE FROM games WHERE id = ?";
        try (PreparedStatement statement = connectionSupplier.get().prepareStatement(sql)) {
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
