package sa.timetracking.servlets;

import logger.classes.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean loggedIn = (Boolean) req.getSession().getAttribute("loggedInAsAdmin");
        if(loggedIn != null){
            Logger.debug("Logged out");
            req.getSession().removeAttribute("loggedInAsAdmin");
            resp.sendRedirect("login");
        }else{
            resp.sendRedirect("login");
        }
    }
}
