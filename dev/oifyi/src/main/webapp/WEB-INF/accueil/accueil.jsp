<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import="dao.MappingUrlFichierDAO" %>

<%--Menu fait avec la page : http://www.oneskyapp.com/fr/docs/bootstrap/components --%>

<div class="row">
    <div class="col-lg-offset-2 col-sm-6">
        <div class="btn-group btn-group-justified">
            <div class="btn-group">
                <button class="btn btn-default dropdown-toggle" type="button" id="dpm1" data-toggle="dropdown">Consultant <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dpm1">
                    <li role="presentation" class="divider"></li>
                    <li role="presentation" class="dropdown-header">Consultant</li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("consultant", "list").formerUrl()%>">Listing</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("consultant", "paye_suivi").formerUrl()%>">Suivi de la paye</a></li>
                    <li role="presentation" class="divider"></li>
                    <li role="presentation" class="dropdown-header">Absences</li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("absences", "list").formerUrl()%>">Listing</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("absences", "validation").formerUrl()%>">Validation</a></li>
                    <li role="presentation" class="divider"></li>
                    <li role="presentation" class="dropdown-header">Compte rendu d'activité</li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("cra", "listing").formerUrl()%>">Listing</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("cra", "validation").formerUrl()%>">Validation</a></li>
                    <li role="presentation" class="divider"></li>
                    <li role="presentation" class="dropdown-header">Notes de frais</li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("notes_de_frais", "list").formerUrl()%>">Listing</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("notes_de_frais", "validation").formerUrl()%>">Validation</a></li>
                    <li role="presentation" class="divider"></li>
                    <li role="presentation" class="dropdown-header">Fournisseurs</li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("fournisseurs", "facturation").formerUrl()%>">Facturation</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("fournisseurs", "contrats").formerUrl()%>">Contrats</a></li>
                    <li role="presentation" class="divider"></li>
                    <li role="presentation" class="dropdown-header">Indépendants</li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("independants", "facturation").formerUrl()%>">Facturation</a></li>
                </ul>
            </div>

            <div class="btn-group">
                <button class="btn btn-default dropdown-toggle" type="button" id="dpm2" data-toggle="dropdown">Missions <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dpm2">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("missions", "list").formerUrl()%>">Listing</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("missions", "facturation").formerUrl()%>">Facturation</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("missions", "facturation_suivi").formerUrl()%>">Suivi facturation</a></li>
                </ul>
            </div>

            <div class="btn-group">
                <button class="btn btn-default dropdown-toggle" type="button" id="dpm3" data-toggle="dropdown">Clients <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dpm3">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("clients", "list").formerUrl()%>">Listing</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("clients", "facturation").formerUrl()%>">Facturation</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("clients", "facturation_suivi").formerUrl()%>">Suivi facturation</a></li>
                </ul>
            </div>

            <div class="btn-group">
                <button class="btn btn-default dropdown-toggle" type="button" id="dpm4" data-toggle="dropdown">Administration <span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dpm4">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("administration", "droits_roles").formerUrl()%>">Droits/Rôles</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("administration", "alertes_mails").formerUrl()%>">Alertes et Mails</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("administration", "logs").formerUrl()%>">Logs et audits</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("administration", "parametrage").formerUrl()%>">Paramétrage</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=MappingUrlFichierDAO.getMuf("administration", "list_pages").formerUrl()%>">Liste des pages référencées</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-offset-1 col-sm-10">
        <% if (request.getParameter("success") != null) { %>
        <div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Succès! </strong><%= request.getParameter("success") %>
        </div>
        <% } %>

        <% if (request.getParameter("info") != null) { %>
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Info! </strong><%= request.getParameter("info") %>
        </div>
        <% } %>

        <% if (request.getParameter("warning") != null) { %>
        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Attention! </strong><%= request.getParameter("warning") %>
        </div>
        <% } %>

        <% if (request.getParameter("erreur") != null) { %>
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Erreur! </strong><%= request.getParameter("erreur") %>
        </div>
        <% } %>
    </div>
</div>