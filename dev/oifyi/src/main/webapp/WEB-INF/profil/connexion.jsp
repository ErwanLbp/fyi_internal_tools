<%@ page import="common.Consultant" %>
<%@ page import="dao.MappingUrlFichierDAO" %>

<%--Si un utilisateur est déjà connecté on le redirige vers la page d'accueil--%>
<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>

<div class="col-lg-offset-4 col-sm-4">
    <form method="post" action="/connexion" class="well">
        <fieldset <%= consultantConnecte != null ? "disabled" : "" %>>
            <legend>Connexion</legend>

            <b>
                <%= request.getAttribute("erreur") == null ?
                        (consultantConnecte != null ? "Un utilisateur est déjà connecté" : "Remplissez tous les champs")
                        : (String) request.getAttribute("erreur") %>
            </b>

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