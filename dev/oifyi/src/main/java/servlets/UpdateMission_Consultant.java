package servlets;

import common.Mission;
import common.Mission_Consultant;
import dao.AbsenceDAO;
import dao.MappingUrlFichierDAO;
import dao.MissionDAO;
import dao.Mission_ConsultantDAO;

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

    private String url_page_listing_mission_consultant = MappingUrlFichierDAO.getMuf("mission_consultant", "list").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    private int id_mission;
    private int id_consultant;
    private float prix;

    private String sIdConsultant;
    private String sPrix;


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
            resp.sendRedirect(url_page_listing_mission_consultant);
        else {
            req.setAttribute("erreur", erreur);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url_page_listing_mission_consultant);
            dispatcher.forward(req, resp);
        }
    }

    private String recuperationChampsForm(HttpServletRequest req) {
        try {

            id_mission = Integer.parseInt(req.getParameter("id_mission"));
            sIdConsultant = req.getParameter("id_consultant");
            if (!sIdConsultant.isEmpty()){
                id_consultant = Integer.parseInt(req.getParameter("id_consultant"));
            }
            else id_consultant=-1;
            sPrix = req.getParameter("prix");
            if (!sPrix.isEmpty()){
                prix = Float.parseFloat(req.getParameter("prix"));
            }
            else prix=-1;


        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la récupération des champs";
        }
        return null;
    }

    private String validationChamps() {
        if (id_mission != -1 && !AbsenceDAO.isInDB(id_mission)) return "L'id de la mission à mettre à jour est inconnu";
        if (id_consultant==-1) return "Le champ consultant n'est pas rempli";
        if (prix==-1) return "Le champ prix n'est pas rempli";
        return null;
    }

    private String sauvegardeDB() {
        Mission_Consultant mission_consultantCree = new Mission_Consultant(id_mission,id_consultant,prix);
        Mission_ConsultantDAO.insert(mission_consultantCree);
        return null;
    }
}
