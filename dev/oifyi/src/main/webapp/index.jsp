<%@ page import="common.Consultant" %>
<%@ page import="common.DroitsPages" %>
<%@ page import="common.MappingUrlFichier" %>
<%@ page import="dao.ConsultantDAO" %>
<%@ page import="dao.DroitsPagesDAO" %>
<%@ page import="dao.MappingUrlFichierDAO" %>

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
    Consultant consultantConnecte = (Consultant) session.getAttribute("consultantConnecte");
    // Si un utilisateur est connecté on regarde s'il a le droit d'accès ou s'il est admin
    if (consultantConnecte != null)
        pageAutorisee = DroitsPagesDAO.isInDB(new DroitsPages(muf.getId_muf(), consultantConnecte.getRole_id())) || ConsultantDAO.isAdmin(consultantConnecte.getId());
        // Si personne est connecté, on accepte la page de connexion seulement
    else if (param_page.equals("profil") && param_mode.equals("connexion"))
        pageAutorisee = true;
        //Si personne est connecté, on redirige vers la page de connexion
    else
        response.sendRedirect(MappingUrlFichierDAO.getMuf("profil", "connexion").formerUrl());

%>
<% } %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/general.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>

    <title>OIFYI Intranet</title>
</head>
<body class="container">
<div id="background"></div>
<div class="header">
    <jsp:include page="WEB-INF/header.jsp" flush="true"/>
</div>
<div class="row center">
    <% if (!pageTrouve) { %>
    <jsp:include page="WEB-INF/page404.jsp" flush="true"/>
    <% } else if (!pageAutorisee) { %>
    <jsp:include page="WEB-INF/pageInterdite.jsp" flush="true"/>
    <% } else { %>
    <jsp:include page="<%=muf.getCheminFichier()%>" flush="true"/>
    <% } %>
</div>
</body>
</html>
