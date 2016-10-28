<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="common.Consultant" %>
<%@ page import="common.MappingUrlFichier" %>
<%@ page import="dao.ConsultantDAO" %>
<%@ page import="dao.DroitsPagesDAO" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="dao.RoleDAO" %>

<%--Regarder dans la base de données si la page demandée existe--%>
<% String param_page = (request.getParameter("page") != null) ? request.getParameter("page") : "profil"; %>
<% String param_mode = (request.getParameter("mode") != null) ? request.getParameter("mode") : "connexion"; %>
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


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/general.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <title>OIFYI Intranet</title>
</head>
<body class="container">
<div class="header">
    <jsp:include page="WEB-INF/header.jsp" flush="true"/>
</div>
<div class="row">
    <% if (!pageTrouve) { %>
    <jsp:include page="WEB-INF/page404.jsp" flush="true"/>
    <% } else if (!pageAutorisee) { %>
    <jsp:include page="WEB-INF/pageInterdite.jsp" flush="true"/>
    <% } else { %>
    <jsp:include page="<%=muf.getCheminFichier()%>" flush="true"/>
    <% } %>
</div>
<div class="footer">
    <jsp:include page="WEB-INF/footer.jsp" flush="true"/>
</div>
</body>
</html>
