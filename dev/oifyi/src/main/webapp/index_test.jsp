<%@ page import="common.Consultant" %>
<%@ page import="java.util.List" %>
<%@ page import="common.MappingUrlFichier" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<html>
<body>
<h2>Hello OIFYI!</h2>

<% List<MappingUrlFichier> lmuf = MappingUrlFichierDAO.getAll();%>
<table width="200" border="1">
    <caption>Contenu table consultant</caption>
    <tr>
        <th>Page</th>
        <th>Mode</th>
        <th>=></th>
        <th>Fichier</th>
    </tr>
    <%for (MappingUrlFichier muf : lmuf) {%>
    <tr>
        <td><%=muf.getMufpk().getNomPage()%>
        </td>
        <td><%=muf.getMufpk().getNomMode()%>
        </td>
        <td></td>
        <td><%=muf.getCheminFichier()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
