package servlets;

import common.Absence;
import dao.AbsenceDAO;
import dao.MappingUrlFichierDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

/**
 * <h1>${PACKAGE_NAME} ${NAME}</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 27-10-2016
 */
public class UpdateAbsence extends HttpServlet {

    private String url_page_update_absence = MappingUrlFichierDAO.getMuf("absences", "update").formerUrl();
    private String url_page_list_absence = MappingUrlFichierDAO.getMuf("absences", "list").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    private int id_absence;
    private int id_consultant;
    private int id_type_absence;
    private String plus_precision;
    private Date date_deb;
    private Date date_fin;
    private int id_statut_absence;
    private String commentaire;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(url_page_update_absence).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("consultantConnecte") == null) {
            resp.sendRedirect(url_page_accueil); // On redirige vers la page d'accueil si un utilisateur n'est pas déjà connecté
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
            resp.sendRedirect(url_page_list_absence);
        else {
            req.setAttribute("erreur", erreur);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url_page_update_absence);
            dispatcher.forward(req, resp);
        }
    }

    private String recuperationChampsForm(HttpServletRequest req) {
        try {
            String sIdAbsence = req.getParameter("id_absence");
            // Si on ne recoit pas d'id absence, on le met à -1 pour la considérer vide
            if (sIdAbsence == null || sIdAbsence.isEmpty())
                id_absence = -1;
            else
                id_absence = Integer.parseInt(sIdAbsence);
            id_consultant = Integer.parseInt(req.getParameter("id_consultant"));
            id_type_absence = Integer.parseInt(req.getParameter("id_type_absence"));
            plus_precision = req.getParameter("plus_precision");
            date_deb = Date.valueOf(req.getParameter("date_deb"));
            date_fin = Date.valueOf(req.getParameter("date_fin"));
            id_statut_absence = Integer.parseInt(req.getParameter("id_statut_absence"));
            commentaire = req.getParameter("commentaire");
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la récupération des champs";
        }
        return null;
    }

    private String validationChamps() {
        if (id_absence != -1 && !AbsenceDAO.isInDB(id_absence)) return "L'id de l'absence à mettre à jour est inconnu";
        if (id_type_absence == 0) return "Le champ type absence n'est pas rempli";
        if (date_deb.equals("")) return "Le champ date début n'est pas rempli";
        if (date_fin.equals("")) return "Le champ date fin n'est pas rempli";
        return null;
    }

    private String sauvegardeDB() {
        Absence absenceCree = new Absence(id_absence, id_consultant, id_type_absence, plus_precision, date_deb, date_fin, id_statut_absence, commentaire);

        if (id_absence == -1) { // On insère le nouveau consultant dans le cas d'un insert
            if (!AbsenceDAO.insert(absenceCree))
                return "Echec de l'insertion de la nouvelle absence";
        } else { // On update le consultant dans le cas d'un update
            if (!AbsenceDAO.update(absenceCree))
                return "Echec de la mise à jour de l'absence";
        }
        return null;
    }
}
