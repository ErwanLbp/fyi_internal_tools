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
public class NewConsultant extends HttpServlet {

    private String url_page_new_consultant = MappingUrlFichierDAO.getMuf("consultant", "new").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    private String nom;
    private String prenom;
    private String username;
    private String password;
    private int role;

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
        String erreur = null;
        if (!recuperationChampsForm(req))
            erreur = "Erreur lors de la récupération des champs";

        // Il y a eu un problème si la récupération s'est mal passée
        // ou si la fonction de validation des champs échoue
        if (erreur != null || (erreur = validationChamps()) != null) { // Fonction test validités champs
            req.setAttribute("erreur", erreur); //FIXME Probleme : l'erreur ne s'affiche pas
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url_page_new_consultant); // On renvoie vers la page avec l'erreur
            dispatcher.forward(req, resp);
        }

        // Si les champs sont valides
        // On insère le nouveau consultant
        Consultant consultantCree = new Consultant(nom, prenom, username, password, role);
        if (!ConsultantDAO.insert(consultantCree))
            url_page_accueil += "&erreur=Echec insertion";

        resp.sendRedirect(url_page_accueil); // On redirige vers la page d'accueil
    }

    private boolean recuperationChampsForm(HttpServletRequest req) {
        try {
            nom = req.getParameter("nom");
            prenom = req.getParameter("prenom");
            username = req.getParameter("username");
            password = req.getParameter("password");
            role = Integer.parseInt(req.getParameter("role"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private String validationChamps() {
        if (nom.equals("")) return "Le champ nom n'est pas rempli";
        if (prenom.equals("")) return "Le champ prenom n'est pas rempli";
        if (username.equals("")) return "Le champ username n'est pas rempli";
        if (password.equals("")) return "Le champ password n'est pas rempli";
        if (!RoleDAO.isInDB(role)) return "La valeur du champ role est inconnue";
        return null;
    }

}
