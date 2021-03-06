<%@ page import="common.Client" %>
<%@ page import="dao.ClientDAO" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="java.util.List" %>


<div class="col-lg-12">
    <div class="row">
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

    <br/><br/><br/>

    <div class="row">
        <div class="col-lg-12">
            <% List<Client> lcli = ClientDAO.getAll();%>
            <table class="table table-striped well">

                <tr>
                    <th>Id</th>
                    <th>Raison sociale</th>
                    <th>Forme Juridique</th>
                    <th>Siret</th>
                    <th>Numéro de TVA</th>
                    <th>Adresse</th>
                    <th>Action</th>
                </tr>
                <%for (Client cli : lcli) {%>
                <tr>
                    <td><%=cli.getId()%>
                    </td>
                    <td><%=cli.getRaison_sociale()%>
                    </td>
                    <td><%=cli.getForme_juridique()%>
                    </td>
                    <td><%=cli.getSiret()%>
                    </td>
                    <td><%=cli.getNum_tva()%>
                    </td>
                    <td><%=cli.getAdresse().getNumero()%> <%=cli.getAdresse().getRue()%> <%=cli.getAdresse().getCp()%> <%=cli.getAdresse().getVille()%>
                    </td>
                    <td><a href="<%=MappingUrlFichierDAO.getMuf("clients", "update").formerUrl()%>&idClient=<%=cli.getId()%>"><input type="button" class="btn btn-primary" value="Modifier"/></a></td>
                </tr>
                <%}%>
            </table>
            <a href="<%=MappingUrlFichierDAO.getMuf("clients", "update").formerUrl()%>"><input type="button" class="btn btn-primary" value="Créer un client"/></a>
        </div>
    </div>
</div>