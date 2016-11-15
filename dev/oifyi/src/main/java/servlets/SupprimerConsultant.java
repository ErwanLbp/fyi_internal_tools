package servlets;

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
 * <h1>servlets SupprimerConsultant</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class SupprimerConsultant extends HttpServlet {

    private String url_page_listing_consultant = MappingUrlFichierDAO.getMuf("consultant", "list").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    private int id_consultant;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(url_page_listing_consultant).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("consultantConnecte") == null)
            resp.sendRedirect(url_page_accueil); // On redirige vers la page d'accueil si un utilisateur n'est pas déjà connecté

        // Récupération des champs du formulaire
        String erreur = recuperationChampsForm(req);

        // Si la récupération s'est bien passé, on valide les champs
        if (erreur == null) erreur = validationChamps();

        // Si les champs sont valides, on sauvegarde dans la base de données
        if (erreur == null) erreur = sauvegardeDB();

        // En cas d'erreur on renvoi sur la page, avec l'erreur
        // Si il n'y a pas d'erreur on redirige vers la page sans l'erreur
        if (erreur == null)
            resp.sendRedirect(url_page_listing_consultant);
        else {
            req.setAttribute("erreur", erreur);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url_page_listing_consultant);
            dispatcher.forward(req, resp);
        }
    }

    private String recuperationChampsForm(HttpServletRequest req) {
        try {
            id_consultant = Integer.parseInt(req.getParameter("idConsultant"));
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la récupération des champs";
        }
        return null;
    }

    private String validationChamps() {
        if (!ConsultantDAO.isInDB(id_consultant)) return "L'id du consultant est inconnu";
        return null;
    }

    private String sauvegardeDB() {



        return "Fonctionnalité pas encore implémentée";
    }
}
