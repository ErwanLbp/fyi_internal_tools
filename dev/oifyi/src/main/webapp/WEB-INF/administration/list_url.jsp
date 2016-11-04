<%@ page import="common.MappingUrlFichier" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.MappingUrlFichierDAO" %>

<% List<MappingUrlFichier> lmuf = MappingUrlFichierDAO.getAll();%>
<table width="100%" border="1" class="table table-striped table-bordered table-condensed well">
    <caption>Contenu table Mapping Url Fichier</caption>
    <tr>
        <th>Id</th>
        <th>Page</th>
        <th>Mode</th>
        <th>=></th>
        <th>Fichier</th>
        <th>Url()</th>
    </tr>
    <%for (MappingUrlFichier muf : lmuf) {%>
    <tr>
        <td><%=muf.getId_muf()%>
        </td>
        <td><%=muf.getNomPage()%>
        </td>
        <td><%=muf.getNomMode()%>
        </td>
        <td></td>
        <td><%=muf.getCheminFichier()%>
        </td>
        <td><%=muf.formerUrl()%>
        </td>
    </tr>
    <%}%>
</table>
