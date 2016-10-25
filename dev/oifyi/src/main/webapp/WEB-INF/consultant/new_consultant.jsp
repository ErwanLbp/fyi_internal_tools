<%--
  Created by IntelliJ IDEA.
  User: eisti
  Date: 19/10/16
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="consultant" scope="request" class="common.Consultant"/>
<form method="post" action="/new_consultant" class="well">
    <legend>Saisie d'un consultant</legend>

    <b>
        <%= request.getParameter("erreur") == null ? "Remplissez tous les champs obligatoires" : request.getParameter("erreur") %>
    </b>

    <div class="form-group">
        <label for="idNom">Nom : <input id="idNom" type="text" name="nom" class="form-control" value="<jsp:getProperty name="consultant" property="nom"/>" required/></label>
    </div>
    <div class="form-group">
        <label for="idPrenom">Prénom : <input id="idPrenom" type="text" name="prenom" class="form-control" value="<jsp:getProperty name="consultant" property="prenom"/>" required/></label>
    </div>
    <div class="form-group">
        <label for="idBirth">Date de naissance : <input id="idBirth" type="date" name="birth" class="form-control" value="<jsp:getProperty name="consultant" property="prenom"/>" required/></label>
    </div>
    <div class="form-group">
        <label for="idAdresse">Adresse : <input id="idAdresse" type="text" name="adresse" class="form-control" value="<jsp:getProperty name="consultant" property="adresse"/>" required/></label>
    </div>
    <div class="form-group">
        <label for="idAdresseCP">Code Postal : <input id="idAdresseCP" type="text" name="adresseCP" class="form-control" value="<jsp:getProperty name="consultant" property="adresseCP"/>" required/></label>
    </div>
    <div class="form-group">
        <label for="idAdresseVille">Ville : <input id="idAdresseVille" type="text" name="adresseVille" class="form-control" value="<jsp:getProperty name="consultant" property="adresseVille"/>" required/></label>
    </div>
    <div class="form-group">
        <label for="idTelPerso">Tél. personnel : <input id="idTelPerso" type="text" name="telPerso" class="form-control" value="<jsp:getProperty name="consultant" property="telPerso"/>" required/></label>
    </div>
    <div class="form-group">
        <label for="idTelPro">Tél. professionnel : <input id="idTelPro" type="text" name="telPro" class="form-control" value="<jsp:getProperty name="consultant" property="telPro"/>" required/></label>
    </div>
    <div class="form-group">
        <label for="idDateEntree">Date d'entrée dans l'entreprise : <input id="idDateEntree" type="date" name="dateEntree" class="form-control" value="<jsp:getProperty name="consultant" property="dateEntree"/>" required/></label>
    </div>
    <div class="form-group">
        <label for="idDateSortie">Date de sortie dans l'entreprise : <input id="idDateSortie" type="date" name="dateSortie" class="form-control" value="<jsp:getProperty name="consultant" property="dateSortie"/>" required/></label>
    </div>
    <!-- à faire plus tard!-->
    <div class="form-group">
        <label for="idMissions">Affectations aux missions : <input id="idMissions" type="date" name="dateSortie" class="form-control" value="<jsp:getProperty name="consultant" property="missions"/>" required/></label>
    </div>
    <!--!-->
    <div class="form-group">
        <label>Type de consultant :
            <input type="radio" name="idType" value="salarie"> Salarié
            <input type="radio" name="idType" value="sous_traitant"> Sous-traitant
        </label>
    </div>
    <div class="form-group">
        <input type="submit" value="Ajouter un CV" class="btn btn-primary"/>
    </div>

    <input type="submit" value="Créer/modifier le consultant" class="btn btn-primary"/>
</form>
