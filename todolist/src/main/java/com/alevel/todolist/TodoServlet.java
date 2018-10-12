package com.alevel.todolist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {

    private ObjectMapper objectMapper;

    private HikariDataSource dataSource;

    private TodoRepository todoRepository;

    @Override
    public void init() {
        HikariConfig hikariConfig = new HikariConfig("/hikary.properties");
        dataSource = new HikariDataSource(hikariConfig);
        todoRepository = new TodoRepository(dataSource);
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Todo> todos;
        try {
            todos = todoRepository.listAllNotDone();
        } catch (TodoException e) {
            resp.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
            return;
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json;charset=utf8");
        objectMapper.writeValue(resp.getOutputStream(), todos);
    }

    @Override
    public void destroy() {
        dataSource.close();
    }
}
