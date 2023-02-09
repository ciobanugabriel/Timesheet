package sa.timetracking.servlets.timesheet;

import logger.classes.Logger;
import sa.timetracking.jdbc.dao.classes.ProjectOwnerDAO;
import sa.timetracking.jdbc.dto.ProjectOwner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "project-owner", value = "/project-owner")
public class ProjectOwnerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.info("On Project Owner Page");

        Boolean loggedIn = (Boolean) req.getSession().getAttribute("loggedInAsAdmin");

        if (loggedIn != null) {
            if (loggedIn) {
                req.setAttribute("projectOwners", new ProjectOwnerDAO().getAll());
                req.setAttribute("onProjectOwner", true);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/timesheet/project-owner.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("home");
            }
        } else {
            resp.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Logger.info("Called doPost(...) on ProjectOwnerServlet");

        String name;
        String buttonValue;
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
                    name = req.getParameter("name");
                } else {
                    Logger.error("Request parameter 'name' not found");
                    break;
                }
                Logger.info("Create Project Owner: " + name + " from UserServlet");
                new ProjectOwnerDAO().create(new ProjectOwner(name));
                break;
            case "update":
                if (req.getParameter("id") != null) {
                    id = Integer.parseInt(req.getParameter("id"));
                } else {
                    Logger.error("Request parameter 'id' not found");
                    break;
                }
                if (req.getParameter("name") != null) {
                    name = req.getParameter("name");
                } else {
                    Logger.error("Request parameter 'name' not found");
                    break;
                }
                Logger.info("Create Project Owner: " + name + " from UserServlet");
                new ProjectOwnerDAO().update(new ProjectOwner(id, name));
                break;
            case "delete":
                if (req.getParameter("ownerID") != null) {
                    id = Integer.parseInt(req.getParameter("ownerID"));
                } else {
                    Logger.error("Request parameter 'ownerID' not found");
                    break;
                }
                Logger.info("Delete Project Owner with ID: " + id + " from UserServlet");
                new ProjectOwnerDAO().delete(new ProjectOwner(id));
                break;
        }
        resp.sendRedirect("project-owner");
    }
}
