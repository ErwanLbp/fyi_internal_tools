<%@ page import="common.Consultant" %>
<%@ page import="common.Mission" %>
<%@ page import="dao.MissionDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>

<% Consultant consultantConnecte = (Consultant) request.getSession().getAttribute("consultantConnecte"); %>
<% //Récupération du mois courant, si aucun paramètre n'est envoyé, le mois courant sera sélectionné
    Date moisAnnee;
    moisAnnee = (Date) request.getAttribute("moisAnnee");
    Calendar calendar = Calendar.getInstance();
    if (moisAnnee != null) {
        calendar.setTime(moisAnnee);
    }
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

    java.sql.Date moisAnneeSQL = new java.sql.Date(calendar.getTimeInMillis());
    Date datePourMoisCourant = calendar.getTime();

    int jourMinDuMois = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
    int jourMaxDuMois = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    //Liste contenant les numéros de jours du weekend pour griser les colonnes
    List<Integer> listWeekend = new ArrayList();
    for (int i = jourMinDuMois; i <= jourMaxDuMois; i++) {
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            listWeekend.add(i);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
    }
%>

<% int colspanTH = 3, colspanTitres = 35; %>

<% List<Mission> missions = MissionDAO.getMissionsDuConsultant(consultantConnecte.getId(), moisAnneeSQL); %>

<form action="/saisieCra" method="post" class="">
    <fieldset <%= !missions.isEmpty() ? "disabled" : "" %>>
        <legend>Saisie du CRA du mois <b><%= new SimpleDateFormat("MM - yyyy").format(datePourMoisCourant)%>
        </b></legend>
        <table class="table table-bordered table-striped table-condensed">

            <%--Ligne pour les numéros de colonnes--%>
            <tr>
                <th colspan="<%=colspanTH%>"></th>
                <% for (int i = jourMinDuMois; i <= jourMaxDuMois; i++) { %>
                <th class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <%=i%>
                </th>
                <% } %>
                <th>Total</th>
            </tr>

            <%--Ligne de titre pour 'Journées facturables'--%>
            <tr>
                <td colspan="<%=colspanTitres%>"><h4>Journees facturables</h4></td>
            </tr>

            <%--Liste des missions et case de saisie pour chaque jour--%>
            <% for (Mission m : missions) { %>
            <tr>
                <th colspan="<%=colspanTH%>"><h5><%=m.getNom()%>
                </h5></th>
                <% for (int i = jourMinDuMois; i <= jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input type="text" min="0" max="1" name="M_<%=m.getId_mission()%>_<%=i%>" class="form-control" title="Pourcentage du jour travaillé (entre 0 et 1)"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" id="tot_M_<%=m.getId_mission()%>" class="form-control" title="Total de jours travaillés sur cette mission"/>
                </td>
            </tr>
            <% } %>

            <%--Ligne de séparation--%>
            <tr>
                <td colspan="<%=colspanTitres%>"></td>
            </tr>

            <%--Ligne de titre pour 'Astreintes'--%>
            <tr>
                <td colspan="<%=colspanTitres%>"><h4>Astreintes</h4></td>
            </tr>

            <%--Liste des astreintes et case de saisie pour chaque jour--%>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Astreintes de jour (nb jour)</h5></th>
                <% for (int i = jourMinDuMois; i <= jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input type="text" min="0" max="1" name="AS_J_<%=i%>" class="form-control" title="Nombre d'astreinte de jour"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" id="tot_AS_J" class="form-control" title="Nombre total d'astreinte de jour"/>
                </td>
            </tr>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Astreintes de nuit (nb jour)</h5></th>
                <% for (int i = jourMinDuMois; i <= jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input type="text" min="0" max="1" name="AS_N_<%=i%>" class="form-control" title="Nombre d'astreinte de nuit"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" id="tot_AS_N" class="form-control" title="Nombre total d'astreinte de nuit"/>
                </td>
            </tr>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Interventions ( nb heure)</h5></th>
                <% for (int i = jourMinDuMois; i <= jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input type="text" min="0" max="1" name="AS_I_<%=i%>" class="form-control" title="Nombre d'heure d'intervention de l'astreinte"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" id="tot_AS_I" class="form-control" title="Nombre total d'heures d'intervention"/>
                </td>
            </tr>

            <%--Ligne de séparation--%>
            <tr>
                <td colspan="<%=colspanTitres%>"></td>
            </tr>

            <%--Ligne de titre pour 'Journées d'absences'--%>
            <tr>
                <td colspan="<%=colspanTitres%>"><h4>Journees d'absences</h4></td>
            </tr>

            <%--Liste des journées d'absences et case de saisie pour chaque jour--%>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Absence - Congé</h5></th>
                <% for (int i = jourMinDuMois; i <= jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input type="text" min="0" max="1" name="AB_C_<%=i%>" class="form-control" title="0 / 0.5 / 1 jour de congé"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" id="tot_AB_C" class="form-control" title="Nombre total de jours de congés"/>
                </td>
            </tr>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Absence - Ferié</h5></th>
                <% for (int i = jourMinDuMois; i <= jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input type="text" min="0" max="1" name="AB_F_<%=i%>" class="form-control" title="0 / 0.5 / 1 jour de congé"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" id="tot_AB_F" class="form-control" title="Nombre total de jours feriés"/>
                </td>
            </tr>

            <%--Ligne de séparation--%>
            <tr>
                <td colspan="<%=colspanTitres%>"></td>
            </tr>

            <%--Ligne des totaux par jour et du total complet du mois--%>
            <tr>
                <th colspan="<%=colspanTH%>"><h5>Total</h5></th>
                <% for (int i = jourMinDuMois; i <= jourMaxDuMois; i++) { %>
                <td class="<%= listWeekend.contains(i) ? "weekend" : "" %>">
                    <input readonly type="text" min="0" max="1" name="tot_col_<%=i%>" class="form-control" title="Total du pourcentage de jour travaillé"/>
                </td>
                <% } %>
                <td>
                    <input readonly type="text" min="0" max="1" id="tot_tot" class="form-control" title="Total de jours imputés"/>
                </td>
            </tr>

        </table>
        <input type="submit" class="btn btn-primary" value="Envoyer"/>
    </fieldset>
</form>