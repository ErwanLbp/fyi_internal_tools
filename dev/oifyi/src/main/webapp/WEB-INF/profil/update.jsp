<%@ page import="common.Consultant" %>
<%@ page import="dao.DocumentLinkDAO" %>
<%@ page import="java.util.List" %>

<% Consultant consultantConnecte = (Consultant) session.getAttribute("consultantConnecte"); %>

<div class="col-lg-12">
    <div class="input-group col-lg-2">
        <span class="input-group-addon"><input type="checkbox" id="activerForm" onclick="document.getElementById('fieldsetFormUpdate').disabled = !document.getElementById('activerForm').checked" title="active le formulaire de maj profil"/></span>
        <input type="text" value="Modifier le profil" class="form-control" readonly title=""/>
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

    <form method="post" action="/oifyi/upload_cv" enctype="multipart/form-data" class="well">
        <legend>
            CV sur le site
        </legend>

        <% if (request.getAttribute("erreurCV") != null) { %>
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Erreur! </strong><%= request.getAttribute("erreurCV") %>
        </div>
        <% } %>

        <% List<String> cvs = DocumentLinkDAO.getAll(consultantConnecte.getId()); %>
        <table class="table table-striped table-condensed well">
            <% for (String cv : cvs) { %>
            <tr>
                <td><a href="<%=cv%>" target="_blank">
                    <%=cv%>
                </a>
                </td>
                <td>
                    <iframe src="<%=cv%>" width="800" height="600" align="middle"></iframe>
                </td>

            </tr>
            <%}%>
        </table>


        <div class="form-group">
            <label for="idCV">Charger un nouveau CV : </label>
            <input id="idCV" type="file" name="cv" value="test"/>
        </div>

        <input type="submit" value="Upload" class="btn btn-primary"/>
    </form>

    <div>responsable de pole</div>
    <div>responsable commercial</div>
    <div>date dernier entretien</div>
    <div>Liste des missions actuelles</div>
</div>
