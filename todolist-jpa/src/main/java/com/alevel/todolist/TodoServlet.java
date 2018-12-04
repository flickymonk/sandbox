package com.alevel.todolist;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(TodoServlet.class);

    private ObjectMapper objectMapper;

    private TodoRepository todoRepository;

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() {
        objectMapper = new ObjectMapper();
        entityManagerFactory = Persistence.createEntityManagerFactory("todo-list-manager");
        EntityManager entityManager = entityManagerFactory
                .createEntityManager();
        todoRepository = new TodoRepository(entityManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Todo> todos;
        try {
            todos = todoRepository.listAllNotDone();
        } catch (TodoException e) {
            log.error("Error during retrieving todo list!", e);
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Todo todo = objectMapper.readValue(req.getInputStream(), Todo.class);
        Long id;
        try {
            id = todoRepository.save(todo);
        } catch (TodoException e) {
            resp.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
            return;
        }
        Map<String, Long> responseData = Collections.singletonMap("id", id);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.setContentType("application/json;charset=utf8");
        objectMapper.writeValue(resp.getOutputStream(), responseData);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Todo[] todos = objectMapper.readValue(req.getInputStream(), Todo[].class);
        try {
            todoRepository.batchUpdate(Arrays.asList(todos));
        } catch (TodoException e) {
            resp.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
            return;
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void destroy() {
        entityManagerFactory.close();
    }
}
