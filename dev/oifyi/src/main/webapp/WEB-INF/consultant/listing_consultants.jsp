<%@ page import="common.Consultant" %>
<%@ page import="common.Recherche" %>
<%@ page import="common.Role" %>
<%@ page import="dao.ConsultantDAO" %>
<%@ page import="dao.Consultant_RoleDAO" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="java.util.ArrayList" %>


<div class="col-lg-12">
    <div class="row">
        <% ArrayList<Consultant> lcon = ConsultantDAO.getAll(request.getParameter("sort"));%>
        <% String url_page = Recherche.supprimerSort(request.getRequestURI() + "?" + request.getQueryString()); %>
        <div class="col-md-12">
            <table class="table table-striped well">
                <tr>
                    <th>
                        <button type="button" class="btn btn-default" onclick="window.location.href='<%=url_page%>&sort=id_consultant'">
                            Id <span class="glyphicon glyphicon-sort"></span>
                        </button>
                    </th>
                    <th>
                        <button type="button" class="btn btn-default" onclick="window.location.href='<%=url_page%>&sort=Nom'">
                            Nom <span class="glyphicon glyphicon-sort"></span>
                        </button>
                    </th>
                    <th>
                        <button type="button" class="btn btn-default" onclick="window.location.href='<%=url_page%>&sort=Prenom'">
                            Prénom <span class="glyphicon glyphicon-sort"></span>
                        </button>
                    </th>
                    <th>
                        <button type="button" class="btn btn-default" onclick="window.location.href='<%=url_page%>&sort=Username'">
                            Username <span class="glyphicon glyphicon-sort"></span>
                        </button>
                    </th>
                    <th>Password</th>
                    <th>Role</th>
                    <th colspan="2">Action</th>
                </tr>
                <% for (Consultant con : lcon) { %>
                <% ArrayList<Role> lrol = Consultant_RoleDAO.getRoles(con.getId()); %>
                <tr>
                    <td><%=con.getId()%>
                    </td>
                    <td><%=con.getNom()%>
                    </td>
                    <td><%=con.getPrenom()%>
                    </td>
                    <td><%=con.getUsername()%>
                    </td>
                    <td><%=con.getPassword()%>
                    </td>
                    <td>
                        <% for (Role rol : lrol) { %>
                        <%= (rol == lrol.get(lrol.size() - 1)) ? rol.getLibelle() : rol.getLibelle() + ", " %>
                        <% } %>
                    </td>
                    <td>
                        <div class="btn-group" role="group">
                            <button class="btn btn-primary" onclick="window.location.href='<%=MappingUrlFichierDAO.getMuf("consultant", "update").formerUrl()%>&idConsultant=<%=con.getId()%>'" title="Modifier le consultant"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                            <button class="btn btn-danger" onclick="window.location.href='/oifyi/supprimerConsultant?idConsultant=<%=con.getId()%>'" title="Supprimer le consultant"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                        </div>
                    </td>
                </tr>
                <% } %>
            </table>
            <a href="<%=MappingUrlFichierDAO.getMuf("consultant", "update").formerUrl()%>"><input type="button" class="btn btn-primary" value="Créer un consultant"/></a>
        </div>
    </div>
</div>