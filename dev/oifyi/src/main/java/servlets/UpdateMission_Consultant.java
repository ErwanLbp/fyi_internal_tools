package servlets;

import common.Mission;
import dao.AbsenceDAO;
import dao.MappingUrlFichierDAO;
import dao.MissionDAO;

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
public class UpdateMission_Consultant extends HttpServlet {

    private String url_page_update_mission_consultant = MappingUrlFichierDAO.getMuf("mission_consutant", "update").formerUrl();
    private String url_page_listing_mission_consultant = MappingUrlFichierDAO.getMuf("mission_consultant", "list").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    private int id_mission;
    private String nom;
    private String num_contrat;
    private int id_client;
    private Date date_deb;
    private Date date_fin;
    private int tjm;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(url_page_accueil).forward(req, resp);
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
        // Si il n'y a pas d'erreur on redirige vers l'accueil
        if (erreur == null)
            resp.sendRedirect(url_page_listing_missions);
        else {
            req.setAttribute("erreur", erreur);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url_page_update_mission);
            dispatcher.forward(req, resp);
        }
    }

    private String recuperationChampsForm(HttpServletRequest req) {
        try {
            String sIdMission = req.getParameter("idMission");
            // Si on ne recoit pas d'id absence, on le met à -1 pour la considérer vide
            if (sIdMission == null || sIdMission.isEmpty())
                id_mission = -1;
            else
                id_mission = Integer.parseInt(sIdMission);

            nom = req.getParameter("nom");
            num_contrat = req.getParameter("num_contrat");

            id_client = Integer.parseInt(req.getParameter("id_client"));

            date_deb = Date.valueOf(req.getParameter("date_deb"));
            date_fin = Date.valueOf(req.getParameter("date_fin"));
            tjm = Integer.parseInt(req.getParameter("tjm"));

            String sTJM = req.getParameter("tjm");
            if (sTJM == null || sTJM.isEmpty())
                tjm = -1;
            else
                tjm = Integer.parseInt(sTJM);

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la récupération des champs";
        }
        return null;
    }

    private String validationChamps() {
        if (id_mission != -1 && !AbsenceDAO.isInDB(id_mission)) return "L'id de la mission à mettre à jour est inconnu";
        if (nom.equals("")) return "Le champ nom n'est pas rempli";
        if (num_contrat.equals("")) return "Le champ numero du contrat n'est pas rempli";
        if (id_client == -1) return "Le champ idClient a un problème";
        if (date_deb.equals("")) return "Le champ date début n'est pas rempli";
        if (date_fin.equals("")) return "Le champ date fin n'est pas rempli";
        if (tjm == -1) return "Le champ TJM n'est pas rempli.";
        if (tjm < 0) return "Le TJM ne peut pas être négatif";
        return null;
    }

    private String sauvegardeDB() {
        Mission missionCree = new Mission(nom,num_contrat,id_client,date_deb,date_fin,tjm);

        if (id_mission == -1) { // On insère le nouveau consultant dans le cas d'un insert
            if (!MissionDAO.insert(missionCree))
                return "Echec de l'insertion de la nouvelle mission";
        } else { // On update le consultant dans le cas d'un update
            if (!MissionDAO.update(missionCree))
                return "Echec de la mise à jour de la mission";
        }
        return null;
    }
}
