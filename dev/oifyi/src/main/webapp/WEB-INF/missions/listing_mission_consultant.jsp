<%@ page import="common.Consultant" %>
<%@ page import="common.Mission" %>
<%@ page import="dao.ClientDAO" %>
<%@ page import="dao.MissionDAO" %>
<%@ page import="dao.Mission_ConsultantDAO" %>
<%@ page import="dao.RoleDAO" %>
<%@ page import="java.util.ArrayList" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>
<% // Récupération de l'id de la mission à modifier
    int idMission = Integer.parseInt(request.getParameter("idMission"));
%>

<div class="col-lg-12">
    <div class="row">
        <% Mission mission = MissionDAO.get(idMission);%>
        <h2>Les consultants assignés à la mission <b><%=mission.getNom()%>
        </b> pour le client <b><%=ClientDAO.get(mission.getClient_id()).getRaison_sociale()%>
        </b>
        </h2>


        <% ArrayList<Consultant> lcon = Mission_ConsultantDAO.getConsultantsPourUneMission(mission.getId_mission());%>
        <table class="table table-striped well">

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
                <td><%=con.getNom()%>
                </td>
                <td><%=con.getPrenom()%>
                </td>
                <td><%=RoleDAO.get(con.getRole_id()).getLibelle()%>
                </td>
            </tr>
            </tbody>
            <%}%>
        </table>
    </div>

    <div class="row">
        <div class="col-lg-12">

            <form method="post" action="/oifyi/update_mission_consultant" class="well">
                <fieldset>
                    <legend>Assigner un nouveau consultant</legend>

                    <b>
                        <%= request.getAttribute("erreur") == null ? "Remplissez tous les champs obligatoires*" : (String) request.getAttribute("erreur") %>
                    </b>

                    <input type="hidden" name="id_mission" value="<%= mission==null ? "" : ""+mission.getId_mission() %>"/>

                    <div class="form-group">
                        <label for="id_consultant">Consultants disponibles : </label>
                        <% ArrayList<Consultant> listeConsultantsDispo = Mission_ConsultantDAO.getConsultantsDisposPourUneMission(mission.getId_mission()); %>
                        <select name="id_consultant" id="id_consultant" class="form-control" required>
                            <% for (Consultant c : listeConsultantsDispo) { %>
                            <option value="<%= c.getId()%>">
                                <%=c.getNom()%> <%=c.getPrenom()%>
                            </option>
                            <% } %>
                        </select>
                    </div>

                    <input type="submit" value="Ajouter le consultant" class="btn btn-primary"/>
                </fieldset>
            </form>
        </div>
    </div>
</div>