<%@ page import="common.Consultant" %>
<%@ page import="common.CraMois" %>
<%@ page import="dao.*" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>


<% Consultant consultantConnecte = (Consultant) session.getAttribute("consultantConnecte"); %>

<% //Récupération du mois courant, si aucun paramètre n'est envoyé, le mois courant sera sélectionné
    // La date en paramètre doit être au format yyyy-MM
    String moisAnnee = request.getParameter("moisAnneeCourant");
    Calendar calendar = Calendar.getInstance();
    if (moisAnnee != null) {
        try {
            Date dMoisAnnee = new SimpleDateFormat("yyyy-MM").parse(moisAnnee);
            calendar.setTime(dMoisAnnee);
        } catch (ParseException e) {
            request.setAttribute("erreur", "La date reçue est mal écrite");
        }
    }
    // Remise du calendrier sur le premier jour du mois courant
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

    // Date SQL pour le CraMoisDAO
    java.sql.Date moisAnneeSQL = new java.sql.Date(calendar.getTimeInMillis());

    // Ajustement pour avoir le mois/annee du mois précédent
    calendar.add(Calendar.MONTH, -1);
    Date datePourMoisPrec = calendar.getTime();

    // Ajustement pour avoir le mois/annee du mois suivant
    calendar.add(Calendar.MONTH, +2);
    Date datePourMoisSuiv = calendar.getTime();

    // Réajustement pour avoir le mois/annee courant
    calendar.add(Calendar.MONTH, -1);
    Date datePourMoisCourant = calendar.getTime();
%>

<% int moisTmp = 0; %>
<% List<CraMois> lcm = CraMoisDAO.getAll(moisAnneeSQL); %>

<div class="col-lg-12">
    <form method="post" action="/ValiderCra">

        <table class="table table-striped table-bordered well">
            <caption>
                <a href="<%=MappingUrlFichierDAO.getMuf("cra","validation").formerUrl()%>&moisAnneeCourant=<%= new SimpleDateFormat("yyyy-MM").format(datePourMoisPrec)%>"><--</a>
                Liste des CRA du mois
                <b>
                    <%= new SimpleDateFormat("MM - yyyy").format(datePourMoisCourant)%>
                </b>
                <a href="<%=MappingUrlFichierDAO.getMuf("cra","validation").formerUrl()%>&moisAnneeCourant=<%= new SimpleDateFormat("yyyy-MM").format(datePourMoisSuiv)%>">--></a>
            </caption>

            <tr>
                <th>Date</th>
                <th>Mission</th>
                <th>Consultant</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <%for (CraMois cm : lcm) {%>
            <% int moisMissionTmp = Integer.parseInt(new SimpleDateFormat("MM").format(cm.getMois_annee())); %>
            <% Consultant consultantTmp = ConsultantDAO.get(cm.getConsultant_id()); %>
            <tr>
                <% if (moisTmp != moisMissionTmp) { %>
                <% moisTmp = moisMissionTmp; %>
                <td id="mois_<%=moisTmp%>" rowspan="1" width="10%"><%=new SimpleDateFormat("MMMM - yyyy").format(cm.getMois_annee())%>
                </td>
                <% } else { %>
                <script type="text/javascript">document.getElementById("mois_<%=moisTmp%>").rowSpan = Number(document.getElementById("mois_<%=moisTmp%>").rowSpan) + 1;
                </script>
                <% } %>
                <td width="45%"><%=MissionDAO.get(cm.getMission_id()).getNom()%>
                </td>
                <td width="15%"><%=consultantTmp.getNom() + " " + consultantTmp.getPrenom()%>
                </td>
                <td width="20%"><%=StatusCraDAO.get(cm.getStatus_cra_id()).getLibelle()%>
                    <input type="hidden" name="idCraMois" value="<%=cm.getId_cra_mois()%>"/>
                    <label for="none">Ne rien faire</label><input type="radio" name="action<%=cm.getId_cra_mois()%>" id="none" value="none"/>
                    <label for="valider">Valider</label><input type="radio" name="action_<%=cm.getId_cra_mois()%>" id="valider" value="valider"/>
                    <label for="modifier">Demander modif.</label><input type="radio" name="action_<%=cm.getId_cra_mois()%>" id="modifier" value="modifier"/>
                </td>
                <td width="10%">
                    <a href="<%=MappingUrlFichierDAO.getMuf("cra", "saisie").formerUrl()%>&moisAnneeCourant=<%=new SimpleDateFormat("yyyy-MM").format(cm.getMois_annee())%>&idConsultant=<%=cm.getConsultant_id()%>">
                        <input type="button" class="btn btn-primary" value="Détails"/>
                    </a>
                </td>
            </tr>
            <%}%>
        </table>

        <input type="hidden" name="moisAnnee" value="<%=moisAnnee%>"/>
        <input type="submit" class="btn btn-primary" value="Changer les status des CRA cochés"/>
    </form>

</div>