package sa.timetracking.servlets.timesheet;

import logger.classes.Logger;
import sa.timetracking.jdbc.dao.classes.RecordStatusDAO;
import sa.timetracking.jdbc.dao.classes.TaskDAO;
import sa.timetracking.jdbc.dto.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "task", value = "/task")
public class TaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.info("On Task Page");

        Boolean loggedIn = (Boolean) req.getSession().getAttribute("loggedInAsAdmin");

        if(loggedIn != null){
            if(loggedIn){
                req.setAttribute("tasks", new TaskDAO().getAll());
                req.setAttribute("onTask", true);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/timesheet/task.jsp");
                requestDispatcher.forward(req, resp);
            }else{
                resp.sendRedirect("home");
            }
        }else{
            resp.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Logger.info("Called doPost(...) on TaskServlet");

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
                Logger.info("Create Task: " + name + " from UserServlet");
                new TaskDAO().create(new Task(name));
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
                Logger.info("Create Task: " + name + " from UserServlet");
                new TaskDAO().update(new Task(id, name));
                break;
            case "delete":
                if (req.getParameter("taskID") != null) {
                    id = Integer.parseInt(req.getParameter("taskID"));
                } else {
                    Logger.error("Request parameter 'taskID' not found");
                    break;
                }
                Logger.info("Delete Task with ID: " + id + " from UserServlet");
                new TaskDAO().delete(new Task(id));
                break;
        }
        resp.sendRedirect("task");
    }
}
