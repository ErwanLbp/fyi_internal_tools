package servlets;

import common.Consultant;
import common.MappingUrlFichier;
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

    String url_page = MappingUrlFichierDAO.getMuf("accueil","connexion").formerUrl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("index.jsp?page=accueil&mode=connexion").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url;
        // Récupération des champs du formulaire
        String password = req.getParameter("password");
        String login = req.getParameter("login");

        // Invalide si champs vides ou introuvable dans la BDD
        if ((login.equals("") || password.equals("")) || !ConsultantDAO.checkLoginPassword(login, password)) {
            req.setAttribute("erreur", "Le login et/ou le password n'est pas correct");
            url = MappingUrlFichierDAO.getMuf("accueil", "connexion").formerUrl();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url); // On renvoi vers la page de connexion
            dispatcher.forward(req, resp);
        }

        // Si les identifiants sont valides
        Consultant consultantConnecte = ConsultantDAO.getByLogin(login); // On récupère le consultant associé
        HttpSession session = req.getSession();
        if (session.getAttribute("consultantConnecte") == null) // On l'ajoute à la session
            session.setAttribute("consultantConnecte", consultantConnecte);
        //FIXME Faire qqc si un consultant est déjà connecté? rediriger vers la déconnexion ou afficher un message d'erreur

        url = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl(); // On redirige vers la page d'accueil
        resp.sendRedirect(url);
    }
}
