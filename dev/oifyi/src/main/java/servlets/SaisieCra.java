package servlets;

import common.CraJour;
import common.CraMois;
import common.Mission;
import common.StatusCra;
import dao.CraMoisDAO;
import dao.MappingUrlFichierDAO;
import dao.MissionDAO;
import dao.StatusCraDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private String url_page_list_cra = MappingUrlFichierDAO.getMuf("cra", "listing").formerUrl();

    private int consultant_id;
    private Date moisAnnee;
    private List<Mission> missions;
    private double[][] joursCra;
    private double[][] joursAbsence;
    private double[][] joursAstreinte;

    private int jourMaxDuMois;
    private boolean[] auMoinsUnJourTravaille;
    private StatusCra statusCraEnvoye;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(url_page_cra).forward(req, resp);
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
            resp.sendRedirect(url_page_list_cra);
        else {
            req.setAttribute("erreur", erreur);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url_page_cra + "&moisAnneeCourant=" + new SimpleDateFormat("yyyy-MM").format(moisAnnee));
            dispatcher.forward(req, resp);
        }
    }

    //TODO Javadoc : SaisieCra
    private String recuperationChampsForm(HttpServletRequest req) {
        try {
            consultant_id = Integer.parseInt(req.getParameter("consultant_id"));
        } catch (Exception e) {
            return "L'id du consultant connecté n'est pas au bon format ou n'a pas été trouvé";
        }

        statusCraEnvoye = StatusCraDAO.get("Envoyé");
        if (statusCraEnvoye == null)
            return "Erreur lors de la récupération du status 'Cra Envoyé'";

        try { //Récupération du mois courant en timeMillis
            long lMoisAnnee = Long.parseLong(req.getParameter("moisAnnee"));
            moisAnnee = new Date(lMoisAnnee);
        } catch (Exception e) {
            return "La date envoyé n'a pas pu être récupérée";
        }

        // Récupération du début et fin des boucles for
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(moisAnnee);
        jourMaxDuMois = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Récupération des champs journées de mission
        missions = MissionDAO.getMissionsDuConsultant(consultant_id, moisAnnee);

        joursCra = new double[jourMaxDuMois][missions.size()];
        joursAbsence = new double[2][jourMaxDuMois];
        joursAstreinte = new double[3][jourMaxDuMois];

        // Pour chaque champ de la forme 'M_<idMission>_<jour>'
        for (int i = 0; i < jourMaxDuMois; i++) {
            for (int j = 0; j < missions.size(); j++) {
                try {
                    joursCra[i][j] = Double.parseDouble(req.getParameter("M_" + missions.get(j).getId_mission() + "_" + i));
                } catch (Exception e) {
                    return "Le jour " + (i + 1) + " sur la mission " + missions.get(j).getNom() + " n'a pas pu être récupéré";
                }
            }
        }

        // Pour chaque champ de la forme 'AB_C/F_<jour>'
        for (int i = 0; i < 2; i++) {
            try { // CONGES
                joursAbsence[i][0] = Double.parseDouble(req.getParameter("AB_C_" + i));
            } catch (Exception e) {
                return "Le jour " + (i + 1) + " sur la ligne des congés n'a pas pu être récupéré";
            }

            try { // FERIES
                joursAbsence[i][1] = Double.parseDouble(req.getParameter("AB_F_" + i));
            } catch (Exception e) {
                return "Le jour " + (i + 1) + " sur la ligne des fériés n'a pas pu être récupéré";
            }
        }

        // Récupération des champs d'astreintes
        // Les astreintes de jour seront de la forme 'AS_J/N/I_<jour>'
        for (int i = 0; i < 3; i++) {
            try { // JOUR
                joursAstreinte[i][0] = Double.parseDouble(req.getParameter("AS_J_" + i));
            } catch (Exception e) {
                return "Le jour " + (i + 1) + " sur la ligne des astreintes de jour n'a pas pu être récupéré";
            }

            try { // NUIT
                joursAstreinte[i][1] = Double.parseDouble(req.getParameter("AS_N_" + i));
            } catch (Exception e) {
                return "Le jour " + (i + 1) + " sur la ligne des astreintes de nuit n'a pas pu être récupéré";
            }

            try { // INTERVENTION
                joursAstreinte[i][2] = Double.parseDouble(req.getParameter("AS_I_" + i));
            } catch (Exception e) {
                return "Le jour " + (i + 1) + " sur la ligne des interventions d'astreintes n'a pas pu être récupéré";
            }
        }

        return null;
    }

    //TODO Javadoc : SaisieCra
    private String validationChamps() {

        auMoinsUnJourTravaille = new boolean[missions.size()];

        // Vérifications s'il y a bien un total de 1j saisi sur chaque jour de mission
        for (int i = 0; i < jourMaxDuMois; i++) {
            double sommeJour = 0;
            for (int j = 0; j < missions.size(); j++) {
                if (joursCra[i][j] > 1)
                    return "Le jour " + (i + 1) + " sur la mission " + missions.get(j).getNom() + " doit être <= 1.";
                if (joursCra[i][j] < 0)
                    return "Le jour " + (i + 1) + " sur la mission " + missions.get(j).getNom() + " doit être >= 0.";
                sommeJour += joursCra[i][j];
                if (joursCra[i][j] > 0) auMoinsUnJourTravaille[j] = true;
            }

            // Vérifications s'il y a bien un total de 0j ou 0.5j ou 1j saisi sur chaque jour d'absence
            if (joursAbsence[0][i] != 1 && joursAbsence[0][i] != 0.5 && joursAbsence[0][i] != 0)
                return "Le jour " + (i + 1) + " sur la ligne des congés doit valoir 1, 0.5, ou 0";
            if (joursAbsence[1][i] != 1 && joursAbsence[1][i] != 0.5 && joursAbsence[1][i] != 0)
                return "Le jour " + (i + 1) + " sur la ligne des férié doit valoir 1, 0.5, ou 0";
            if (joursAbsence[0][i] + joursAbsence[1][i] > 1 || joursAbsence[0][i] + joursAbsence[1][i] < 0)
                return "Le jour " + (i + 1) + " est trop rempli sur les champs d'absences";

            sommeJour += joursAbsence[0][i] + joursAbsence[1][i];

            if (sommeJour != 0 && sommeJour < 1)
                return "Le jour " + (i + 1) + " n'est pas complet.";
            if (sommeJour != 0 && sommeJour > 1)
                return "Le jour " + (i + 1) + " est trop rempli";

            //TODO Vérification sur les astreintes
        }
        return null;
    }

    private String sauvegardeDB() {

        int[] id_cramois = new int[missions.size()];
        List<CraMois> lCraMois = new ArrayList<>();
        Map<CraJour, Integer> mCraJour = new HashMap<>();
        int statusEnvoye = statusCraEnvoye.getId_status_cra();

        // Création de la ligne de cra_mois si au moins un jour est saisi sur la mission
        for (int i = 0; i < missions.size(); i++) {
            if (auMoinsUnJourTravaille[i])
                lCraMois.add(new CraMois(missions.get(i).getId_mission(), consultant_id, moisAnnee, statusEnvoye));
        }

        // Création d'une ligne pour chaque jour dans cra_jour, absence, et astreinte
        for (int i = 0; i < jourMaxDuMois; i++) {

            // Pour les missions
            for (int j = 0; j < missions.size(); j++) {
                // On ne fait l'insertion que pour les jours qui ont été travaillé, donc qui ont une valeur > 0
                if (joursCra[i][j] > 0)
                    mCraJour.put(new CraJour(-1, i + 1, joursCra[i][j]), missions.get(j).getId_mission());
            }

            // TODO Pour les absences

            // TODO Pour les astreintes

        }

        // On lance l'insertion de la transaction
        if (!CraMoisDAO.insertToutMoisTransaction(lCraMois, mCraJour))
            return "Erreur lors de l'insertion du mois de cra";

        return null;
    }
}
