<%@ page import="common.Consultant" %>

<%--Si un utilisateur est déjà connecté on le redirige vers la page d'accueil--%>
<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>

<div class="col-lg-offset-4 col-sm-4">
    <form method="post" action="/oifyi/connexion" class="well">
        <fieldset <%= consultantConnecte != null ? "disabled" : "" %>>
            <legend>Connexion</legend>

            <% if (request.getAttribute("erreur") != null) { %>
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <strong>Erreur! </strong><%= request.getAttribute("erreur") %>
            </div>
            <% } else if (consultantConnecte != null) { %>
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <strong>Erreur ! </strong><%= "Un utilisateur est déjà connecté" %>
            </div>
            <% } else { %>
            <div class="alert alert-info alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <strong>Info ! </strong><%= "Remplissez tous les champs" %>
            </div>
            <% } %>

            <div class="form-group">
                <label for="idLogin">Login:<input id="idLogin" type="text" name="login" class="form-control" align="left" accesskey="l" required/></label>
            </div>
            <div class="form-group">
                <label for="idPassword">Password:<input id="idPassword" type="password" name="password" class="form-control" align="left" accesskey="p" required/></label>
            </div>
            <input type="submit" value="Connexion" class="btn btn-primary"/>
        </fieldset>
    </form>
</div>