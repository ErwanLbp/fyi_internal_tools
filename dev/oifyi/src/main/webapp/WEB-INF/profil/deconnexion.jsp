<%@ page import="dao.MappingUrlFichierDAO" %>

<%--Si un utilisateur n'est connecté on redirige vers la page d'accueil--%>
<% if (request.getSession().getAttribute("consultantConnecte") == null) response.sendRedirect(MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl()); %>

<div class="col-lg-offset-4 col-sm-4">
    <form method="post" action="/deconnexion" class="well">
        <legend>Déconnexion</legend>

        <b>
            <%= request.getParameter("erreur") == null ? "" : request.getParameter("erreur") %>
        </b>

        <label for="idSubmit">Voulez-vous vraiment vous déconnecter ?</label>
        <input type="submit" value="Déconnexion" id="idSubmit" class="btn btn-primary"/>
    </form>
</div>