<%@ page import="common.Consultant" %>

<% Consultant consultantConnecte = (Consultant) session.getAttribute("consultantConnecte"); %>

<div class="col-lg-12">
    <div class="input-group col-lg-2">
        <span class="input-group-addon"><input type="checkbox" id="activerForm" onclick="document.getElementById('fieldsetFormUpdate').disabled = !document.getElementById('activerForm').checked"/></span>
        <input type="text" value="Modifier le profil" class="form-control" readonly/>
    </div>

    <form method="post" action="/oifyi/update_profil_consultant" class="well">
        <fieldset id="fieldsetFormUpdate" disabled>
            <legend>
                Page de profil de <%=consultantConnecte.getUsername()%> -- <%=consultantConnecte.getPrenom() + " " + consultantConnecte.getNom()%>
            </legend>

            <% if (request.getAttribute("erreur") != null) { %>
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <strong>Erreur! </strong><%= request.getAttribute("erreur") %>
            </div>
            <% } %>

            <input type="hidden" name="id_consultant" value="<%=consultantConnecte.getId()%>"/>

            <div class="form-group">
                <label for="idPassword">Nouveau mot de passe : </label>
                <input id="idPassword" type="password" name="password" class="form-control" value="<%=consultantConnecte!=null ? consultantConnecte.getPassword() : ""%>"/>
            </div>

            <input type="submit" value="Envoyer" class="btn btn-primary"/>
        </fieldset>
    </form>
</div>
