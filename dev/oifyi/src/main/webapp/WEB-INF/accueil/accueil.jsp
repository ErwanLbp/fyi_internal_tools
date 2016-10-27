<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import="dao.MappingUrlFichierDAO" %>

<div class="col-lg-offset-4 col-sm-4">
    <li>Consultant
        <ul>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("consultant", "new").formerUrl()%>">Créer un consultant</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("consultant", "list").formerUrl()%>">Listing consultants</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("clients", "list").formerUrl()%>">Listing clients</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("absences", "saisie").formerUrl()%>">Saisie d'absences</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("absences", "validation").formerUrl()%>">Validation d'absences</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("cra", "saisie").formerUrl()%>">Saisie CRA</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("cra", "validation").formerUrl()%>">Validation CRA</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("notes_de_frais", "saisie").formerUrl()%>">Notes de frais</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("notes_de_frais", "validation").formerUrl()%>">Validation notes de frais</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("facturation_indep", "saisie").formerUrl()%>">Facturation indépendants</a></li>
        </ul>
    </li>
    <li>Missions
        <ul>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("missions", "list").formerUrl()%>">Listing</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("factu_missions", "saisie").formerUrl()%>">Facturation</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("factu_missions", "suivi").formerUrl()%>">Suivi facturation</a></li>
        </ul>
    </li>
    <li>Missions
        <ul>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("administration", "droits_roles").formerUrl()%>">Droits/Rôles</a></li>
            <li><a href="<%=MappingUrlFichierDAO.getMuf("administration", "alertes_mails").formerUrl()%>">Alertes et Mails</a></li>
        </ul>
    </li>
</div>