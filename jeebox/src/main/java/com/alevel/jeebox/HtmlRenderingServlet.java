package com.alevel.jeebox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@WebServlet(urlPatterns = "/render")
public class HtmlRenderingServlet extends HttpServlet {

    private String responseTemplate;

    @Override
    public void init() throws ServletException {

        try {
            URI templateURI = getClass().getResource("/template/demo.html").toURI();
            responseTemplate = Files.readString(Paths.get(templateURI), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            throw new ServletException(e);
        }

        System.out.println("!!! HtmlRendering Servlet Initialized !!!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String remoteHost = req.getRemoteHost(); //get ip

        System.out.println("GET from " + remoteHost);

        String parameter = req.getParameter("u");

        String username = Optional
                .ofNullable(parameter)
                .orElse("Anonymous");

        String responseBody = responseTemplate
                .replace("{username}", username)
                .replace("{host}", remoteHost);

        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().print(responseBody);
    }

    @Override
    public void destroy() {
        System.out.println("!!! HtmlRendering Servlet Destroyed !!!");
    }
}