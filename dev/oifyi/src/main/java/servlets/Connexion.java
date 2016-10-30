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
 * <h1>servlets Connexion</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 18-10-2016
 */
public class Connexion extends HttpServlet {

    private String url_page_connexion = MappingUrlFichierDAO.getMuf("profil", "connexion").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(url_page_connexion).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("consultantConnecte") != null)
            resp.sendRedirect(url_page_accueil); // On redirige vers la page d'accueil si un utilisateur est déjà connecté

        // Récupération des champs du formulaire
        String password = req.getParameter("password");
        String login = req.getParameter("login");

        // Invalide si champs vides ou introuvable dans la BDD
        if ((login.equals("") || password.equals("")) || !ConsultantDAO.checkLoginPassword(login, password)) {
            req.setAttribute("erreur", "Le login et/ou le password n'est pas correct");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url_page_connexion); // On renvoi vers la page de connexion
            dispatcher.forward(req, resp);
        }

        // Si les identifiants sont valides
        Consultant consultantConnecte = ConsultantDAO.get(login); // On récupère le consultant associé
        if (session.getAttribute("consultantConnecte") == null) // On l'ajoute à la session
            session.setAttribute("consultantConnecte", consultantConnecte);

        resp.sendRedirect(url_page_accueil); // On redirige vers la page d'accueil
    }
}
