package sa.timetracking.servlets;

import logger.classes.Logger;
import sa.timetracking.jdbc.dao.classes.CustomerDAO;
import sa.timetracking.jdbc.dao.classes.EmployeeDAO;
import sa.timetracking.jdbc.dao.classes.UserDAO;
import sa.timetracking.jdbc.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "user", value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean loggedIn = (Boolean) req.getSession().getAttribute("loggedInAsAdmin");

        if (loggedIn != null) {
            if (loggedIn) {
                req.setAttribute("users", new UserDAO().getAll());
                req.setAttribute("onUser", true);
                req.setAttribute("employees", new EmployeeDAO().getAll());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("home");
            }
        } else {
            resp.sendRedirect("login");
        }
        Logger.info("On User Page");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.info("Called doPost(...) on UserServlet");
        boolean exist = true;
        String username;
        String password;
        String buttonValue;
        boolean isAdmin;
        int employeeID;
        int id;

        if (req.getParameter("button") != null) {
            buttonValue = req.getParameter("button");
        } else {
            Logger.error("Request parameter 'button' not found");
            return;
        }

        switch (buttonValue) {
            case "create":
                if (req.getParameter("name") != null) {
                    username = req.getParameter("name");
                } else {
                    Logger.error("Request parameter 'name' not found");
                    break;
                }
                if (req.getParameter("password") != null) {
                    password = req.getParameter("password");
                } else {
                    Logger.error("Request parameter 'password' not found");
                    break;
                }
                if (req.getParameter("isAdmin") != null) {
                    isAdmin = req.getParameter("isAdmin").equals("true");
                } else {
                    Logger.error("Request parameter 'isAdmin' not found");
                    break;
                }
                if (req.getParameter("employeeID") != null) {
                    employeeID = Integer.parseInt(req.getParameter("employeeID"));
                } else {
                    Logger.error("Request parameter 'employeeID' not found");
                    break;
                }
                User user = new UserDAO().getAll().stream().filter(u -> u.getName().equals(username)).findAny().orElse(null);
                if(user != null){
                    Logger.error("User: '" + username + "' exists in database.");
                    String language = (String)req.getSession().getAttribute("language");
                    switch (language){
                        case "en_EN":
                            req.getSession().setAttribute("error_message_user", "User: " + username + " exists.");
                            break;
                        case "ro_RO":
                            req.getSession().setAttribute("error_message_user", "Utilizatorul: " + username + " exista.");
                            break;
                        case "de_DE":
                            req.getSession().setAttribute("error_message_user", "\n" +
                                    "Benutzer: " + username + " existiert.");
                            break;
                    }
                }else{
                    Logger.info("Create User: " + username + " from UserServlet");
                    new UserDAO().create(new User(username, encrypt(username,password), isAdmin, employeeID));
                }
                break;
            case "update":
                if (req.getParameter("id") != null) {
                    id = Integer.parseInt(req.getParameter("id"));
                } else {
                    Logger.error("Request parameter 'id' not found");
                    break;
                }
                if (req.getParameter("name") != null) {
                    username = req.getParameter("name");
                } else {
                    Logger.error("Request parameter 'name' not found");
                    break;
                }
                if (req.getParameter("password") != null) {
                    password = req.getParameter("password");
                } else {
                    Logger.error("Request parameter 'password' not found");
                    break;
                }
                if (req.getParameter("isAdmin") != null) {
                    isAdmin = req.getParameter("isAdmin").equals("true");
                } else {
                    Logger.error("Request parameter 'isAdmin' not found");
                    break;
                }
                if (req.getParameter("employeeID") != null) {
                    employeeID = Integer.parseInt(req.getParameter("employeeID"));
                } else {
                    Logger.error("Request parameter 'employeeID' not found");
                    break;
                }
                Logger.info("Update User: " + username + " from UserServlet");
                new UserDAO().update(new User(id, username, encrypt(username,password), isAdmin, employeeID));
                break;
            case "delete":
                if (req.getParameter("userID") != null) {
                    id = Integer.parseInt(req.getParameter("userID"));
                } else {
                    Logger.error("Request parameter 'userID' not found");
                    break;
                }
                Logger.info("Delete User with ID: " + id + " from UserServlet");
                new UserDAO().delete(new User(id));
                break;
        }
        resp.sendRedirect("user");
    }

    private  String encrypt(final String input ,final String key) {
        StringBuilder output = new StringBuilder();
        StringBuilder text = new StringBuilder();

        if(input.length() >= key.length()){
            for(int i=0; i< key.length(); i++){
                text.append(input.charAt(i)).append(key.charAt(i));
            }
            text.append(input.substring(key.length()));
        }else{
            for(int i=0; i< input.length(); i++){
                text.append(input.charAt(i)).append(key.charAt(i));
            }
            text.append(key.substring(input.length()));
        }
        for (int i = 0; i < text.length(); i++) {
            output.append(text.charAt(i) ^ key.charAt(i % key.length()));
        }
        return output.toString();
    }
}
