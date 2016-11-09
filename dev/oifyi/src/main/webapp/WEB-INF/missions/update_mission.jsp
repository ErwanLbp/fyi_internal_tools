<%@ page import="common.Client" %>
<%@ page import="common.Consultant" %>
<%@ page import="common.Mission" %>
<%@ page import="dao.ClientDAO" %>
<%@ page import="dao.MissionDAO" %>
<%@ page import="java.util.List" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>

<% // Récupération de l'id de l'absence à modifier, si null : c'est une nouvelle absence
    String sIdMission = request.getParameter("idMission");
    Mission mission = null;
    if (sIdMission != null) {
        try {
            int idMission = Integer.parseInt(sIdMission);
            mission = MissionDAO.get(idMission);
        } catch (Exception e) {
            mission = null;
        }
    }
%>

<form method="post" action="/oifyi/update_mission" class="well">
    <fieldset>
        <legend>Saisie d'une mission</legend>

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

        <input type="hidden" name="id_mission" value="<%= mission==null ? "" : ""+mission.getId_mission() %>"/>

        <input type="hidden" name="id_consultant" value="<%= consultantConnecte.getId() %>"/>

        <div class="form-group">
            <label for="idNom">Nom : <input id="idNom" type="text" name="nom" class="form-control" value="<%=mission!=null ? mission.getNom() : ""%>"/></label>
        </div>

        <div class="form-group">
            <label for="idNumContrat">Numéro de contrat : <input id="idNumContrat" type="text" name="num_contrat" class="form-control" value="<%=mission!=null ? mission.getNumero_contrat() : ""%>"/></label>
        </div>

        <%--FIXME on fera une recherche et on récupérera l'id dans un hidden--%>
        <div class="form-group">
            <label for="idClient">Client : </label>
            <% List<Client> clients = ClientDAO.getAll(); %>
            <select name="id_client" id="idClient" class="form-control" required>
                <% for (Client c : clients) { %>
                <option value="<%= c.getId()%>" <%=mission != null ? (mission.getClient_id() == c.getId() ? "selected" : "") : "" %>><%= c.getRaison_sociale() %>
                </option>
                <% } %>
            </select>
        </div>

        <div class="form-group">
            <label for="idDateDeb">Date début* : <input placeholder="yyyy-mm-dd" id="idDateDeb" type="date" name="date_deb" class="form-control" value="<%=mission!=null ? mission.getDate_debut().toString() : ""%>" required/></label>
        </div>

        <div class="form-group">
            <label for="idDateFin">Date fin* : <input placeholder="yyyy-mm-dd" id="idDateFin" type="date" name="date_fin" class="form-control" value="<%=mission!=null ? mission.getDate_fin().toString() : ""%>" required/></label>
        </div>

        <div class="form-group">
            <label for="idTJM">TJM : <input id="idTJM" type="number" min="0" step="1" name="tjm" class="form-control" value="<%=mission!=null ? mission.getTjm() : 0%>"/></label>
        </div>


        <input type="submit" value="<%= mission==null?"Créer":"Modifier" %> la mission" class="btn btn-primary"/>
    </fieldset>
</form>
