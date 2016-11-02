<div class="col-lg-offset-4 col-sm-4">
    <form method="post" action="/deconnexion" class="well">
        <legend>Déconnexion</legend>

        <% if (request.getAttribute("erreur") != null) { %>
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Erreur! </strong><%= request.getAttribute("erreur") %>
        </div>
        <% } %>

        <label for="idSubmit">Voulez-vous vraiment vous déconnecter ?</label>
        <input type="submit" value="Déconnexion" id="idSubmit" class="btn btn-primary"/>
    </form>
</div>