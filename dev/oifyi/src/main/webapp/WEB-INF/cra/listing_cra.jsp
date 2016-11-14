<%@ page import="common.Consultant" %>
<%@ page import="common.CraMois" %>
<%@ page import="dao.CraMoisDAO" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="dao.MissionDAO" %>
<%@ page import="dao.StatusCraDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>


<% Consultant consultantConnecte = (Consultant) session.getAttribute("consultantConnecte"); %>

<% int consultant_id = consultantConnecte.getId(); %>
<% int moisTmp = 0; %>
<% List<CraMois> lcm = CraMoisDAO.getAll(consultant_id); %>

<div class="col-lg-12">
    <table class="table table-striped table-bordered well">
        <caption>Liste des cra du consultant <%= consultant_id %>
        </caption>

        <tr>
            <th>Date</th>
            <th>Mission</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <%for (CraMois cm : lcm) {%>
        <% int moisMissionTmp = Integer.parseInt(new SimpleDateFormat("MM").format(cm.getMois_annee())); %>
        <tr>
            <% if (moisTmp != moisMissionTmp) { %>
            <% moisTmp = moisMissionTmp; %>
            <td id="mois_<%=moisTmp%>" rowspan="1" width="15%"><%=new SimpleDateFormat("MMMM - yyyy").format(cm.getMois_annee())%>
            </td>
            <% } else { %>
            <script type="text/javascript">document.getElementById("mois_<%=moisTmp%>").rowSpan = Number(document.getElementById("mois_<%=moisTmp%>").rowSpan) + 1;
            </script>
            <% } %>
            <td width="50%"><%=MissionDAO.get(cm.getMission_id()).getNom()%>
            </td>
            <td width="20%"><%=StatusCraDAO.get(cm.getStatus_cra_id()).getLibelle()%>
            </td>
            <td width="15%">
                <a href="<%=MappingUrlFichierDAO.getMuf("cra", "saisie").formerUrl()%>&moisAnneeCourant=<%=new SimpleDateFormat("yyyy-MM").format(cm.getMois_annee())%>&idConsultant=<%=cm.getConsultant_id()%>">
                    <input type="button" class="btn btn-primary" value="Saisir"/>
                </a>
            </td>
        </tr>
        <%}%>
    </table>
    <a href="<%=MappingUrlFichierDAO.getMuf("cra", "saisie").formerUrl()%>"><input type="button" class="btn btn-primary" value="Saisir un nouveau cra"/></a>
</div>