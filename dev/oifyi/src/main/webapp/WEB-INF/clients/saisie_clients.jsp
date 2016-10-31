<%@ page import="dao.RoleDAO" %>
<%@ page import="common.Role" %>
<%@ page import="java.util.List" %>

<form method="post" action="" class="well">
    <fieldset>
        <legend>Saisie d'un client</legend>

        <div class="row">
        <div class="col-lg-4">
            <h4>Informations</h4>
            <div class="form-group">
                <label for="idRaisonSociale">Raison sociale : <input id="idRaisonSociale" type="text" name="raisonSociale" class="form-control" value="" required/></label>
            </div>
            <div class="form-group">
                <label for="idFormeJuridique">Forme juridique : <input id="idFormeJuridique" type="text" name="formeJuridique" class="form-control" value="" required/></label>
            </div>
            <div class="form-group">
                <label for="idSiret">Siret : <input id="idSiret" type="text" name="siret" class="form-control" value="" required/></label>
            </div>
            <div class="form-group">
                <label for="idTVA">Numero TVA intercommunautaire: <input id="idTVA" type="text" name="TVA" class="form-control" value="" required/></label>
            </div>
                <input type="submit" value="Créer/modifier le client" class="btn btn-primary"/>
        </div>
            <div class="col-lg-5">
                <h4>Adresse du siège</h4>
                <div class="form-group">
                    <label for="idNumAdresse">Numéro : <input id="idNumAdresse" type="text" name="numAdresse" class="form-control" value="" required/></label>
                </div>
                <div class="form-group">
                    <label for="idRueAdresse">Rue : <input id="idRueAdresse" type="text" name="rueAdresse" class="form-control" value="" required/></label>
                </div>
                <div class="form-group">
                    <label for="idCodePostal">Code Postal : <input id="idCodePostal" type="text" name="codePostal" class="form-control" value="" required/></label>
                </div>
                <div class="form-group">
                    <label for="idVille">Ville : <input id="idVille" type="text" name="ville" class="form-control" value="" required/></label>
                </div>

        </div>
        </div>
    </fieldset>
</form>

