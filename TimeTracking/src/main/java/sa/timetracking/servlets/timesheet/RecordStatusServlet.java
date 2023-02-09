package sa.timetracking.servlets.timesheet;

import logger.classes.Logger;
import sa.timetracking.jdbc.dao.classes.ProjectDAO;
import sa.timetracking.jdbc.dao.classes.RecordStatusDAO;
import sa.timetracking.jdbc.dto.RecordStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "status", value = "/status")
public class RecordStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.info("On Record Status Page");

        Boolean loggedIn = (Boolean) req.getSession().getAttribute("loggedInAsAdmin");

        if(loggedIn != null){
            if(loggedIn){
                req.setAttribute("records", new RecordStatusDAO().getAll());
                req.setAttribute("onStatus", true);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/timesheet/status.jsp");
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
        Logger.info("Called doPost(...) on RecordStatusServlet");

        String status;
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
                if (req.getParameter("status") != null) {
                    status = req.getParameter("status");
                } else {
                    Logger.error("Request parameter 'status' not found");
                    break;
                }
                Logger.info("Create Record Status: " + status + " from UserServlet");
                new RecordStatusDAO().create(new RecordStatus(status));
                break;
            case "update":
                if (req.getParameter("id") != null) {
                    id = Integer.parseInt(req.getParameter("id"));
                } else {
                    Logger.error("Request parameter 'id' not found");
                    break;
                }
                if (req.getParameter("status") != null) {
                    status = req.getParameter("status");
                } else {
                    Logger.error("Request parameter 'status' not found");
                    break;
                }
                Logger.info("Create Record Status: " + status + " from UserServlet");
                new RecordStatusDAO().update(new RecordStatus(id, status));
                break;
            case "delete":
                if (req.getParameter("statusID") != null) {
                    id = Integer.parseInt(req.getParameter("statusID"));
                } else {
                    Logger.error("Request parameter 'statusID' not found");
                    break;
                }
                Logger.info("Delete Record Status with ID: " + id + " from UserServlet");
                new RecordStatusDAO().delete(new RecordStatus(id));
                break;
        }
        resp.sendRedirect("status");
    }
}
