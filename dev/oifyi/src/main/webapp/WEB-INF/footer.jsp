<%@ page import="dao.MappingUrlFichierDAO" %><%--Ce fichier contient le code de la balise footer--%>

<!--<footer>-->
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="col-lg-offset-2 col-lg-3">
        <h4>- E.I.S.T.I -</h4>
        <p>Dab & Co</p>
    </div>
    <div class="col-lg-4">
        <i>Liste des pages de la BDD : <a href="<%=MappingUrlFichierDAO.getMuf("administration","list_pages").formerUrl()%>">Test BDD</a></i><br/><br/>
    </div>
</nav>
<!--</footer>-->