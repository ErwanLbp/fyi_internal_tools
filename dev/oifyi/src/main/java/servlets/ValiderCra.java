package servlets;

import common.CraMois;
import dao.CraMoisDAO;
import dao.MappingUrlFichierDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>servlets ValiderCra</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class ValiderCra extends HttpServlet {

    private String url_page_cra = MappingUrlFichierDAO.getMuf("cra", "saisie").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();
    private String url_page_list_cra = MappingUrlFichierDAO.getMuf("cra", "validation").formerUrl();

    private Date moisAnnee;
    private Map<Integer, String> actionParId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(url_page_list_cra).forward(req, resp);
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
            resp.sendRedirect(url_page_cra);
        else {
            req.setAttribute("erreur", erreur);
            RequestDispatcher dispatcher;
            if (moisAnnee != null)
                dispatcher = getServletContext().getRequestDispatcher(url_page_list_cra + "&moisAnneeCourant=" + new SimpleDateFormat("yyyy-MM").format(moisAnnee));
            else
                dispatcher = getServletContext().getRequestDispatcher(url_page_list_cra);
            dispatcher.forward(req, resp);
        }
    }

    //TODO Javadoc : SaisieCra
    private String recuperationChampsForm(HttpServletRequest req) {
        actionParId = new HashMap<>();
        try {
            String[] idCraMois = req.getParameterValues("idCraMois");
            for (String s : idCraMois) {
                int idTmp = Integer.parseInt(s);
                actionParId.put(idTmp, req.getParameter("action_" + idTmp));
            }
        } catch (Exception e) {
            return "Erreur lors de la récupération des champs action ou id_cra_mois";
        }

        try { //Récupération du mois courant en timeMillis
            long lMoisAnnee = Long.parseLong(req.getParameter("moisAnnee"));
            moisAnnee = new Date(lMoisAnnee);
        } catch (Exception e) {
            return "La date envoyé n'a pas pu être récupérée";
        }
        return null;
    }

    //TODO Javadoc : SaisieCra
    private String validationChamps() {
        List<String> validator = new ArrayList<>();
        validator.add("none");
        validator.add("valider");
        validator.add("modifier");
        for (Map.Entry<Integer, String> cmAction : actionParId.entrySet()) {
            if (!validator.contains(cmAction.getValue()))
                return "L'action demandée pour l'id_cra_mois " + cmAction.getKey() + " n'est pas possible";
            CraMois cm = CraMoisDAO.get(cmAction.getKey());
            if (cm == null)
                return "Le cra_mois " + cmAction.getKey() + " n'existe pas";
            int cm_status = cm.getStatus_cra_id();
            if (cm_status != 2 && cm_status != 4)
                return "Le cra_mois " + cmAction.getValue() + " ne peut pas changer de statut";
        }
        return null;
    }

    private String sauvegardeDB() {
        boolean toutVaBien = true;
        for (Map.Entry<Integer, String> cmAction : actionParId.entrySet()) {
            switch (cmAction.getValue()) {
                case "valider":
                    toutVaBien = CraMoisDAO.setStatus(cmAction.getKey(), 3);
                    break;
                case "modifier":
                    toutVaBien = CraMoisDAO.setStatus(cmAction.getKey(), 4);
                    break;
            }
        }
        if (!toutVaBien)
            return "Erreur lors de la modification d'un ou des status du mois de cra";

        return null;
    }
}
