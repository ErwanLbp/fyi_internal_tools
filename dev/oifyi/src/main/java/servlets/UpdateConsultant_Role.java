package servlets;

import common.Mission_Consultant;
import dao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <h1>${PACKAGE_NAME} ${NAME}</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 27-10-2016
 */
public class UpdateConsultant_Role extends HttpServlet {

    private String url_page_listing_consultant_role = MappingUrlFichierDAO.getMuf("consultant", "list_assignations").formerUrl() + "&idConsultant=";
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    private int id_consultant;
    private int id_role;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(getServletContext().getContextPath() + url_page_accueil).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("consultantConnecte") == null)
            resp.sendRedirect(getServletContext().getContextPath() + url_page_accueil); // On redirige vers la page d'accueil si un utilisateur n'est pas déjà connecté

        // Récupération des champs du formulaire
        String erreur = recuperationChampsForm(req);

        // Si la récupération s'est bien passé, on valide les champs
        if (erreur == null) erreur = validationChamps();

        // Si les champs sont valides, on sauvegarde dans la base de données
        if (erreur == null) erreur = sauvegardeDB();

        // En cas d'erreur on renvoi sur la page, avec l'erreur
        // Si il n'y a pas d'erreur on redirige vers la page sans l'erreur
        if (erreur == null)
            resp.sendRedirect(getServletContext().getContextPath() + url_page_listing_consultant_role + String.valueOf(id_consultant));
        else {
            req.setAttribute("erreur", erreur);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(getServletContext().getContextPath() + url_page_listing_consultant_role + String.valueOf(id_consultant));
            dispatcher.forward(req, resp);
        }
    }

    private String recuperationChampsForm(HttpServletRequest req) {
        try {

            id_consultant = Integer.parseInt(req.getParameter("id_consultant"));
            id_role = Integer.parseInt(req.getParameter("id_role"));

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la récupération des champs";
        }
        return null;
    }

    private String validationChamps() {
        if (!ConsultantDAO.isInDB(id_consultant)) return "L'id du consultant est inconnu";
        if (!RoleDAO.isInDB(id_role)) return "L'id du role est inconnu";
        if (Consultant_RoleDAO.isInDB(id_consultant, id_role)) return "Le role est déjà associé au consultant";
        return null;
    }

    private String sauvegardeDB() {
        Mission_Consultant mission_consultantCree = new Mission_Consultant(id_consultant, id_role);
        if (!Mission_ConsultantDAO.insert(mission_consultantCree)) {
            return "Echec de l'insertion de la nouvelle mission_consultant";
        }
        return null;

    }
}
