<%@ page import="dao.MappingUrlFichierDAO" %>
<%--Ce fichier contient le code de la balise header--%>

<!--<header>-->
<nav class="navbar navbar-default">
    <ul class="nav navbar-nav">
        <li class="active"><a href="<%=MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl()%>">FYI Consulting <span class="sr-only">(current)</span></a></li>
        <li><a href="<%=MappingUrlFichierDAO.getMuf("development", "view").formerUrl()%>">Dab & Co</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="<%=MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl()%>"><span class="glyphicon glyphicon-home"></span>Accueil</a></li>
        <% if (request.getSession().getAttribute("consultantConnecte") != null) { %>
        <li><a href="<%=MappingUrlFichierDAO.getMuf("profil", "view").formerUrl()%>"><span class="glyphicon glyphicon-user"></span>Profil</a></li>
        <li><a href="<%=MappingUrlFichierDAO.getMuf("profil", "deconnexion").formerUrl()%>"><span class="glyphicon glyphicon-log-in"></span> Se deconnecter</a></li>
        <% } %>
    </ul>
</nav>


<!--</header>-->