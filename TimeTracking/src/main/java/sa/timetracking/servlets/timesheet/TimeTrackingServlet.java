package sa.timetracking.servlets.timesheet;

import logger.classes.Logger;
import sa.timetracking.jdbc.dao.classes.*;
import sa.timetracking.jdbc.dto.TimeTracking;
import sa.timetracking.jdbc.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "time-tracking", value = "/time-tracking")
public class TimeTrackingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.info("On TimeTracking Page");

        Boolean loggedIn = (Boolean) req.getSession().getAttribute("loggedInAsAdmin");

        if (loggedIn != null) {
            req.setAttribute("timeTrackingList", new TimeTrackingDAO().getAll());
            req.setAttribute("onTimeTracking", true);
            req.setAttribute("customers", new CustomerDAO().getAll());
            req.setAttribute("employees", new EmployeeDAO().getAll());
            req.setAttribute("projectOwners", new ProjectOwnerDAO().getAll());
            req.setAttribute("projects", new ProjectDAO().getAll());
            req.setAttribute("tasks", new TaskDAO().getAll());
            req.setAttribute("records", new RecordStatusDAO().getAll());

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/timesheet/time-tracking.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Logger.info("Called doPost(...) on TimeTrackingServlet");

        int id;
        int employeeID;
        int projectID;
        int workedHours;
        int projectOwnerID;
        int taskID;
        Date start;
        Date end;
        int customerID;
        int recordID;
        String comment;
        String buttonValue;
        User user;

        if (req.getParameter("button") != null) {
            buttonValue = req.getParameter("button");
        } else {
            Logger.error("Request parameter 'button' not found");
            return;
        }
        switch (buttonValue) {
            case "create":
                user = (User) req.getSession().getAttribute("userLogged");
                if (user != null) {
                    employeeID = user.getEmployeeID();
                } else {
                    Logger.error("User logged attribute not found in session");
                    break;
                }
                if (req.getParameter("selectedProjectID") != null) {
                    projectID = Integer.parseInt(req.getParameter("selectedProjectID"));
                } else {
                    Logger.error("Request parameter 'selectedProjectID' not found");
                    break;
                }
                if (req.getParameter("workedHours") != null) {
                    workedHours = Integer.parseInt(req.getParameter("workedHours"));
                } else {
                    Logger.error("Request parameter 'workedHours' not found");
                    break;
                }
                if (req.getParameter("selectedProjectOwnerID") != null) {
                    projectOwnerID = Integer.parseInt(req.getParameter("selectedProjectOwnerID"));
                } else {
                    Logger.error("Request parameter 'selectedProjectOwnerID' not found");
                    break;
                }
                if (req.getParameter("selectedTaskID") != null) {
                    taskID = Integer.parseInt(req.getParameter("selectedTaskID"));
                } else {
                    Logger.error("Request parameter 'selectedTaskID' not found");
                    break;
                }
                if (req.getParameter("start") != null) {
                    start = Date.valueOf(LocalDate.parse(req.getParameter("start")));
                } else {
                    Logger.error("Request parameter 'start' not found");
                    break;
                }
                if (req.getParameter("end") != null) {
                    end = Date.valueOf(LocalDate.parse(req.getParameter("end")));
                } else {
                    Logger.error("Request parameter 'end' not found");
                    break;
                }
                if (req.getParameter("selectedCustomerID") != null) {
                    customerID = Integer.parseInt(req.getParameter("selectedCustomerID"));
                } else {
                    Logger.error("Request parameter 'selectedCustomerID' not found");
                    break;
                }
                if (req.getParameter("selectedRecordID") != null) {
                    recordID = Integer.parseInt(req.getParameter("selectedRecordID"));
                } else {
                    Logger.error("Request parameter 'selectedRecordID' not found");
                    break;
                }
                if (req.getParameter("comment") != null) {
                    comment = req.getParameter("comment");
                } else {
                    Logger.error("Request parameter 'comment' not found");
                    break;
                }
                Logger.info("Create Time Tracking: from UserServlet");

                new TimeTrackingDAO().create(new TimeTracking(new EmployeeDAO().getById(employeeID), new ProjectDAO().getById(projectID),
                        workedHours, new ProjectOwnerDAO().getById(projectOwnerID), new TaskDAO().getById(taskID), start, end,
                        new CustomerDAO().getById(customerID), new RecordStatusDAO().getById(recordID), comment));
                break;
            case "update":
                if (req.getParameter("id") != null) {
                    id = Integer.parseInt(req.getParameter("id"));
                } else {
                    Logger.error("Request parameter 'id' not found");
                    break;
                }
                user = (User) req.getSession().getAttribute("userLogged");
                if (user != null) {
                    employeeID = user.getEmployeeID();
                } else {
                    Logger.error("User logged attribute not found in session");
                    break;
                }
                if (req.getParameter("selectedProjectID") != null) {
                    projectID = Integer.parseInt(req.getParameter("selectedProjectID"));
                } else {
                    Logger.error("Request parameter 'selectedProjectID' not found");
                    break;
                }
                if (req.getParameter("workedHours") != null) {
                    workedHours = Integer.parseInt(req.getParameter("workedHours"));
                } else {
                    Logger.error("Request parameter 'workedHours' not found");
                    break;
                }
                if (req.getParameter("selectedProjectOwnerID") != null) {
                    projectOwnerID = Integer.parseInt(req.getParameter("selectedProjectOwnerID"));
                } else {
                    Logger.error("Request parameter 'selectedProjectOwnerID' not found");
                    break;
                }
                if (req.getParameter("selectedTaskID") != null) {
                    taskID = Integer.parseInt(req.getParameter("selectedTaskID"));
                } else {
                    Logger.error("Request parameter 'selectedTaskID' not found");
                    break;
                }
                if (req.getParameter("start") != null) {
                    start = Date.valueOf(LocalDate.parse(req.getParameter("start")));
                } else {
                    Logger.error("Request parameter 'start' not found");
                    break;
                }
                if (req.getParameter("end") != null) {
                    end = Date.valueOf(LocalDate.parse(req.getParameter("end")));
                } else {
                    Logger.error("Request parameter 'end' not found");
                    break;
                }
                if (req.getParameter("selectedCustomerID") != null) {
                    customerID = Integer.parseInt(req.getParameter("selectedCustomerID"));
                } else {
                    Logger.error("Request parameter 'selectedCustomerID' not found");
                    break;
                }
                if (req.getParameter("selectedRecordID") != null) {
                    recordID = Integer.parseInt(req.getParameter("selectedRecordID"));
                } else {
                    Logger.error("Request parameter 'selectedRecordID' not found");
                    break;
                }
                if (req.getParameter("comment") != null) {
                    comment = req.getParameter("comment");
                } else {
                    Logger.error("Request parameter 'comment' not found");
                    break;
                }
                Logger.info("Update Time Tracking: from UserServlet");

                new TimeTrackingDAO().update(new TimeTracking(id, new EmployeeDAO().getById(employeeID), new ProjectDAO().getById(projectID),
                        workedHours, new ProjectOwnerDAO().getById(projectOwnerID), new TaskDAO().getById(taskID), start, end,
                        new CustomerDAO().getById(customerID), new RecordStatusDAO().getById(recordID), comment));
                break;
            case "delete":
                if (req.getParameter("timeTrackingID") != null) {
                    id = Integer.parseInt(req.getParameter("timeTrackingID"));
                } else {
                    Logger.error("Request parameter 'timeTrackingID' not found");
                    break;
                }
                Logger.info("Delete Time Tracking with ID: " + id + " from UserServlet");
                new TimeTrackingDAO().delete(new TimeTracking(id));
                break;
        }
        resp.sendRedirect("time-tracking");
    }

}
