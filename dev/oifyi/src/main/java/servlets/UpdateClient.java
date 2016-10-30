package servlets;

import common.Adresse;
import common.Client;
import dao.ClientDAO;
import dao.MappingUrlFichierDAO;

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
public class UpdateClient extends HttpServlet {

    private String url_page_saisie_client = MappingUrlFichierDAO.getMuf("clients", "update").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    private int id_client;
    private String raison_sociale;
    private String forme_juridique;
    private String siret;
    private String num_tva;
    private Adresse adresse;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(url_page_saisie_client).forward(req, resp);
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
            resp.sendRedirect(url_page_accueil);
        else {
            req.setAttribute("erreur", erreur);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url_page_saisie_client);
            dispatcher.forward(req, resp);
        }
    }

    private String recuperationChampsForm(HttpServletRequest req) {
        try {
            String sIdClient = req.getParameter("id_client");
            // Si on ne recoit pas d'id consultant, on le met à -1 pour le considérer vide
            if (sIdClient == null || sIdClient.isEmpty())
                id_client = -1;
            else
                id_client = Integer.parseInt(sIdClient);
            forme_juridique = req.getParameter("forme_juridique");
            raison_sociale = req.getParameter("raison_sociale");
            siret = req.getParameter("siret");
            num_tva = req.getParameter("num_tva");
            adresse = new Adresse(Integer.parseInt(req.getParameter("adresseNumero")), req.getParameter("adresseRue"), Integer.parseInt(req.getParameter("adresseCP")), req.getParameter("adresseVille"));
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la récupération des champs";
        }
        return null;
    }

    private String validationChamps() {
        //if (id_client != -1 && !ClientDAO.isInDB(id_client)) return "L'id du consultant à mettre à jour est inconnu";
        if (raison_sociale.equals("")) return "Le champ nom n'est pas rempli";
        if (forme_juridique.equals("")) return "Le champ prenom n'est pas rempli";
        if (siret.equals("")) return "Le champ username n'est pas rempli";
        if (num_tva.equals("")) return "Le champ password n'est pas rempli";
        //if (!RoleDAO.isInDB(role)) return "La valeur du champ role est inconnue";
        if (String.valueOf(adresse.getNumero()).equals("")) return "Le champ numéro n'est pas rempli";
        if (adresse.getRue().equals("")) return "Le champ rue n'est pas rempli";
        if (String.valueOf(adresse.getCp()).equals("")) return "Le champ code postal n'est pas rempli";
        if (adresse.getVille().equals("")) return "Le champ ville n'est pas rempli";

        return null;
    }

    private String sauvegardeDB() {
        Client clientCree = new Client(id_client, raison_sociale, forme_juridique, siret, num_tva, adresse);

        if (id_client == -1) { // On insère le nouveau consultant dans le cas d'un insert
            if (!ClientDAO.insert(clientCree))
                return "Echec de l'insertion du nouveau client";
        } else { // On update le consultant dans le cas d'un update
            if (!ClientDAO.update(clientCree))
                return "Echec de la mise à jour du consultant";
        }
        return null;
    }
}
