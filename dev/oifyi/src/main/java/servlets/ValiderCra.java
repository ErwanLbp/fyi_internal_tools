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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class ValiderCra extends HttpServlet {

    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();
    private String url_page_list_cra = MappingUrlFichierDAO.getMuf("cra", "validation").formerUrl();

    private Date moisAnnee;
    private Map<Integer, Integer> statusParId;

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
            resp.sendRedirect(url_page_list_cra + "&moisAnneeCourant=" + new SimpleDateFormat("yyyy-MM").format(moisAnnee));
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

    private String recuperationChampsForm(HttpServletRequest req) {
        statusParId = new HashMap<>();
        try {
            String[] idCraMois = req.getParameterValues("idCraMois");
            for (String s : idCraMois) {
                int idCMTmp = Integer.parseInt(s);
                int idStatusCraTmp = Integer.parseInt(req.getParameter("statusCra_" + idCMTmp));
                statusParId.put(idCMTmp, idStatusCraTmp);
            }
        } catch (Exception e) {
            return "Erreur lors de la récupération des champs statusCra ou id_cra_mois";
        }

        try { //Récupération du mois courant en timeMillis
            long lMoisAnnee = Long.parseLong(req.getParameter("moisAnnee"));
            moisAnnee = new Date(lMoisAnnee);
        } catch (Exception e) {
            return "La date envoyée n'a pas pu être récupérée";
        }
        return null;
    }

    private String validationChamps() {
        Iterator<Map.Entry<Integer, Integer>> it = statusParId.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            CraMois craMoisDansDB = CraMoisDAO.get(entry.getKey());
            if (craMoisDansDB == null)
                return "L'id_cra_mois " + entry.getKey() + " est inconnu dans la base de données";
            if (craMoisDansDB.getStatus_cra_id() == entry.getValue())
                it.remove();
            else if (craMoisDansDB.getStatus_cra_id() != 2 && craMoisDansDB.getStatus_cra_id() != 4)
                return "Le cra_mois " + entry.getKey() + " ne peut pas changer de statut";
            else if (craMoisDansDB.getStatus_cra_id() == 4 && entry.getValue() != 3)
                return "Le cra_mois " + entry.getKey() + " ne peut qu'etre soit <i>A modifier</i> ou <i>Validé</i>";
        }
        return null;
    }

    private String sauvegardeDB() {
        boolean toutVaBien = true;
        for (Map.Entry<Integer, Integer> cmAction : statusParId.entrySet()) {
            toutVaBien = toutVaBien && CraMoisDAO.setStatus(cmAction.getKey(), cmAction.getValue());
        }
        if (!toutVaBien)
            return "Erreur lors de la modification d'un ou des status du mois de cra";

        return null;
    }
}
