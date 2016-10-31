<%@ page import="common.Consultant" %>
<%@ page import="common.Mission" %>
<%@ page import="dao.MappingUrlFichierDAO" %>
<%@ page import="dao.MissionDAO" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>
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

    // Variables de début et fin des boucles for pour afficher les cases de saisies
    int jourMaxDuMois = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    //Liste contenant les numéros de jours du weekend pour griser les colonnes
    List<Integer> listWeekend = new ArrayList<Integer>();
    for (int i = 0; i < jourMaxDuMois; i++) {
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            listWeekend.add(i);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
    }
%>

<% int colspanTH = 3, colspanTitres = 35, size = 0; %>

<% List<Mission> missions = MissionDAO.getMissionsDuConsultant(consultantConnecte.getId(), moisAnneeSQL); %>

<form action="/saisieCra" method="post" class="well" id="formSaisieCra">
    <fieldset>
        <legend>
            <a href="<%=MappingUrlFichierDAO.getMuf("cra","saisie").formerUrl()%>&moisAnneeCourant=<%= new SimpleDateFormat("yyyy-MM").format(datePourMoisPrec)%>"><--</a>
            Saisie du CRA du mois
            <b>
                <%= new SimpleDateFormat("MM - yyyy").format(datePourMoisCourant)%>
            </b>
            <a href="<%=MappingUrlFichierDAO.getMuf("cra","saisie").formerUrl()%>&moisAnneeCourant=<%= new SimpleDateFormat("yyyy-MM").format(datePourMoisSuiv)%>">--></a>
        </legend>

        <h3><b>
            <%= request.getAttribute("erreur") != null ? (String) request.getAttribute("erreur") : "" %>
        </b></h3>

        <%--Champs caché pour transmettre le mois courant et le consultant concerné--%>
        <input type="hidden" name="moisAnnee" value="<%=datePourMoisCourant.getTime()%>"/>
        <input type="hidden" name="consultantConnecte_id" value="<%=consultantConnecte.getId()%>"/>


        <table class="table table-bordered table-striped table-condensed" onchange="remplirTotaux()" cellpadding="0" cellspacing="0">

            <%--Ligne pour les numéros de colonnes--%>
            <tr>
                <th colspan="<%=colspanTH%>"></th>
                <% for (int i = 0; i <= jourMaxDuMois; i++) { %>
                <th class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <%=i + 1%>
                </th>
                <% } %>
                <th>Total</th>
            </tr>

            <%--Ligne de titre pour 'Journées facturables'--%>
            <tr>
                <th colspan="<%=colspanTitres%>"><h4>Journees facturables</h4></th>
            </tr>

            <%--Liste des missions et case de saisie pour chaque jour--%>
            <% for (Mission m : missions) { %>
            <tr>
                <th colspan="<%=colspanTH%>"><h5><%=m.getNom()%>
                </h5></th>
                <% for (int i = 0; i < jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">  <!--FIXME N'afficher modifiable que sur les j-->
                    <input type="text" min="0" max="1" size="<%=size%>" name="M_<%=m.getId_mission()%>_<%=i%>" title="Pourcentage du jour travaillé (entre 0 et 1)"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" size="<%=size%>" id="tot_M_<%=m.getId_mission()%>" title="Total de jours travaillés sur cette mission"/>
                </td>
            </tr>
            <% } %>

            <%--Ligne de séparation--%>
            <tr>
                <th colspan="<%=colspanTitres%>"></th>
            </tr>

            <%--Ligne de titre pour 'Astreintes'--%>
            <tr>
                <th colspan="<%=colspanTitres%>"><h4>Astreintes</h4></th>
            </tr>

            <%--Liste des astreintes et case de saisie pour chaque jour--%>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Jour<br>(nb jour)</h5></th>
                <% for (int i = 0; i < jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input type="text" min="0" max="1" size="<%=size%>" name="AS_J_<%=i%>" title="Nombre d'astreinte de jour"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" size="<%=size%>" id="tot_AS_J" title="Nombre total d'astreinte de jour"/>
                </td>
            </tr>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Nuit<br>(nb jour)</h5></th>
                <% for (int i = 0; i < jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input type="text" min="0" max="1" size="<%=size%>" name="AS_N_<%=i%>" title="Nombre d'astreinte de nuit"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" size="<%=size%>" id="tot_AS_N" title="Nombre total d'astreinte de nuit"/>
                </td>
            </tr>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Interventions<br>(nb heure)</h5></th>
                <% for (int i = 0; i < jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input type="text" min="0" max="1" size="<%=size%>" name="AS_I_<%=i%>" title="Nombre d'heure d'intervention de l'astreinte"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" size="<%=size%>" id="tot_AS_I" title="Nombre total d'heures d'intervention"/>
                </td>
            </tr>

            <%--Ligne de séparation--%>
            <tr>
                <th colspan="<%=colspanTitres%>"></th>
            </tr>

            <%--Ligne de titre pour 'Journées d'absences'--%>
            <tr>
                <th colspan="<%=colspanTitres%>"><h4>Journees d'absences</h4></th>
            </tr>

            <%--Liste des journées d'absences et case de saisie pour chaque jour--%>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Congé</h5></th>
                <% for (int i = 0; i < jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input type="text" min="0" max="1" size="<%=size%>" name="AB_C_<%=i%>" title="0 / 0.5 / 1 jour de congé"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" size="<%=size%>" id="tot_AB_C" title="Nombre total de jours de congés"/>
                </td>
            </tr>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Ferié</h5></th>
                <% for (int i = 0; i < jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input type="text" min="0" max="1" name="AB_F_<%=i%>" title="0 / 0.5 / 1 jour férié"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" size="<%=size%>" id="tot_AB_F" title="Nombre total de jours feriés"/>
                </td>
            </tr>

            <%--Ligne de séparation--%>
            <tr>
                <th colspan="<%=colspanTitres%>"></th>
            </tr>

            <%--Ligne des totaux par jour et du total complet du mois--%>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Total</h5></th>
                <% for (int i = 0; i < jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input readonly type="text" min="0" max="1" size="<%=size%>" id="tot_col_<%=i%>" title="Total du pourcentage de jour travaillé"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" size="<%=size%>" id="tot_tot" title="Total de jours imputés"/>
                </td>
            </tr>

        </table>
        <input type="submit" onclick="remplirVidesZeros(); alert()" class="btn btn-primary" value="Envoyer"/>
    </fieldset>
</form>

<script type="text/javascript">
    function remplirTotaux() {
        // *******************
        // ** Remise à zero **
        // *******************
        <% for(Mission mission: missions){ %>
        document.getElementById("tot_M_<%=mission.getId_mission()%>").value = 0;
        <% } %>
        document.getElementById("tot_AS_J").value = 0;
        document.getElementById("tot_AS_N").value = 0;
        document.getElementById("tot_AS_I").value = 0;
        document.getElementById("tot_AB_F").value = 0;
        document.getElementById("tot_AB_C").value = 0;
        document.getElementById("tot_tot").value = 0

        // ************************************************
        // ** Totaux du bas et des cotés en même temps ! **
        // ************************************************
        for (i = 0; i < <%=jourMaxDuMois%>; i++) {
            somme = 0;

            // Récupération et ajout à la somme des valeur des cases missions
            <% for(Mission mission: missions){ %>
            somme += Number(document.getElementsByName("M_<%=mission.getId_mission()%>_" + i)[0].value);
            document.getElementById("tot_M_<%=mission.getId_mission()%>").value = Number(document.getElementById("tot_M_<%=mission.getId_mission()%>").value) + Number(document.getElementsByName("M_<%=mission.getId_mission()%>_" + i)[0].value);
            <% } %>

            // Récupération et ajout à la somme des valeur des cases absences
            somme += Number(document.getElementsByName("AB_C_" + i)[0].value);
            somme += Number(document.getElementsByName("AB_F_" + i)[0].value);
            document.getElementById("tot_AB_F").value = Number(document.getElementById("tot_AB_F").value) + Number(document.getElementsByName("AB_F_" + i)[0].value);
            document.getElementById("tot_AB_C").value = Number(document.getElementById("tot_AB_C").value) + Number(document.getElementsByName("AB_C_" + i)[0].value);

            // Ajout des totaux des astreintes
            document.getElementById("tot_AS_J").value = Number(document.getElementById("tot_AS_J").value) + Number(document.getElementsByName("AS_J_" + i)[0].value);
            document.getElementById("tot_AS_N").value = Number(document.getElementById("tot_AS_N").value) + Number(document.getElementsByName("AS_N_" + i)[0].value);
            document.getElementById("tot_AS_I").value = Number(document.getElementById("tot_AS_I").value) + Number(document.getElementsByName("AS_I_" + i)[0].value);

            // Assignation de la somme à la case total
            document.getElementById("tot_col_" + i).value = somme;
        }

        // ***********************
        // ** Totaux des totaux **
        // ***********************
        for (i = 0; i < <%=jourMaxDuMois%>; i++) {
            // Assignation de la somme à la case total général
            document.getElementById("tot_tot").value = Number(document.getElementById("tot_tot").value) + Number(document.getElementById("tot_col_" + i).value);
        }
    }

    // Pour qu'au chargement les cases de total soient remplis
    remplirTotaux();

    function remplirVidesZeros() {
        // ************************************************
        // ** Totaux du bas et des cotés en même temps ! **
        // ************************************************
        for (i = 0; i < <%=jourMaxDuMois%>; i++) {

            // Pour les jours de missions
            <% for(Mission mission: missions){ %>
            if (document.getElementsByName("M_<%=mission.getId_mission()%>_" + i)[0].value == "") document.getElementsByName("M_<%=mission.getId_mission()%>_" + i)[0].value = 0;
            <% } %>

            // Pour les jours d'absences
            if (document.getElementsByName("AB_C_" + i)[0].value == "") document.getElementsByName("AB_C_" + i)[0].value = 0;
            if (document.getElementsByName("AB_F_" + i)[0].value == "") document.getElementsByName("AB_F_" + i)[0].value = 0;

            // Pour les jours d'astreinte
            if (document.getElementsByName("AS_J_" + i)[0].value == "") document.getElementsByName("AS_J_" + i)[0].value = 0;
            if (document.getElementsByName("AS_N_" + i)[0].value == "") document.getElementsByName("AS_N_" + i)[0].value = 0;
            if (document.getElementsByName("AS_I_" + i)[0].value == "") document.getElementsByName("AS_I_" + i)[0].value = 0;
        }
    }
</script>
