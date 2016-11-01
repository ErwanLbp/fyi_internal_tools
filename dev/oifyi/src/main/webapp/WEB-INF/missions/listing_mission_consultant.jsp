<%@ page import="common.Mission" %>
<%@ page import="common.Consultant" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.*" %>
<%@ page import="common.Mission_Consultant" %>
<%@ page import="java.lang.reflect.Array" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>
<% // Récupération de l'id de la mission à modifier
    int id_mission = Integer.parseInt(request.getParameter("idMission"));
%>



<div class="row">
    <% Mission mission = MissionDAO.get(id_mission);%>
    <h1>Les consultants assignés à la mission <%=mission.getNom()%> pour le client <%=ClientDAO.get(mission.getClient_id()).getRaison_sociale()%></h1>


    <% ArrayList<Consultant> lcon = Mission_ConsultantDAO.getConsultantsPourUneMission(id_mission);%>
    <table class="table table-striped">

        <thead>
        <tr>
            <td>Nom</td>
            <td>Prenom</td>
            <td>Role</td>
        </tr>
        </thead>
        <tbody>
        <%for (Consultant con : lcon) {%>
        <tr>
            <td><%=con.getNom()%></td>
            <td><%=con.getPrenom()%></td>
            <td><%=RoleDAO.get(con.getRole_id()).getLibelle()%></td>
        </tr>
        </tbody>
        <%}%>
    </table>
    <a href="<%=MappingUrlFichierDAO.getMuf("mission_consultant", "update").formerUrl()%>"><input type="button" class="btn btn-primary" value="Ajouter le consultant sur la mission"/></a>
</div>


<form method="post" action="/update_mission_consultant" class="well">
    <fieldset>
        <legend>Assigner un nouveau consultant</legend>

        <b>
            <%= request.getAttribute("erreur") == null ? "Remplissez tous les champs obligatoires*" : (String) request.getAttribute("erreur") %>
        </b>

        <input type="hidden" name="id_mission" value="<%= mission==null ? "" : ""+mission.getId_mission() %>"/>

        <input type="hidden" name="id_consultant" value="<%= consultantConnecte.getId() %>"/>

        <%--FIXME on fera une recherche et on récupérera l'id dans un hidden--%>
        <div class="form-group">
            <label for="id_consultant">Client : </label>
            <% ArrayList<Consultant> listeConsultantsDispo = Mission_ConsultantDAO.getConsultantsDisposPourUneMission(id_mission); %>
            <select name="id_consultant" id="id_consultant" class="form-control" required>
                <% for (Consultant c : listeConsultantsDispo) { %>
                <option value="<%= c.getId()%>"><%= c.getNom() %> <%= c.getPrenom() %>
                </option>
                <% } %>
            </select>
        </div>

        <input type="submit" value="Ajouter le consultant" class="btn btn-primary"/>
    </fieldset>
</form>
