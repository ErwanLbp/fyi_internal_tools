<%@ page import="dao.MappingUrlFichierDAO" %>

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