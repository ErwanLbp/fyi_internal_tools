package servlets;

import common.Consultant;
import dao.ConsultantDAO;
import dao.MappingUrlFichierDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <h1>servlets Deconnexion</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class Deconnexion extends HttpServlet {

    private String url_page_deconnexion = MappingUrlFichierDAO.getMuf("profil", "deconnexion").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(url_page_deconnexion).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        try{
            session.invalidate();
        }catch (IllegalStateException ise){ // Si la session est déjà détruite
            req.setAttribute("erreur", "La session est déjà détruite");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url_page_deconnexion); // On renvoi vers la page de deconnexion
            dispatcher.forward(req, resp);
        }

        resp.sendRedirect(url_page_accueil); // On redirige vers la page d'accueil
    }
}
