package sa.timetracking.servlets.timesheet;

import logger.classes.Logger;
import sa.timetracking.jdbc.dao.classes.EmployeeDAO;
import sa.timetracking.jdbc.dto.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "employee", value = "/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.info("On Employee Page");

        Boolean loggedIn = (Boolean) req.getSession().getAttribute("loggedInAsAdmin");

        if (loggedIn != null) {
            if (loggedIn) {
                req.setAttribute("employees", new EmployeeDAO().getAll());
                req.setAttribute("onEmployee", true);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/timesheet/employee.jsp");
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
        Logger.info("Called doPost(...) on EmployeeServlet");

        String name;
        String phoneNumber;
        String buttonValue;
        int age;
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
                if (req.getParameter("phone") != null) {
                    phoneNumber = req.getParameter("phone");
                } else {
                    Logger.error("Request parameter 'phone' not found");
                    break;
                }
                if (req.getParameter("age") != null) {
                    age = Integer.parseInt(req.getParameter("age"));
                } else {
                    Logger.error("Request parameter 'age' not found");
                    break;
                }
                Logger.info("Create Employee: " + name + " from UserServlet");
                new EmployeeDAO().create(new Employee(name, age, phoneNumber));
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
                if (req.getParameter("phone") != null) {
                    phoneNumber = req.getParameter("phone");
                } else {
                    Logger.error("Request parameter 'phone' not found");
                    break;
                }
                if (req.getParameter("age") != null) {
                    age = Integer.parseInt(req.getParameter("age"));
                } else {
                    Logger.error("Request parameter 'age' not found");
                    break;
                }
                Logger.info("Create Employee: " + name + " from UserServlet");
                new EmployeeDAO().update(new Employee(id, name, age, phoneNumber));
                break;
            case "delete":
                if (req.getParameter("employeeID") != null) {
                    id = Integer.parseInt(req.getParameter("employeeID"));
                } else {
                    Logger.error("Request parameter 'employeeID' not found");
                    break;
                }
                Logger.info("Delete Employee with ID: " + id + " from UserServlet");
                new EmployeeDAO().delete(new Employee(id));
                break;
        }
        resp.sendRedirect("employee");
    }
}
