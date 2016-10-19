package servlets;

import dao.ConsultantDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h1>servlets Connexion</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 18-10-2016
 */
public class Connexion extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url;
        String password = req.getParameter("password");
        String login = req.getParameter("login");

        if ((login.equals("") || password.equals("")) || !ConsultantDAO.checkLoginPassword(login, password)) {
            req.setAttribute("erreur", "Le login et/ou le password n'est pas correct");
            url = "/index.jsp?page=connexion&mode=view";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(req, resp);
        } else {
            url = "?page=accueil&mode=view";
            resp.sendRedirect(url);
        }

    }
}
