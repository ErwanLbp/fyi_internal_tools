<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import="dao.MappingUrlFichierDAO" %>
<div class="row">
    <div class="col-lg-offset-4 col-sm-4">
        <ul>
            <li>Consultant
                <ul>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("consultant", "list").formerUrl()%>">Listing consultants</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("absences", "saisie").formerUrl()%>">Saisie d'absences</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("absences", "validation").formerUrl()%>">Validation d'absences</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("cra", "saisie").formerUrl()%>">Saisie CRA</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("cra", "validation").formerUrl()%>">Validation CRA</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("notes_de_frais", "list").formerUrl()%>">Notes de frais</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("notes_de_frais", "validation").formerUrl()%>">Validation notes de frais</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("fournisseurs", "facturation").formerUrl()%>">Facturation fournisseurs</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("fournisseurs", "contrats").formerUrl()%>">Contrats fournisseurs</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("independants", "facturation").formerUrl()%>">Facturation indépendants</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("consultant", "paye_suivi").formerUrl()%>">Suivi de la paye</a></li>
                </ul>
            </li>
            <li>Missions
                <ul>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("missions", "list").formerUrl()%>">Listing</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("missions", "facturation").formerUrl()%>">Facturation</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("missions", "facturation_suivi").formerUrl()%>">Suivi facturation</a></li>
                </ul>
            </li>
            <li>Clients
                <ul>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("clients", "list").formerUrl()%>">Listing</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("clients", "facturation").formerUrl()%>">Facturation</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("clients", "facturation_suivi").formerUrl()%>">Suivi facturation</a></li>
                </ul>
            </li>
            <li>Administration
                <ul>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("administration", "droits_roles").formerUrl()%>">Droits/Rôles</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("administration", "alertes_mails").formerUrl()%>">Alertes et Mails</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("administration", "logs").formerUrl()%>">Logs et audits</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("administration", "parametrage").formerUrl()%>">Paramétrage</a></li>
                    <li><a href="<%=MappingUrlFichierDAO.getMuf("administration", "list_pages").formerUrl()%>">Liste des pages référencées</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-lg-offset-4 col-sm-4">
        <h2>
            <b>
                <%= request.getAttribute("erreur") != null ? (String) request.getAttribute("erreur") : "" %>
            </b>
        </h2>
    </div>
</div>