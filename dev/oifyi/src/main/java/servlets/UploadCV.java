package servlets;

import dao.MappingUrlFichierDAO;
import persistence.UploadFileOnServer;

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
public class UploadCV extends HttpServlet {

    private String url_page_profil = MappingUrlFichierDAO.getMuf("profil", "update").formerUrl();
    private String url_page_accueil = MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl();

    private String filePath;

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
        String erreur = UploadFileOnServer.UploadCV(req, filePath);

        // En cas d'erreur on renvoi sur la page, avec l'erreur
        // Si il n'y a pas d'erreur on redirige vers l'accueil
        if (erreur == null)
            resp.sendRedirect(url_page_profil);
        else {
            req.setAttribute("erreurCV", erreur);
            RequestDispatcher dispatcher;
            dispatcher = getServletContext().getRequestDispatcher(url_page_profil);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void init() throws ServletException {
        filePath = getServletContext().getInitParameter("file-upload");
    }
}
