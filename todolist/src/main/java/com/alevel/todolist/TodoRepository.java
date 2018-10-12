package com.alevel.todolist;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TodoRepository {

    private final DataSource dataSource;

    public TodoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Long save(Todo entity) throws TodoException {
        String sql = "INSERT INTO todos (text) VALUE (?); SELECT LAST_INSERT_ID();";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(sql)
        ) {
            query.setString(1, entity.getText());
            ResultSet resultSet = query.executeQuery();
            resultSet.first();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new TodoException(e);
        }
    }

    public void update(Todo entity) throws TodoException {
        String sql = "UPDATE todos SET text = ?, is_done = ? WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(sql)
        ) {
            query.setString(1, entity.getText());
            query.setBoolean(2, entity.isDone());
            query.setLong(3, entity.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new TodoException(e);
        }
    }

    public List<Todo> listAllNotDone() throws TodoException {
        String sql = "SELECT * FROM todos WHERE is_done = false";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = query.executeQuery();
            List<Todo> todos = new LinkedList<>();
            while (resultSet.next()) {
                todos.add(new Todo(
                        resultSet.getLong("id"),
                        resultSet.getString("text"),
                        false
                ));
            }
            return todos;
        } catch (SQLException e) {
            throw new TodoException(e);
        }
    }

}
