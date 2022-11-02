package com.alevel.jeebox;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

@WebServlet(name = "sample-servlet", urlPatterns = "/sample")
public class SampleServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -1738342798982908762L;

    private static final Logger log = LoggerFactory.getLogger(SampleServlet.class);

    @Override
    public void init() {
        log.info(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter responseBody = resp.getWriter();

        resp.setContentType("text/html");

        responseBody.printf("""
                <h1 align="center">Sample Servlet GET method processing</h1>
                <h3 align="center">Request from: %s</h3>%n
                """, req.getRemoteHost());

        String client = req.getParameter("client");
        if (client == null) {
            client = "anonymous user";
        }

        responseBody.printf("<h3 align=\"center\">Hi, %s</h3>%n", client);
    }

    @Override
    public void destroy() {
        log.info(getServletName() + " destroyed");
    }
}
