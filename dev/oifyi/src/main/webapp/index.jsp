<%@ page import="java.util.List" %>
<%@ page import="main.java.common.Consultant" %>
<%@ page import="main.java.dao.TestDao" %>
<html>
<body>
<h2>Hello OIFYI!</h2>

<% List<Consultant> lc = TestDao.findAllConsultant();%>
<table width="200" border="1">
    <caption>Contenu table consultant</caption>
    <tr><th>Id</th><th>Nom</th><th>Prenom</th></tr>
    <%for (Consultant c : lc){%>
    <tr><td><%=c.getId()%></td><td><%=c.getNom()%></td><td><%=c.getPrenom()%></td></tr>
    <%}%>
</table>
</body>
</html>
