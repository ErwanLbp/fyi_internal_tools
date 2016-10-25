<%@ page import="common.Client" %>
<%@ page import="dao.ClientDAO" %>
<%@ page import="java.util.ArrayList" %>


<div class="container">

    <div class="col-md-6">
        <h2>Rechercher un client</h2>
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

<div class="row">
    <% ArrayList<Client> lcli = ClientDAO.getAll();%>
    <table class="table table-striped">

        <thead>
        <tr>
            <td>Raison sociale</td>
            <td> Nom signataire</td>
            <td> telephone</td>
            <td> adresse du siege</td>
            <td> Fonction signataire</td>
            <td> Forme Juridique</td>
        </tr>
        </thead>
        <tbody>
        <%for (Client cli : lcli) {%>
        <tr>
            <td> <%=cli.getRaison_sociale()%></td>
            <td> <%=cli.getRepresentant_nom()%></td>
            <td> <%=cli.getRepresentant_fonction()%></td>
            <td> <%=cli.getTelephone()%></td>
            <td> <%=cli.getAdresse_numero()%> <%=cli.getAdresse_rue()%> <%=cli.getAdresse_cp()%> <%=cli.getAdresse_ville()%></td>
            <td> <%=cli.getForme_juridique()%></td>
        </tr>
        </tbody>
        <%}%>
    </table>
</div>
