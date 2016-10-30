package servlets;

import common.Consultant;
import common.Mission;
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
import java.util.List;

/**
 * <h1>servlets SaisieCra</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class SaisieCra extends HttpServlet {

    private String url_page_cra = MappingUrlFichierDAO.getMuf("cra", "saisie").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();
    private String url_page_vue_cra = MappingUrlFichierDAO.getMuf("cra", "view").formerUrl();

    private Date moisAnnee;

    private int[][] joursCra;
    private List<Mission> missions;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(url_page_cra).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Récupération de la variable de session du consultant et redirection si personne est connecté
        HttpSession session = req.getSession();
        Consultant consultantConnecte = (Consultant) session.getAttribute("consultantConnecte");
        if (consultantConnecte == null) {
            resp.sendRedirect(url_page_accueil); // On redirige vers la page d'accueil si un utilisateur est déjà connecté
            return;
        }

        // Récupération des champs du formulaire
        String erreur = null;
        if (!recuperationChampsForm(req, consultantConnecte))
            erreur = "Erreur lors de la récupération des champs";

        // Il y a eu un problème si la récupération s'est mal passée
        // ou si la fonction de validation des champs échoue
        if (erreur != null || (erreur = validationChamps()) != null) { // Fonction test validités champs
            req.setAttribute("erreur", erreur);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url_page_cra); // On renvoi vers la page de saisie du cra
            dispatcher.forward(req, resp);
            return;
        }

        // Si les champs sont valides
        //Pour chaque champsdu tableau joursCra, l'insérer en base
        sauvegardeCraBDD();

        //Rediriger vers la page de vue du cra
        resp.sendRedirect(url_page_vue_cra);
    }

    //TODO Javadoc : SaisieCra
    private boolean recuperationChampsForm(HttpServletRequest req, Consultant consultantConnecte) {
        try { //Récupération du mois courant
            moisAnnee = Date.valueOf(req.getParameter("moisAnnee") + "-01"); //Format : yyyy-mm-dd
        } catch (IllegalArgumentException iae) {
            return false;
        }

        // Récupération des champs journées autres que astreintes
        missions = MissionDAO.getMissionsDuConsultant(consultantConnecte.getId(), moisAnnee);
        joursCra = new int[31][missions.size()];

        // Pour chaque champ de la forme 'M_<idMission>_<jour>(_<typeJour>)'
        for (int i = 0; i < joursCra.length; i++) {
            for (int j = 0; j < missions.size(); j++) {
                try {
                    joursCra[i][j] = Integer.parseInt(req.getParameter("M_" + missions.get(j).getId_mission() + "_" + i));
                } catch (NumberFormatException nfe) {
                    joursCra[i][j] = 0;
                }
            }
        }

        // Récupération des champs d'astreintes
        // Les astreintes de jour seront de la forme 'AS_J_<jour>'
        // Les astreintes de nuit seront de la forme 'AS_N_<jour>'
        // Les astreintes intervention seront de la forme 'AS_I_<jour>'


        return true;
    }

    //TODO Javadoc : SaisieCra
    private String validationChamps() {

        // Vérifications s'il y a bien un total de 1j saisi sur chaque jour
        for (int i = 0; i < joursCra.length; i++) {
            double sommeJour = 0;
            for (int j = 0; j < missions.size(); j++) {
                if (joursCra[i][j] > 1)
                    return "Le jour " + i + " sur la mission " + missions.get(j).getNom() + " doit être <= 1.";
                if (joursCra[i][j] < 0)
                    return "Le jour " + i + " sur la mission " + missions.get(j).getNom() + " doit être >= 0.";
                sommeJour += joursCra[i][j];
            }
            if (sommeJour < 1)
                return "Le jour " + i + " n'est pas complet.";
            if (sommeJour > 1)
                return "Le jour " + i + " est trop rempli.";
        }

        return null;
    }

    //TODO Javadoc : SaisieCra
    private void sauvegardeCraBDD() {
        //Insérer ligne cra_mois
        for (int i = 0; i < joursCra.length; i++) {
            for (int j = 0; j < missions.size(); j++) {
                // Si la case vaut pas 0, insérer une ligne dans cra_jour
            }
        }
    }

}
