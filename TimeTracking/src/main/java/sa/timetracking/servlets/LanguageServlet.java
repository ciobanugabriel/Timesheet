package sa.timetracking.servlets;

import logger.classes.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "language", value = "/language")
public class LanguageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String buttonValue;


        if (req.getParameter("button") != null) {
            buttonValue = req.getParameter("button");
        } else {
            Logger.error("Request parameter 'button' not found");
            return;
        }
        switch (buttonValue) {
            case "en_EN":
                req.getSession().setAttribute("language", "en_US");
                req.getSession().setAttribute("enButton", "btn btn-warning btn-sm");
                req.getSession().setAttribute("roButton", "btn btn-outline-secondary btn-sm");
                req.getSession().setAttribute("deButton", "btn btn-outline-secondary btn-sm");
                break;
            case "ro_RO":
                req.getSession().setAttribute("language", "ro_RO");
                req.getSession().setAttribute("roButton", "btn btn-warning btn-sm");
                req.getSession().setAttribute("enButton", "btn btn-outline-secondary btn-sm");
                req.getSession().setAttribute("deButton", "btn btn-outline-secondary btn-sm");
                break;
            case "de_DE":
                req.getSession().setAttribute("language", "de_DE");
                req.getSession().setAttribute("deButton", "btn btn-warning btn-sm");
                req.getSession().setAttribute("roButton", "btn btn-outline-secondary btn-sm");
                req.getSession().setAttribute("enButton", "btn btn-outline-secondary btn-sm");
                break;
        }
        resp.sendRedirect("home");
    }
}
