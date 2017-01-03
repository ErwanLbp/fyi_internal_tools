<%@ page import="common.Consultant" %>
<%@ page import="common.Role" %>
<%@ page import="dao.ConsultantDAO" %>
<%@ page import="dao.Consultant_RoleDAO" %>
<%@ page import="dao.RoleDAO" %>
<%@ page import="java.util.List" %>

<% // Récupération de l'id du consultant à modifier, si null : c'est un nouveau consultant
    String sIdConsultant = request.getParameter("idConsultant");
    Consultant consultant = null;
    if (sIdConsultant != null) {
        try {
            int idConsultant = Integer.parseInt(sIdConsultant);
            consultant = ConsultantDAO.get(idConsultant);
        } catch (Exception e) {
            consultant = null;
        }
    }

%>
<div class="col-lg-12">
    <form method="post" action="/oifyi/update_consultant" class="well form-horizontal">
        <fieldset>
            <legend>Saisie d'un consultant</legend>

            <% if (request.getAttribute("erreur") != null) { %>
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <strong>Erreur! </strong><%= request.getAttribute("erreur") %>
            </div>
            <% } else { %>
            <div class="alert alert-info alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <strong>Info ! </strong><%= "Remplissez tous les champs obligatoires *" %>
            </div>
            <% } %>

            <input type="hidden" name="id_consultant" value="<%= consultant==null ? "" : ""+consultant.getId() %>"/>

            <div class="form-group">
                <label for="idNom" class="col-lg-2">Nom : </label>
                <div class="col-lg-6"><input id="idNom" type="text" name="nom" class="form-control" value="<%=consultant!=null ? consultant.getNom() : ""%>" required/></div>
            </div>
            <div class="form-group">
                <label for="idPrenom" class="col-lg-2">Prénom : </label>
                <div class="col-lg-6"><input id="idPrenom" type="text" name="prenom" class="form-control" value="<%=consultant!=null ? consultant.getPrenom() : ""%>" required/></div>
            </div>
            <div class="form-group">
                <label for="idUsername" class="col-lg-2">Username : </label>
                <div class="col-lg-6"><input id="idUsername" type="text" name="username" class="form-control" value="<%=consultant!=null ? consultant.getUsername() : ""%>" required/></div>
            </div>
            <div class="form-group">
                <label for="idPassword" class="col-lg-2">Password : </label>
                <div class="col-lg-6"><input id="idPassword" type="text" name="password" class="form-control" value="<%=consultant!=null ? consultant.getPassword() : ""%>" required/></div>
            </div>


            <label>Roles : </label>
            <% List<Role> roles = RoleDAO.getAll(); %>
            <% for (Role r : roles) { %>
            <div class="form-group">
                <label for="checkboxrole<%=r.getId_role()%>" class="col-lg-2"><%= r.getLibelle() %> : </label>
                <div class="col-lg-6"><input type=checkbox name="role_<%=r.getId_role()%>" id="checkboxrole<%=r.getId_role()%>" class="form-control" value="<%= r.getId_role()%>" <%= (consultant != null) ? (Consultant_RoleDAO.isInDB(consultant.getId(), r.getId_role()) ? "checked" : "") : "" %>/></div>
            </div>
            <% } %>

            <input type="submit" value="<%= consultant==null?"Créer":"Modifier" %> le consultant" class="btn btn-primary"/>

        </fieldset>
    </form>
</div>
