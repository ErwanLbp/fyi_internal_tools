<%@ page import="common.Consultant" %>
<%@ page import="common.Role" %>
<%@ page import="dao.ConsultantDAO" %>
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

<form method="post" action="/update_consultant" class="well">
    <fieldset>
        <legend>Saisie d'un consultant</legend>

        <b>
            <%= request.getAttribute("erreur") == null ? "Remplissez tous les champs obligatoires" : (String) request.getAttribute("erreur") %>
        </b>

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
            <label for="idRole">Role : </label>
            <% List<Role> roles = RoleDAO.getAll(); %>
            <select name="role" id="idRole" class="form-control" required>
                <% for (Role r : roles) { %>
                <option value="<%= r.getId_role()%>" <%=consultant != null ? (consultant.getRole_id() == r.getId_role() ? "selected" : "") : "" %>><%= r.getLibelle() %>
                </option>
                <% } %>
            </select>
        </div>

        <input type="submit" value="<%= consultant==null?"Créer":"Modifier" %> le consultant" class="btn btn-primary"/>
    </fieldset>
</form>
