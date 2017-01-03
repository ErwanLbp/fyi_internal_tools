<%@ page import="common.Consultant" %>
<%@ page import="dao.MappingUrlFichierDAO" %>

<% String consultConnecte = (session.getAttribute("consultantConnecte") != null) ? "Connecté en tant que " + ((Consultant) session.getAttribute("consultantConnecte")).getUsername() + " <span class='glyphicon glyphicon-user' aria-hidden='true'></span>" : "";%>

<nav class="navbar navbar-default">
    <ul class="nav navbar-nav navbar-left">
        <li class="active"><a href="<%=MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl()%>">FYI Consulting <span class="sr-only">(current)</span></a></li>
        <li><a href="<%=MappingUrlFichierDAO.getMuf("development", "view").formerUrl()%>">Dab & Co</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li class="active"><p class="navbar-text"><%=consultConnecte%>
        </p></li>
        <li><a href="<%=MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl()%>"><span class="glyphicon glyphicon-home"></span>Accueil</a></li>
        <% if (request.getSession().getAttribute("consultantConnecte") != null) { %>
        <li><a href="<%=MappingUrlFichierDAO.getMuf("profil", "update").formerUrl()%>"><span class="glyphicon glyphicon-user"></span>Profil</a></li>
        <li><a href="<%=MappingUrlFichierDAO.getMuf("profil", "deconnexion").formerUrl()%>"><span class="glyphicon glyphicon-log-in"></span> Se deconnecter</a></li>
        <% } %>
    </ul>
</nav>