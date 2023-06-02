<%-- 
    Document   : buscar_pacientes
    Created on : 27-abr-2023, 12:48:43
    Author     : Oday
--%>

<%@page import="java.sql.Connection"%>
<%@page import="config.conexionSQL"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AndiCare - Buscar pacientes</title>
        <link rel="stylesheet" href="Paginas/style.css">
        <style>
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
                    <li><a href="./Paginas/InicioSesion.jsp">Cuenta</a></li>
                    <li><a href="./Paginas/acerca_de.jsp">Acerca de</a></li>
                    <li><a href="./Paginas/contactanos.jsp">Contáctanos</a></li>
                    <li style= "float: right; padding-right: 2%"><a href="./Paginas/paginaIntermedia.jsp">Volver</a></li>
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
            <form action="./buscar_pacientes.jsp" method="get">
                <label for="nuhsa">Introduzca el NUHSA del paciente:</label>
                <input type="text" id="nuhsa" name="nuhsa">
                <button type="submit">Buscar</button>
            </form>
        </main>
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
                // Recuperar el parámetro "nuhsa" enviado por el formulario
                String nuhsa = request.getParameter("nuhsa");

                // Comprobar si el parámetro "nuhsa" no es nulo ni está vacío
                if (nuhsa != null && !nuhsa.equals("")) {
                    try {
                        // Crear un objeto de conexión utilizando la clase conexionSQL
                        conexionSQL conexionBD = new conexionSQL();
                        Connection con = null;
                        PreparedStatement statement = null;
                        con = conexionBD.getConexion();

                        // Preparar la consulta SQL para buscar el paciente por NUHSA
                        PreparedStatement ps = con.prepareStatement("SELECT idPaciente, DNI, nombre, apellidos, Fecha_Nacimiento, Estado_Usuario_LibreView, NUHSA, DNI_Medico FROM Pacientes WHERE NUHSA = ?");
                        ps.setString(1, nuhsa);

                        // Ejecutar la consulta SQL
                        ResultSet rs = ps.executeQuery();

                        // Comprobar si se ha encontrado un paciente con el NUHSA especificado
                        if (rs.next()) {
                            // Iterar sobre el resultado de la consulta y mostrar los datos en la tabla
                            do {
                                out.print("<tr>");
                                out.print("<td>" + rs.getString("idPaciente") + "</td>");
                                out.print("<td>" + rs.getString("DNI") + "</td>");
                                out.print("<td>" + rs.getString("nombre") + "</td>");
                                out.print("<td>" + rs.getString("apellidos") + "</td>");
                                out.print("<td>" + rs.getString("Fecha_Nacimiento") + "</td>");
                                out.print("<td>" + rs.getString("Estado_Usuario_LibreView") + "</td>");
                                out.print("<td><a href=\"./Paginas/mostrarAnaliticas.jsp?NUHSA=" + rs.getString("NUHSA") + "\">" + rs.getString("NUHSA") + "</a></td>");
                                out.print("<td>" + rs.getString("DNI_Medico") + "</td>");
                                out.print("</tr>");
                            } while (rs.next()); // Continuar mientras haya más filas de resultados

                            // Cerrar la conexión y los objetos statement y resultSet
                            rs.close();
                            ps.close();
                            con.close();
            %>
        </table>
        <br>
        <%
        } else {
        %>
        <p>No se encontró un paciente con el NUHSA: <%= nuhsa%></p>
        <%
                    }
                    // Cerrar la conexión a la base de datos
                    rs.close();
                    ps.close();
                    con.close();
                } catch (Exception e) {
                    out.println(e);
                }
            }
        %>