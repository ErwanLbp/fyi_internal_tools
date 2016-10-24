<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="common.MappingUrlFichier" %>
<%@ page import="common.DroitsPages" %>
<%@ page import="dao.DroitsPagesDAO" %>
<%@ page import="common.Consultant" %>
<%@ page import="dao.ConsultantDAO" %>
<%@ page import="dao.RoleDAO" %>

<%--Regarder dans la base de données si la page demandée existe--%>
<% String param_page = (request.getParameter("page") != null) ? request.getParameter("page") : "accueil"; %>
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
    int roleConsultantConnecte = (consultantConnecte == null) ? roleLambda : consultantConnecte.getRole_id(); // Si personne est connecté, le role de l'utilisateur est 'lambda'
    if (roleConsultantConnecte == roleLambda && !(param_page.equals("accueil") && param_mode.equals("connexion")))  // Si l'utilisateur n'est pas connecté et qu'il essaye d'accéder
        response.sendRedirect("index.jsp?page=accueil&mode=connexion");                                             // à une autre page que la page de connexion on le redirige
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
    <%--<link href="WEB-INF/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="style/general.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <title>OIFYI Intranet</title>
</head>
<body class="container">
<div class="header">
    <jsp:include page="header.jsp" flush="true"/>
</div>
<div class="row">
    <% if (!pageTrouve) { %>
    <jsp:include page="page404.jsp" flush="true"/>
    <% } else if (!pageAutorisee) { %>
    <jsp:include page="pageInterdite.jsp" flush="true"/>
    <% } else { %>
    <jsp:include page="<%=muf.getCheminFichier()%>" flush="true"/>
    <% } %>
</div>
<div class="footer">
    <jsp:include page="footer.jsp" flush="true"/>
</div>
</body>
</html>
