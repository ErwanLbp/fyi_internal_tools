package servlets;

import dao.ConnexionDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

        String url = getServletContext().getContextPath();
        boolean valide = true;

        String password = req.getParameter("password");
        if (password.equals("")) {
            valide = false;
            req.setAttribute("erreur", "Le password n'est pas rempli");
        }

        String login = req.getParameter("login");
        if (login.equals("")) {
            valide = false;
            req.setAttribute("erreur", "Le login n'est pas rempli");
        }

        if (valide && ConnexionDAO.checkLoginPassword(login, password)) {
            resp.sendRedirect(url + "?page=accueil");
        } else {
            getServletContext().getRequestDispatcher(url).forward(req, resp);
        }

        resp.sendRedirect(url);
    }
}
