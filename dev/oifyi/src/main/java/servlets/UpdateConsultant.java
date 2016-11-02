package servlets;

import common.Consultant;
import dao.ConsultantDAO;
import dao.MappingUrlFichierDAO;
import dao.RoleDAO;

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
public class UpdateConsultant extends HttpServlet {

    private String url_page_saisie_consultant = MappingUrlFichierDAO.getMuf("consultant", "update").formerUrl();
    private String url_page_list_consultant = MappingUrlFichierDAO.getMuf("consultant", "list").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    private int id_consultant;
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private int role;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(getServletContext().getContextPath() + url_page_accueil).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("consultantConnecte") == null) {
            resp.sendRedirect(getServletContext().getContextPath() + url_page_accueil); // On redirige vers la page d'accueil si un utilisateur n'est pas déjà connecté
            return;
        }

        // Récupération des champs du formulaire
        String erreur = recuperationChampsForm(req);

        // Si la récupération s'est bien passé, on valide les champs
        if (erreur == null) erreur = validationChamps();

        // Si les champs sont valides, on sauvegarde dans la base de données
        if (erreur == null) erreur = sauvegardeDB();

        // En cas d'erreur on renvoi sur la page, avec l'erreur
        // Si il n'y a pas d'erreur on redirige vers l'accueil
        if (erreur == null)
            resp.sendRedirect(getServletContext().getContextPath() + url_page_list_consultant);
        else {
            req.setAttribute("erreur", erreur);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(getServletContext().getContextPath() + url_page_saisie_consultant);
            dispatcher.forward(req, resp);
        }
    }

    private String recuperationChampsForm(HttpServletRequest req) {
        try {
            String sIdConsultant = req.getParameter("id_consultant");
            // Si on ne recoit pas d'id consultant, on le met à -1 pour le considérer vide
            if (sIdConsultant == null || sIdConsultant.isEmpty())
                id_consultant = -1;
            else
                id_consultant = Integer.parseInt(sIdConsultant);
            nom = req.getParameter("nom");
            prenom = req.getParameter("prenom");
            username = req.getParameter("username");
            password = req.getParameter("password");
            role = Integer.parseInt(req.getParameter("role"));
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la récupération des champs";
        }
        return null;
    }

    private String validationChamps() {
        if (id_consultant != -1 && !ConsultantDAO.isInDB(id_consultant)) return "L'id du consultant à mettre à jour est inconnu";
        if (nom.equals("")) return "Le champ nom n'est pas rempli";
        if (prenom.equals("")) return "Le champ prenom n'est pas rempli";
        if (username.equals("")) return "Le champ username n'est pas rempli";
        if (password.equals("")) return "Le champ password n'est pas rempli";
        if (!RoleDAO.isInDB(role)) return "La valeur du champ role est inconnue";
        return null;
    }

    private String sauvegardeDB() {
        Consultant consultantCree = new Consultant(id_consultant, nom, prenom, username, password, role);

        if (id_consultant == -1) { // On insère le nouveau consultant dans le cas d'un insert
            if (!ConsultantDAO.insert(consultantCree))
                return "Echec de l'insertion du nouveau consultant";
        } else { // On update le consultant dans le cas d'un update
            if (!ConsultantDAO.update(consultantCree))
                return "Echec de la mise à jour du consultant";
        }
        return null;
    }
}
