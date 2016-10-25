<div class="col-lg-offset-4 col-sm-4">
    <form method="post" action="/connexion" class="well">
        <legend>Connexion</legend>

        <b>
            <%= request.getParameter("erreur") == null ? "Remplissez tous les champs" : request.getParameter("erreur") %>
        </b>

        <div class="form-group">
            <label for="idLogin">Login:<input id="idLogin" type="text" name="login" class="form-control" align="left" accesskey="l" required/></label>
        </div>
        <div class="form-group">
            <label for="idPassword">Password:<input id="idPassword" type="password" name="password" class="form-control" align="left" accesskey="p" required/></label>
        </div>
        <input type="submit" value="Connexion" class="btn btn-primary"/>
    </form>
</div>