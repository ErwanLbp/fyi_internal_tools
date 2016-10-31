<%@ page import="common.Absence" %>
<%@ page import="common.Consultant" %>
<%@ page import="dao.AbsenceDAO" %>
<%@ page import="dao.ConsultantDAO" %>
<%@ page import="dao.TypeAbsenceDAO" %>
<%@ page import="dao.StatutAbsenceDAO" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="java.util.ArrayList" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>


<div class="row">
    <% ArrayList<Absence> labs = AbsenceDAO.getAllForConsultant(consultantConnecte.getId());%>
<%--    <% ArrayList<Absence> labs = AbsenceDAO.getAll();%>--%>
    <table class="table table-striped">

        <thead>
        <tr>
            <td>Type d'absence</td>
            <td>Plus de précision</td>
            <td>Date début</td>
            <td>Date fin</td>
            <td>Statut de l'absence</td>
            <td>Commentaire</td>
            <td>Modifier l'absence</td>
        </tr>
        </thead>
        <tbody>
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
            <td><%if (abs.getId_statut_absence()==2){ %> <a href="<%=MappingUrlFichierDAO.getMuf("absences", "update").formerUrl()%>&idAbsence=<%=abs.getId_absence()%>"><input type="button" class="btn btn-primary" value="Modifier"/></a><%}%>
            </td>
        </tr>
        </tbody>
        <%}%>
    </table>
    <a href="<%=MappingUrlFichierDAO.getMuf("absences", "update").formerUrl()%>"><input type="button" class="btn btn-primary" value="Nouvelle absence"/></a>
</div>
