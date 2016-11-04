<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.*" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="common.*" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>
<% // Récupération de l'id de la mission à modifier
    int idConsultant = Integer.parseInt(request.getParameter("idConsultant"));
%>



<div class="row">
    <% Consultant consultant = ConsultantDAO.get(idConsultant);%>
    <h1>Les roles du consultant <%=consultant.getNom()%> <%=consultant.getPrenom()%></h1>


    <% ArrayList<Role> lrol = Consultant_RoleDAO.getRolesPourUnConsultant(consultant.getId());%>
    <table class="table table-striped">

        <thead>
        <tr>
            <td>Libelle</td>
        </tr>
        </thead>
        <tbody>
        <%for (Role rol : lrol) {%>
        <tr>
            <td><%=rol.getLibelle()%></td>
        </tr>
        </tbody>
        <%}%>
    </table>
</div>

<form method="post" action="/update_mission_consultant_role" class="well">
    <fieldset>
        <legend>Assigner un nouveau role</legend>

        <b>
            <%= request.getAttribute("erreur") == null ? "Remplissez tous les champs obligatoires*" : (String) request.getAttribute("erreur") %>
        </b>

        <input type="hidden" name="id_consultant" value="<%= consultant==null ? "" : ""+consultant.getId() %>"/>

        <div class="form-group">
            <label for="id_consultant">Consultants disponibles : </label>
            <% ArrayList<Role> listeRolesDispo = Consultant_RoleDAO.getRolesDisposPourUnConsultant(consultant.getId()); %>
            <select name="id_consultant" id="id_consultant" class="form-control" required>
                <% for (Role r : listeRolesDispo) { %>
                <option value="<%= r.getId_role()%>">
                    <%=r.getLibelle()%>
                </option>
                <% } %>
            </select>
        </div>

        <input type="submit" value="Ajouter le consultant" class="btn btn-primary"/>
    </fieldset>
</form>
