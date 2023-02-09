package sa.timetracking.servlets.timesheet;

import logger.classes.Logger;
import sa.timetracking.jdbc.dao.classes.ProjectDAO;
import sa.timetracking.jdbc.dto.Project;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "project", value = "/project")
public class ProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.info("On Project Page");

        Boolean loggedIn = (Boolean) req.getSession().getAttribute("loggedInAsAdmin");

        if (loggedIn != null) {
            if (loggedIn) {
                req.setAttribute("projects", new ProjectDAO().getAll());
                req.setAttribute("onProject", true);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/timesheet/project.jsp");
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
        Logger.info("Called doPost(...) on ProjectServlet");

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
                Logger.info("Create Project: " + name + " from UserServlet");
                new ProjectDAO().create(new Project(name));
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
                Logger.info("Create Project: " + name + " from UserServlet");
                new ProjectDAO().update(new Project(id, name));
                break;
            case "delete":
                if (req.getParameter("projectID") != null) {
                    id = Integer.parseInt(req.getParameter("projectID"));
                } else {
                    Logger.error("Request parameter 'projectID' not found");
                    break;
                }
                Logger.info("Delete Project with ID: " + id + " from UserServlet");
                new ProjectDAO().delete(new Project(id));
                break;
        }
        resp.sendRedirect("project");
    }
}
