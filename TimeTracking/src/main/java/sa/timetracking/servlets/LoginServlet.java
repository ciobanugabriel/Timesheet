package sa.timetracking.servlets;

import logger.classes.Logger;
import sa.timetracking.jdbc.dao.classes.UserDAO;
import sa.timetracking.jdbc.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean loggedIn = (Boolean) req.getSession().getAttribute("loggedInAsAdmin");
        if (loggedIn != null && loggedIn) {
            resp.sendRedirect("home");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
        }
        Logger.info("On Login Page");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.info("Called doPost(...) on LoginServlet");
        String username;
        String password;
        Boolean loggedIn;
        boolean logged = false;
        loggedIn = (Boolean) req.getSession().getAttribute("loggedInAsAdmin");

        if (loggedIn != null) {
            resp.sendRedirect("home");
            return;
        }
        if (req.getParameter("username") != null) {
            username = req.getParameter("username");
        } else {
            Logger.error("Request parameter 'username' not found");
            return;
        }
        if (req.getParameter("password") != null) {
            password = decrypt(username, req.getParameter("password"));
        } else {
            Logger.error("Request parameter 'password' not found");
            return;
        }

        List<User> users = new UserDAO().getAll();

        for (User user : users) {
            if (username.equals(user.getName()) && password.equals(user.getPassword())) { // && password.equals(user.getPassword())
                logged = true;
                req.getSession().setAttribute("userLogged", user);
                req.getSession().setAttribute("loggedInAsAdmin", user.isAdmin());
                Logger.debug("Authentication successful for: " + username);
                resp.sendRedirect("home");
            }
        }
        if (!logged) {
            Logger.debug("Failed to authenticate: " + username);
            req.setAttribute("error_message", "Failed to authenticate!");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
        }
        if(req.getSession().getAttribute("language") == null){
            req.getSession().setAttribute("language","en_US");
        }
        if(req.getSession().getAttribute("roButton") == null){
            req.getSession().setAttribute("roButton","btn btn-outline-secondary btn-sm");
        }
        if(req.getSession().getAttribute("enButton") == null){
            req.getSession().setAttribute("enButton","btn btn-warning btn-sm");
        }
        if(req.getSession().getAttribute("deButton") == null){
            req.getSession().setAttribute("deButton","btn btn-outline-secondary btn-sm");
        }
    }

    private String decrypt(final String input, final String key) {
        StringBuilder output = new StringBuilder();
        StringBuilder text = new StringBuilder();

        if (input.length() >= key.length()) {
            for (int i = 0; i < key.length(); i++) {
                text.append(input.charAt(i)).append(key.charAt(i));
            }
            text.append(input.substring(key.length()));
        } else {
            for (int i = 0; i < input.length(); i++) {
                text.append(input.charAt(i)).append(key.charAt(i));
            }
            text.append(key.substring(input.length()));
        }
        for (int i = 0; i < text.length(); i++) {
            output.append(text.charAt(i) ^ key.charAt(i % key.length()));
        }
        Logger.info("Password decrypted");
        return output.toString();
    }
}
