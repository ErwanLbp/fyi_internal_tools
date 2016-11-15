<%@ page import="common.Consultant" %>
<%@ page import="common.Role" %>
<%@ page import="dao.ConsultantDAO" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="dao.RoleDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.Consultant_RoleDAO" %>


<div class="row">
    <div class="col-md-6">
        <h4>Rechercher un consultant</h4>
        <div id="custom-search-input" class="input-group col-md-12">
            <input type="text" class="form-control input-lg" placeholder="Rechercher un consultant"/>
            <span class="input-group-btn">
                <button class="btn btn-info btn-lg" type="button">
                    <i class="glyphicon glyphicon-search"></i>
                </button>
            </span>
        </div>
    </div>
</div>


</br></br></br>

<div class="row">
    <% ArrayList<Consultant> lcon = ConsultantDAO.getAll();%>
    <table class="table table-striped well">
        <tr>
            <th>Id</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Username</th>
            <th>Password</th>
            <th>Role</th>
            <th colspan="2">Action</th>
        </tr>
        <%
            for (Consultant con : lcon) {
                ArrayList<Role> lrol = Consultant_RoleDAO.getRoles(con.getId());
        %>
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
            <td><%
                for (Role rol : lrol) {
                    if (rol == lrol.get(lrol.size() - 1)) {%>
                <%= rol.getLibelle()%> <%
                } else {%>
                <%= rol.getLibelle() + ", "%>
                <% }
                }
                %>
            </td>
            <td>
                <div class="btn-group" role="group">
                    <button class="btn btn-primary" onclick="window.location.href='<%=MappingUrlFichierDAO.getMuf("consultant", "update").formerUrl()%>&idConsultant=<%=con.getId()%>'" title="Modifier le consultant"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                    <button class="btn btn-danger" onclick="window.location.href='/oifyi/supprimerConsultant?idConsultant=<%=con.getId()%>'" title="Supprimer le consultant"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                </div>
            </td>
        </tr>
        <%}%>
    </table>
    <a href="<%=MappingUrlFichierDAO.getMuf("consultant", "update").formerUrl()%>"><input type="button" class="btn btn-primary" value="Créer un consultant"/></a>
</div>