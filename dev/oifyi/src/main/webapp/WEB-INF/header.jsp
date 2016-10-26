<%@ page import="dao.MappingUrlFichierDAO" %>
<%--Ce fichier contient le code de la balise header--%>

<!--<header>-->
<nav class="navbar navbar-default">
    <ul class="nav navbar-nav">
        <li class="active"><a href="<%=MappingUrlFichierDAO.getMuf("accueil", "view").formerUrl()%>">FYI Consulting <span class="sr-only">(current)</span></a></li>
        <li><a href="<%=MappingUrlFichierDAO.getMuf("development", "view").formerUrl()%>">Dab & Co</a></li>
        <li>Consultant
            <ul>
                <li><a href="<%=MappingUrlFichierDAO.getMuf("consultant", "list").formerUrl()%>">Listing consultants</a></li>
                <li><a href="<%=MappingUrlFichierDAO.getMuf("clients", "list").formerUrl()%>">Listing clients</a></li>
                <li><a href="<%=MappingUrlFichierDAO.getMuf("absences", "saisie").formerUrl()%>">Saisie d'absences</a></li>
                <li><a href="<%=MappingUrlFichierDAO.getMuf("absences", "validation").formerUrl()%>">Validation d'absences</a></li>
                <li><a href="<%=MappingUrlFichierDAO.getMuf("cra", "saisie").formerUrl()%>">Saisie CRA</a></li>
                <li><a href="<%=MappingUrlFichierDAO.getMuf("cra", "validation").formerUrl()%>">Validation CRA</li>
                <li><a href="<%=MappingUrlFichierDAO.getMuf("notes_de_frais", "saisie").formerUrl()%>">Notes de frais</li>
                <li><a href="<%=MappingUrlFichierDAO.getMuf("notes_de_frais", "validation").formerUrl()%>">Validation notes de frais</li>
                <li><a href="<%=MappingUrlFichierDAO.getMuf("facturation_indep", "saisie").formerUrl()%>">Facturation indépendants</li>
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