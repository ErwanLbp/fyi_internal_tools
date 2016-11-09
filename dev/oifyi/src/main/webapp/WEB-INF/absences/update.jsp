<%@ page import="common.Absence" %>
<%@ page import="common.Consultant" %>
<%@ page import="common.TypeAbsence" %>
<%@ page import="dao.AbsenceDAO" %>
<%@ page import="dao.TypeAbsenceDAO" %>
<%@ page import="java.util.List" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>

<% // Récupération de l'id de l'absence à modifier, si null : c'est une nouvelle absence
    String sIdAbsence = request.getParameter("idAbsence");
    Absence absence = null;
    if (sIdAbsence != null) {
        try {
            int idAbsence = Integer.parseInt(sIdAbsence);
            absence = AbsenceDAO.get(idAbsence);
        } catch (Exception e) {
            absence = null;
        }
    }
%>

<form method="post" action="/oifyi/update_absence" class="well">
    <fieldset>
        <legend>Saisie d'une absence</legend>

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

        <input type="hidden" name="id_absence" value="<%= absence==null ? "" : ""+absence.getId_absence() %>"/>

        <input type="hidden" name="id_consultant" value="<%= consultantConnecte.getId() %>"/>

        <div class="form-group">
            <label for="id_type_absence">Type d'absence* : </label>
            <% List<TypeAbsence> ltyp = TypeAbsenceDAO.getAll(); %>
            <select name="id_type_absence" id="id_type_absence" class="form-control" required>
                <% for (TypeAbsence typ : ltyp) { %>
                <option value="<%= typ.getId_type_absence()%>" <%=absence != null ? (absence.getId_type_absence() == typ.getId_type_absence() ? "selected" : "") : "" %>><%= typ.getLibelle() %>
                </option>
                <% } %>
            </select>
        </div>
        <div class="form-group">
            <label for="idPlusPrecision">Plus de précision : <input id="idPlusPrecision" type="text" name="plus_precision" class="form-control" value="<%=absence!=null ? absence.getPlus_precision() : ""%>"/></label>
        </div>
        <div class="form-group">
            <label for="idDateDeb">Date début* : <input placeholder="yyyy-mm-dd" id="idDateDeb" type="date" name="date_deb" class="form-control" value="<%=absence!=null ? absence.getDate_debut().toString() : ""%>" required/></label>
        </div>
        <div class="form-group">
            <label for="idDateFin">Date fin* : <input placeholder="yyyy-mm-dd" id="idDateFin" type="date" name="date_fin" class="form-control" value="<%=absence!=null ? absence.getDate_fin().toString() : ""%>" required/></label>
        </div>
        <input type="hidden" name="id_statut_absence" value="<%= absence==null ? "2" : ""+absence.getId_statut_absence() %>"/>
        <div class="form-group">
            <label for="idCommentaire">Commentaire : <input id="idCommentaire" type="text" name="commentaire" class="form-control" value="<%=absence!=null ? absence.getCommentaire() : ""%>"/></label>
        </div>


        <input type="submit" value="<%= absence==null?"Créer":"Modifier" %> l'absence" class="btn btn-primary"/>
    </fieldset>
</form>
