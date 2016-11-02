<%@ page import="common.Client" %>
<%@ page import="dao.ClientDAO" %>

<% // Récupération de l'id du consultant à modifier, si null : c'est un nouveau client
    String sIdClient = request.getParameter("idClient");
    Client client = null;
    if (sIdClient != null) {
        try {
            int idClient = Integer.parseInt(sIdClient);
            client = ClientDAO.get(idClient);
        } catch (Exception e) {
            client = null;
        }
    }
%>

<form method="post" action="<%=request.getContextPath()%>/update_client" class="well">
    <fieldset>
        <legend>Saisie d'un client</legend>

        <% if (request.getAttribute("erreur") != null) { %>
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Erreur! </strong><%= request.getAttribute("erreur") %>
        </div>
        <% } else { %>
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Info ! </strong><%= "Remplissez tous les champs obligatoires *" %>
        </div>
        <% } %>

        <input type="hidden" name="id_client" value="<%= client==null ? "" : ""+client.getId() %>"/>

        <div class="form-group">
            <label for="idRaisonSociale">Raison sociale : <input id="idRaisonSociale" type="text" name="raison_sociale" class="form-control" value="<%=client!=null ? client.getRaison_sociale() : ""%>" required/></label>
        </div>
        <div class="form-group">
            <label for="idFormeJurisique">Forme juridique : <input id="idFormeJurisique" type="text" name="forme_juridique" class="form-control" value="<%=client!=null ? client.getForme_juridique() : ""%>" required/></label>
        </div>
        <div class="form-group">
            <label for="idSiret">Siret : <input id="idSiret" type="text" name="siret" class="form-control" value="<%=client!=null ? client.getSiret() : ""%>" required/></label>
        </div>
        <div class="form-group">
            <label for="idNumTVA">Num TVA : <input id="idNumTVA" type="text" name="num_tva" class="form-control" value="<%=client!=null ? client.getNum_tva() : ""%>" required/></label>
        </div>
        <div class="form-group">
            <label for="idAdresseNumero">Adresse (numéro) : <input id="idAdresseNumero" type="text" name="adresseNumero" class="form-control" value="<%=client!=null ? String.valueOf(client.getAdresse().getNumero()) : ""%>" required/></label>
        </div>
        <div class="form-group">
            <label for="idAdresseRue">Adresse (rue) : <input id="idAdresseRue" type="text" name="adresseRue" class="form-control" value="<%=client!=null ? client.getAdresse().getRue() : ""%>" required/></label>
        </div>
        <div class="form-group">
            <label for="idAdresseCP">Adresse (code postal) : <input id="idAdresseCP" type="text" name="adresseCP" class="form-control" value="<%=client!=null ? String.valueOf(client.getAdresse().getCp()) : ""%>" required/></label>
        </div>
        <div class="form-group">
            <label for="idAdresseVille">Adresse (ville) : <input id="idAdresseVille" type="text" name="adresseVille" class="form-control" value="<%=client!=null ? client.getAdresse().getVille() : ""%>" required/></label>
        </div>

        <input type="submit" value="<%= client==null?"Créer":"Modifier" %> le consultant" class="btn btn-primary"/>
    </fieldset>
</form>
