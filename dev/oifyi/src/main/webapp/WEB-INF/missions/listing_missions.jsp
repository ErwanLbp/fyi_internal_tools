<%@ page import="common.Mission" %>
<%@ page import="common.Consultant" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.*" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>


<div class="row">
    <% ArrayList<Mission> lmis = MissionDAO.getAll();%>
    <%--    <% ArrayList<Absence> labs = AbsenceDAO.getAll();%>--%>
    <table class="table table-striped">

        <thead>
        <tr>
            <td>Raison sociale du client</td>
            <td>Nom</td>
            <td>Date début</td>
            <td>Date fin</td>
        </tr>
        </thead>
        <tbody>
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
            <td><a href="<%=MappingUrlFichierDAO.getMuf("missions", "update").formerUrl()%>&idMission=<%=mis.getId_mission()%>"><input type="button" class="btn btn-primary" <%if (ConsultantDAO.isAdmin(consultantConnecte.getId())) {%>value="Modifier/afficher"<%} else {%>value="Afficher"<%}%>/></a>
            </td>
        </tr>
        </tbody>
        <%}%>
    </table>
    <a href="<%=MappingUrlFichierDAO.getMuf("missions", "update").formerUrl()%>"><input type="button" class="btn btn-primary" value="Nouvelle mission"/></a>
</div>
