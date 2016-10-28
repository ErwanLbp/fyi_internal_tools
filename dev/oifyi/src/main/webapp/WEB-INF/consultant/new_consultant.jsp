<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="dao.RoleDAO" %>
<%@ page import="common.Role" %>
<%@ page import="java.util.List" %>

<jsp:useBean id="newConsultant" scope="page" class="common.Consultant"/>
<jsp:setProperty name="newConsultant" property="*"/>

<form method="post" action="/new_consultant" class="well">
    <fieldset>
        <legend>Saisie d'un consultant</legend>

        <b>
            <%= request.getParameter("erreur") == null ? "Remplissez tous les champs obligatoires" : request.getParameter("erreur") %>
        </b>

        <div class="form-group">
            <label for="idNom">Nom : <input id="idNom" type="text" name="nom" class="form-control" value="<jsp:getProperty name="newConsultant" property="nom"/>" required/></label>
        </div>
        <div class="form-group">
            <label for="idPrenom">Prénom : <input id="idPrenom" type="text" name="prenom" class="form-control" value="<jsp:getProperty name="newConsultant" property="prenom"/>" required/></label>
        </div>
        <div class="form-group">
            <label for="idUsername">Username : <input id="idUsername" type="text" name="username" class="form-control" value="<jsp:getProperty name="newConsultant" property="username"/>" required/></label>
        </div>
        <div class="form-group">
            <label for="idPassword">Password : <input id="idPassword" type="text" name="password" class="form-control" value="<jsp:getProperty name="newConsultant" property="password"/>" required/></label>
        </div>
        <div class="form-group">
            <label for="idRole">Role : </label>
            <% List<Role> roles = RoleDAO.getAll(); %>
            <select name="role" id="idRole" class="form-control" required>
                <% for (Role r : roles) { %>
                <option value="<%= r.getId_role()%>" <%= (newConsultant.getRole_id() == r.getId_role()) ? "selected" : "" %>><%= r.getLibelle() %>
                </option>
                <% } %>
            </select>
            <input id="idRole" type="text" name="role" class="form-control" value="<jsp:getProperty name="newConsultant" property="role"/>" required/>
        </div>

        <input type="submit" value="Créer/modifier le consultant" class="btn btn-primary"/>
    </fieldset>
</form>
