<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="common.MappingUrlFichier" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<% String param_page = request.getParameter("page"), param_mode = request.getParameter("mode"); %>
<% if (param_page == null) param_page = "accueil"; %>
<% if (param_mode == null) param_mode = "view"; %>
<% System.out.println("page=" + param_page + " et mode=" + param_mode); %>
<% MappingUrlFichier muf = MappingUrlFichierDAO.getMuf(param_page, param_mode); %>
<% boolean pageTrouve = true, pageAutorisee = false; %>
<% if (muf.getCheminFichier() == null || muf.getCheminFichier().isEmpty()) { %>
<% pageTrouve = false; %>
<% } else { %>
<%
    //TODO Verification si le consultant connecté a le droit d'accès à cette page
    pageAutorisee = true; //TMP
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
