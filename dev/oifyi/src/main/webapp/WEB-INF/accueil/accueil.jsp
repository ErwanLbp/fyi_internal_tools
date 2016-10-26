<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="common.Consultant" %>
<%@ page import="common.MappingUrlFichier" %>
<%@ page import="dao.ConsultantDAO" %>
<%@ page import="dao.DroitsPagesDAO" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="dao.RoleDAO" %>

<%--Regarder dans la base de données si la page demandée existe--%>
<% String param_page = (request.getParameter("page") != null) ? request.getParameter("page") : "accueil"; %>
<% String param_mode = (request.getParameter("mode") != null) ? request.getParameter("mode") : "view"; %>
<% MappingUrlFichier muf = MappingUrlFichierDAO.getMuf(param_page, param_mode); %>
<% boolean pageTrouve = true, pageAutorisee = false; %>

<%--Si elle existe, regarder si le consultant connecté a le droit d'y accéder--%>
<% if (muf == null || muf.getCheminFichier().isEmpty()) { %>
<% pageTrouve = false; %>
<% } else { %>
<%
    int roleLambda = RoleDAO.get("lambda").getId_role();
    Consultant consultantConnecte = (Consultant) session.getAttribute("consultantConnecte");
    int roleConsultantConnecte = (consultantConnecte == null) ? roleLambda : consultantConnecte.getRole_id();      // Si personne est connecté, le role de l'utilisateur est 'lambda'
    if (roleConsultantConnecte == roleLambda && !(param_page.equals("profil") && param_mode.equals("connexion")))  // Si l'utilisateur n'est pas connecté et qu'il essaye d'accéder
        response.sendRedirect("connexion");                                                                        // à une autre page que la page de connexion on le redirige
    else {
        pageAutorisee = DroitsPagesDAO.isAllowed(muf.getId_muf(), roleConsultantConnecte); // La page est autorisée si le role (lambda par défaut) et la page matchent dans la BDD
        if (consultantConnecte != null)                                                    // ou s'il est admin
            pageAutorisee = pageTrouve || ConsultantDAO.isAdmin(consultantConnecte.getId());
    }
%>
<% } %>




<div class="col-lg-offset-4 col-sm-4">
    <h1>OK PAGE ACCUEIL</h1>

    <li class="active"><a href="<%=MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl()%>">FYI Consulting <span class="sr-only">(current)</span></a></li>
    <li><a href="<%=MappingUrlFichierDAO.getMuf("development", "view").formerUrl()%>">Dab & Co</a></li>
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