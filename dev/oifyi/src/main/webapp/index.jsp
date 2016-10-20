<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="common.MappingUrlFichierPK" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<link href="WEB-INF/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="general.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <title>A compléter</title>
</head>
<body class="container">
<div class="header">
    <jsp:include page="header.jsp" flush="true"/>
</div>
<div class="row">
    <% String param_page = request.getParameter("page"), param_mode = request.getParameter("mode"); %>
    <% if (param_page.equals("")) param_page = "accueil"; %>
    <% if (param_mode.equals("")) param_mode = "view"; %>
    <% System.out.println("page : " + param_page + "\nmode : " + param_mode); %>
    <% String cheminFichier = param_page + ".jsp"; %>
    <%--FIXME MappingUrlFichierDAO.getFichier(new MappingUrlFichierPK(param_page, param_mode));--%>
    <% if (cheminFichier == null || cheminFichier.isEmpty()) { %>
    <jsp:include page="page404.jsp" flush="true"/>
    <% } else { %>
    <jsp:include page="<%=cheminFichier%>" flush="true"/>
    <% } %>
</div>
<div class="footer">
    <jsp:include page="footer.jsp" flush="true"/>
</div>
</body>
</html>
