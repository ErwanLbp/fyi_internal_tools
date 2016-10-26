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
 * Created by eisti on 25/10/16.
 */
public class NewConsultant extends HttpServlet {

    private String url_page_new_consultant = MappingUrlFichierDAO.getMuf("consultant", "new").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(url_page_accueil).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("consultantConnecte") != null)
            resp.sendRedirect(url_page_accueil); // On redirige vers la page d'accueil si un utilisateur est déjà connecté

        // Récupération des champs du formulaire
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        // Invalide si champs vides ou introuvable dans la BDD
        if (nom.equals("") || prenom.equals("") || username.equals("") || password.equals("") || role.equals("")){
            req.setAttribute("erreur", "Un champ est vide."); //FIXME Probleme : l'erreur ne s'affiche pas meme en cas de mauvais identifiants
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url_page_new_consultant); // On renvoie vers la page de connexion
            dispatcher.forward(req, resp);
        }


        // Si les champs sont valides
        Consultant consultantCree= ConsultantDAO.getByLogin(username); // On récupère le consultant associé
        if (session.getAttribute("consultantCree") == null) // On l'ajoute à la session
            session.setAttribute("consultantCree", consultantCree);
        //FIXME Faire qqc si un consultant est déjà connecté? rediriger vers la déconnexion ou afficher un message d'erreur

        resp.sendRedirect(url_page_accueil); // On redirige vers la page d'accueil
    }
}
