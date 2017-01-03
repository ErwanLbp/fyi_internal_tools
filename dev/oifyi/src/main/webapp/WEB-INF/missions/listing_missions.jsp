<%@ page import="common.Consultant" %>
<%@ page import="common.Mission" %>
<%@ page import="common.Recherche" %>
<%@ page import="dao.ClientDAO" %>
<%@ page import="dao.ConsultantDAO" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="dao.MissionDAO" %>
<%@ page import="java.util.List" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>

<div class="col-lg-12">
    <% List<Mission> lmis = MissionDAO.getAll(request.getParameter("sort"));%>
    <% String url_page = Recherche.supprimerSort(request.getRequestURI() + "?" + request.getQueryString()); %>
    <table class="table table-striped well">
        <tr>
            <th>Raison sociale du client</th>
            <th>
                <button type="button" class="btn btn-default" onclick="window.location.href='<%=url_page%>&sort=nom'">
                    Nom <span class="glyphicon glyphicon-sort"></span>
                </button>
            </th>
            <th>
                <button type="button" class="btn btn-default" onclick="window.location.href='<%=url_page%>&sort=date_debut'">
                    Date début <span class="glyphicon glyphicon-sort"></span>
                </button>
            </th>
            <th>
                <button type="button" class="btn btn-default" onclick="window.location.href='<%=url_page%>&sort=date_fin'">
                    Date fin <span class="glyphicon glyphicon-sort"></span>
                </button>
            </th>
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
                <a href="<%=MappingUrlFichierDAO.getMuf("missions", "list_assignations").formerUrl()%>&idMission=<%=mis.getId_mission()%>"><input type="button" class="btn btn-primary" <%if (ConsultantDAO.isAdmin(consultantConnecte.getId())) {%>value="Modifier/afficher les consultants" <%} else {%>value="Afficher les consultants"<%}%>/></a>
            </td>
        </tr>
        <%}%>
    </table>
    <a href="<%=MappingUrlFichierDAO.getMuf("missions", "update").formerUrl()%>"><input type="button" class="btn btn-primary" value="Nouvelle mission"/></a>
</div>
