<%@ page import="common.Absence" %>
<%@ page import="common.Consultant" %>
<%@ page import="dao.AbsenceDAO" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="dao.StatutAbsenceDAO" %>
<%@ page import="dao.TypeAbsenceDAO" %>
<%@ page import="java.util.ArrayList" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>

<% ArrayList<Absence> labs = AbsenceDAO.getAllForConsultant(consultantConnecte.getId());%>

<div class="col-lg-12">
    <table class="table table-striped well">
        <tr>
            <th>Type d'absence</th>
            <th>Plus de précision</th>
            <th>Date début</th>
            <th>Date fin</th>
            <th>Statut de l'absence</th>
            <th>Commentaire</th>
            <th>Modifier l'absence</th>
        </tr>
        <%for (Absence abs : labs) {%>
        <tr>
            <td><%=TypeAbsenceDAO.get(abs.getId_type_absence()).getLibelle()%>
            </td>
            <td><%=abs.getPlus_precision()%>
            </td>
            <td><%=abs.getDate_debut().toString()%>
            </td>
            <td><%=abs.getDate_fin().toString()%>
            </td>
            <td><%=StatutAbsenceDAO.get(abs.getId_statut_absence()).getLibelle()%>
            </td>
            <td><%=abs.getCommentaire()%>
            </td>
            <td><%if (abs.getId_statut_absence() == 2) { %> <a href="<%=MappingUrlFichierDAO.getMuf("absences", "update").formerUrl()%>&idAbsence=<%=abs.getId_absence()%>"><input type="button" class="btn btn-primary" value="Modifier"/></a><%}%>
            </td>
        </tr>
        <%}%>
    </table>
    <a href="<%=MappingUrlFichierDAO.getMuf("absences", "update").formerUrl()%>"><input type="button" class="btn btn-primary" value="Nouvelle absence"/></a>
</div>
