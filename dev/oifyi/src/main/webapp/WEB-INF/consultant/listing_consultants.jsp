<%@ page import="common.Consultant" %>
<%@ page import="dao.ConsultantDAO" %>
<%@ page import="common.Role" %>
<%@ page import="dao.RoleDAO" %>
<%@ page import="java.util.ArrayList" %>



<div class="container">

    <div class="col-md-6">
        <h2>Rechercher un consultant</h2>
        <div id="custom-search-input">
            <div class="input-group col-md-12">
                <input type="text" class="form-control input-lg" placeholder="Rechercher client"/>
                <span class="input-group-btn">
                        <button class="btn btn-info btn-lg" type="button">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </span>
            </div>
        </div>
    </div>

</div>


</br></br></br>

<form method="post" action="/new_consultant" class="well">
<div class="row">
    <% ArrayList<Consultant> lcon = ConsultantDAO.getAll();%>
    <table class="table table-striped">
        <thead>
        <tr>
            <td>Id</td>
            <td>Nom</td>
            <td>Prénom</td>
            <td>Username</td>
            <td>Password</td>
            <td>Role</td>
            <td>Modifier le consultant</td>
        </tr>
        </thead>
        <tbody>
        <%for (Consultant con : lcon) {%>
        <tr>
            <td><%=con.getId()%></td>
            <td><%=con.getNom()%></td>
            <td><%=con.getPrenom()%></td>
            <td><%=con.getUsername()%></td>
            <td><%=con.getPassword()%></td>
            <td><%=RoleDAO.get(con.getRole_id()).getLibelle()%></td>
            <td><input type="submit" value="X" <% request.getAttribute("con");%>/> </td>
        </tr>
        <%}%>
        </tbody>
    </table>
    <input type="submit" value="Créer un consultant" <% request.getAttribute("");%>/>
</div>
</form>