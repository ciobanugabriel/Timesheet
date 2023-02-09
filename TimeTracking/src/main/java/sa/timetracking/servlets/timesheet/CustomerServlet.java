package sa.timetracking.servlets.timesheet;

import logger.classes.Logger;
import sa.timetracking.jdbc.dao.classes.CustomerDAO;
import sa.timetracking.jdbc.dto.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "customer", value = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean loggedIn = (Boolean) req.getSession().getAttribute("loggedInAsAdmin");

        if (loggedIn != null) {
            if (loggedIn) {
                req.setAttribute("customers", new CustomerDAO().getAll());
                req.setAttribute("onCustomer", true);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/timesheet/customer.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("home");
            }
        } else {
            resp.sendRedirect("login");
        }
        Logger.info("On Customer Page");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Logger.info("Called doPost(...) on CustomerServlet");

        String name;
        String phoneNumber;
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
                if (req.getParameter("phone") != null) {
                    phoneNumber = req.getParameter("phone");
                } else {
                    Logger.error("Request parameter 'phone' not found");
                    break;
                }
                Logger.info("Create Customer: " + name + " from UserServlet");
                new CustomerDAO().create(new Customer(name, phoneNumber));
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
                Logger.info("Create Customer: " + name + " from UserServlet");
                new CustomerDAO().update(new Customer(id, name, phoneNumber));
                break;
            case "delete":
                if (req.getParameter("customerID") != null) {
                    id = Integer.parseInt(req.getParameter("customerID"));
                } else {
                    Logger.error("Request parameter 'customerID' not found");
                    break;
                }
                Logger.info("Delete Customer with ID: " + id + " from UserServlet");
                new CustomerDAO().delete(new Customer(id));
                break;
        }
        resp.sendRedirect("customer");
    }
}
