<%-- 
    Document   : mostrarPacientes
    Created on : 20-abr-2023, 11:12:00
    Author     : Oday
--%>

<%@page import="config.conexionSQL"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AndiCare - Mostrar Paciente</title>
        <link rel="stylesheet" href="style.css">
        <style>
            nav ul li:hover {
                background: #1b570e;
            }
            table {
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                text-align: left;
                padding: 8px;
                border-bottom: 1px solid #ddd;
            }

            tr:hover {
                background-color: #f5f5f5;
            }

            th {
                background-color: #4CAF50;
                color: white;
            }
        </style>
    </head>
    <body id="baseSesion">
        <header>
            <nav>
                <ul>
                    <li><a href="InicioSesion.jsp">Cuenta</a></li>
                    <li><a href="acerca_de.jsp">Acerca de</a></li>
                    <li><a href="contactanos.jsp">Contáctanos</a></li>
                    <li style= "float: right; padding-right: 2%"><a href="paginaIntermedia.jsp">Volver</a></li>
                </ul>
            </nav>
        </header>

        <main>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <form action="../buscar_pacientes.jsp" method="get">
                <label for="nuhsa">Introduzca el NUHSA del paciente:</label>
                <input type="text" id="nuhsa" name="nuhsa">
                <button type="submit">Buscar</button>
            </form>
            <br>
            <br>
            <br>
            <br>
            <table align="center">
                <tr>
                    <th>idPaciente</th>
                    <th>DNI</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Fecha_Nacimiento</th>
                    <th>Estado_Usuario_LibreView</th>
                    <th> NUHSA </th>
                    <th>DNI_Medico</th>
                </tr>
                <%

                    // Crear un objeto de conexión utilizando la clase conexionSQL
                    conexionSQL conexionBD = new conexionSQL();
                    Connection con = null;
                    PreparedStatement statement = null;
                    con = conexionBD.getConexion();

                    // Preparar la consulta SQL para buscar todos los pacientes
                    PreparedStatement ps = con.prepareStatement("SELECT idPaciente, DNI, nombre, apellidos, Fecha_Nacimiento, "
                            + "Estado_Usuario_LibreView, NUHSA, DNI_Medico FROM Pacientes");

                    // Ejecutar la consulta SQL
                    ResultSet rs = ps.executeQuery();

                    // Iterar sobre el resultado de la consulta y mostrar los datos en la tabla
                    while (rs.next()) {
                        out.print("<tr>");
                        out.print("<td>" + rs.getString("idPaciente") + "</td>");
                        out.print("<td>" + rs.getString("DNI") + "</td>");
                        out.print("<td>" + rs.getString("nombre") + "</td>");
                        out.print("<td>" + rs.getString("apellidos") + "</td>");
                        out.print("<td>" + rs.getString("Fecha_Nacimiento") + "</td>");
                        out.print("<td>" + rs.getString("Estado_Usuario_LibreView") + "</td>");
                        out.print("<td><a href=\"mostrarAnaliticas.jsp?NUHSA=" + rs.getString("NUHSA") + "\">" + rs.getString("NUHSA") + "</a></td>");
                        out.print("<td>" + rs.getString("DNI_Medico") + "</td>");
                        out.print("</tr>");
                    }

                    // Cerrar la conexión y los objetos statement y resultSet
                    rs.close();
                    ps.close();
                    con.close();
                %>
            </table>
        </main>
    </body>
</html>
