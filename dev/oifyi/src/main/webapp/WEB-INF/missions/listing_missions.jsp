<%@ page import="common.Consultant" %>
<%@ page import="common.Mission" %>
<%@ page import="dao.ClientDAO" %>
<%@ page import="dao.ConsultantDAO" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="dao.MissionDAO" %>
<%@ page import="java.util.ArrayList" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>

<div class="row">
    <% ArrayList<Mission> lmis = MissionDAO.getAll();%>
    <table class="table table-striped">
        <tr>
            <th>Raison sociale du client</th>
            <th>Nom</th>
            <th>Date début</th>
            <th>Date fin</th>
            <th>Action</th>
        </tr>
        <%for (Mission mis : lmis) {%>
        <tr>
            <td><%=ClientDAO.get(mis.getClient_id()).getRaison_sociale()%>
            </td>
            <td><%=mis.getNom()%>
            </td>
            <td><%=mis.getDate_debut().toString()%>
            </td>
            <td><%=mis.getDate_fin().toString()%>
            </td>
            <td><a href="<%=MappingUrlFichierDAO.getMuf("missions", "update").formerUrl()%>&idMission=<%=mis.getId_mission()%>"><input type="button" class="btn btn-primary" <%if (ConsultantDAO.isAdmin(consultantConnecte.getId())) {%>value="Modifier/afficher" <%} else {%>value="Afficher"<%}%>/></a>
            </td>
            </td>
            <td><a href="<%=MappingUrlFichierDAO.getMuf("missions", "consultants").formerUrl()%>&idMission=<%=mis.getId_mission()%>"><input type="button" class="btn btn-primary" <%if (ConsultantDAO.isAdmin(consultantConnecte.getId())) {%>value="Modifier/afficher les consultants"<%} else {%>value="Afficher les consultants"<%}%>/></a>
            </td>
        </tr>
        <%}%>
    </table>
    <a href="<%=MappingUrlFichierDAO.getMuf("missions", "update").formerUrl()%>"><input type="button" class="btn btn-primary" value="Nouvelle mission"/></a>
</div>
