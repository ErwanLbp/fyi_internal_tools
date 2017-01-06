package servlets;

import common.Consultant;
import dao.ConsultantDAO;
import dao.MappingUrlFichierDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Erwan
 * @version 1.0
 * @since 06-01-2017
 */
public class UpdateProfil extends HttpServlet {

    private String url_page_profil = MappingUrlFichierDAO.getMuf("profil", "update").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    private int idConsultant;
    private String password;
    private Consultant consultDansDB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(url_page_profil).forward(req, resp);
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
        if (erreur == null) erreur = sauvegardeDB(session);

        // En cas d'erreur on renvoi sur la page, avec l'erreur
        // Si il n'y a pas d'erreur on redirige vers l'accueil
        if (erreur == null)
            resp.sendRedirect(url_page_profil);
        else {
            req.setAttribute("erreur", erreur);
            RequestDispatcher dispatcher;
            dispatcher = getServletContext().getRequestDispatcher(url_page_profil);
            dispatcher.forward(req, resp);
        }
    }

    private String recuperationChampsForm(HttpServletRequest req) {
        try {
            idConsultant = Integer.parseInt(req.getParameter("id_consultant"));
        } catch (Exception e) {
            return "L'id du consultant n'a pas pu être récupéré";
        }

        password = req.getParameter("password");

        return null;
    }

    private String validationChamps() {
        if ((consultDansDB = ConsultantDAO.get(idConsultant)) == null) return "L'id du consultant est inconnu dans la base de données";
        if (password == null) return "Le champs password ne doit pas être null";
        return null;
    }

    private String sauvegardeDB(HttpSession session) {

        if (!password.isEmpty())
            consultDansDB.setPassword(password);
        if (!ConsultantDAO.update(consultDansDB)) return "Erreur lors de la mise à jour du consultant";

        session.setAttribute("consultantConnecte", consultDansDB);

        return null;
    }
}
