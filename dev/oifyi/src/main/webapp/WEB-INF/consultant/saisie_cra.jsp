<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<div class="container">
    <%-- List<Mission> missions = (List<Mission>) request.getAttribute("missions");  --%>
    <form class="form-horizontal">
        <div class="table">
            <table class="table table-bordered table-stripped table-highlight">
                <thead>
                <td colspan="3"></td>
                <% for (int i = 1; i <= 31; i++) { %>
                <th><%=  i %>
                </th>
                <% } %>
                <th>
                    Total
                </th>

                </thead>
                <tbody>
                <tr>
                    <td colspan="7"><h4>Journees facturables</h4></td>
                </tr>
                <%-- Tableau mission --%>

                <tr>
                    <td colspan="3">
                        <h5>CACIB Orchestrade</h5>
                            <% for (int a = 1; a <= 31; a++) { %>
                    <th><input type="text" class="form-control"/>
                    </th>
                    <% } %>
                    </td>
                </tr>

                <%-- Fin tableau mission --%>

                <%-- Tableau mission --%>

                <tr>
                    <td colspan="3">
                        <h5>USO mon amour</h5>
                            <% for (int i = 1; i <= 31; i++) { %>
                    <th><input type="text" class="form-control"/>
                    </th>
                    <% } %>
                    </td>
                </tr>

                <%-- Fin tableau mission --%>

                <%-- Tableau mission --%>

                <tr>
                    <td colspan="3">
                        <h5>BLA BLA BLA</h5>
                            <% for (int i = 1; i <= 31; i++) { %>
                    <th><input type="text" class="form-control"/>
                    </th>
                    <% } %>
                    </td>
                </tr>

                <%-- Fin tableau mission --%>

                <%-- Tableau mission --%>

                <tr>
                    <td colspan="3">
                        <h5> Bla bla bla </h5>

                            <% for (int i = 1; i <= 31; i++) { %>
                    <th><input type="text" class="form-control"/>
                    </th>
                    <% } %>
                    </td>
                </tr>

                <%-- Fin tableau mission --%>
                </br>
                <tr>
                    <td colspan="7">
                        <h4> Astreintes </h4>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <h5> Astreintes de jour (nb jour) </h5>
                            <% for (int i = 1; i <= 31; i++) { %>
                    <th><input type="text" class="form-control"/>
                    </th>
                    <% } %>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <h5> Astreintes de nuit (nb jour) </h5>
                            <% for (int i = 1; i <= 31; i++) { %>
                    <th><input type="text" class="form-control"/>
                    </th>
                    <% } %>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <h5> Interventions ( nb heure) </h5>
                            <% for (int i = 1; i <= 31; i++) { %>
                    <th><input type="text" class="form-control"/>
                    </th>
                    <% } %>
                    </td>
                </tr>
                <tr>
                    <td colspan="7"><h4>Journees d'absences </h4></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <h5> Absence - Congé </h5>
                            <% for (int i = 1; i <= 31; i++) { %>
                    <th><input type="text" class="form-control"/>
                    </th>
                    <% } %>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <h5> Absence - Férié </h5>
                            <% for (int i = 1; i <= 31; i++) { %>
                    <th><input type="text" class="form-control"/>
                    </th>
                    <% } %>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <INPUT TYPE="submit" NAME="nom" VALUE=" Envoyer ">
    </form>
</div>