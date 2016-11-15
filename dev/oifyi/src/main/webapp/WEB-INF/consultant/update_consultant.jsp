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
    <form method="post" action="/oifyi/update_consultant" class="well">
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
                <label for="idNom">Nom : <input id="idNom" type="text" name="nom" class="form-control" value="<%=consultant!=null ? consultant.getNom() : ""%>" required/></label>
            </div>
            <div class="form-group">
                <label for="idPrenom">Prénom : <input id="idPrenom" type="text" name="prenom" class="form-control" value="<%=consultant!=null ? consultant.getPrenom() : ""%>" required/></label>
            </div>
            <div class="form-group">
                <label for="idUsername">Username : <input id="idUsername" type="text" name="username" class="form-control" value="<%=consultant!=null ? consultant.getUsername() : ""%>" required/></label>
            </div>
            <div class="form-group">
                <label for="idPassword">Password : <input id="idPassword" type="text" name="password" class="form-control" value="<%=consultant!=null ? consultant.getPassword() : ""%>" required/></label>
            </div>

            <div class="form-group">
                <label>Roles : </label>
                <% List<Role> roles = RoleDAO.getAll(); %>
                <% for (Role r : roles) { %>
                <label for="checkboxrole"><%= r.getLibelle() %> : </label>
                <input type=checkbox name="role" id="checkboxrole" class="form-control" value="<%= r.getId_role()%>" <%= (consultant != null) ? (Consultant_RoleDAO.isInDB(consultant.getId(), r.getId_role()) ? "checked" : "") : "" %>/>
                <% } %>
            </div>

            <input type="submit" value="<%= consultant==null?"Créer":"Modifier" %> le consultant" class="btn btn-primary"/>

        </fieldset>
    </form>
</div>
