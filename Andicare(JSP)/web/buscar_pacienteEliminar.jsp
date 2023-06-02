<%-- 
    Document   : buscar_paciente.jsp
    Created on : 18-abr-2023, 10:58:13
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
            <form action="./buscar_pacienteEliminar.jsp" method="get">
                <label for="dni">Introduzca el DNI del paciente:</label>
                <input type="text" id="dni" name="dni">
                <button type="submit">Buscar</button>
            </form>
        </main>
        <br>
        <br>
        <br>
        <br>

        <%
            // Recuperar el parámetro "dni" enviado por el formulario
            String dni = request.getParameter("dni");

            // Recuperar el parámetro "nuhsa" enviado por el formulario
            String nuhsa = request.getParameter("nuhsa");

            // Comprobar si el parámetro "dni" no es nulo ni está vacío
            if (dni != null && !dni.equals("")) {
                try {
                    // Crear un objeto de conexión utilizando la clase conexionSQL
                    conexionSQL conexionBD = new conexionSQL();
                    Connection con = null;
                    PreparedStatement statement = null;
                    con = conexionBD.getConexion();

                    // Preparar la consulta SQL para buscar el paciente por DNI
                    PreparedStatement ps = con.prepareStatement("SELECT idPaciente, DNI, nombre, apellidos, Fecha_Nacimiento, Estado_Usuario_LibreView, NUHSA, DNI_Medico FROM Pacientes WHERE DNI = ?");
                    ps.setString(1, dni);

                    // Ejecutar la consulta SQL
                    ResultSet rs = ps.executeQuery();

                    // Comprobar si se ha encontrado un paciente con el DNI especificado
                    if (rs.next()) {
        %>
        <table align = "center">
            <tr>
                <th>idPaciente</th>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Fecha Nacimiento</th>
                <th>Estado Usuario LibreView</th>
                <th>NUHSA</th>
                <th>DNI Médico</th>
            </tr>
            <tr>
                <td><%= rs.getString("idPaciente")%></td>
                <td><%= rs.getString("DNI")%></td>
                <td><%= rs.getString("nombre")%></td>
                <td><%= rs.getString("apellidos")%></td>
                <td><%= rs.getString("Fecha_Nacimiento")%></td>
                <td><%= rs.getString("Estado_Usuario_LibreView")%></td>
                <td><%= rs.getString("NUHSA")%></td>
                <td><%= rs.getString("DNI_Medico")%></td>
            </tr>
        </table>
        <br>
        <form method="post" action="buscar_pacienteEliminar.jsp" id="confirmacion">
            <input type="hidden" name="nuhsa" value="<%= rs.getString("NUHSA")%>">
            <input type="submit" value="Eliminar Paciente" onclick="return confirm('¿Estás seguro de que deseas eliminar este paciente?');">
        </form>
        <%
        } else {
        %>
        <p>No se encontró un paciente con el NUHSA <%= nuhsa%></p>
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

        <%
            // Código para eliminar el paciente seleccionado
            String nuhsaEliminar = request.getParameter("nuhsa");
            if (nuhsaEliminar != null && !nuhsaEliminar.equals("")) {
                try {
                    conexionSQL conexionBD = new conexionSQL();
                    Connection con = null;
                    PreparedStatement ps = null;
                    con = conexionBD.getConexion();

                    ps = con.prepareStatement("DELETE FROM analiticas WHERE NUHSA = ?");
                    ps.setString(1, nuhsaEliminar);
                    int result1 = ps.executeUpdate();

                    ps = con.prepareStatement("DELETE FROM pacientes WHERE NUHSA = ?");
                    ps.setString(1, nuhsaEliminar);
                    int result2 = ps.executeUpdate();
                    if (result2 > 0) {
        %>
        <p>El paciente con NUHSA <%= nuhsaEliminar%> ha sido eliminado correctamente junto a sus analiticas</p>
        <%
        } else {
        %>
        <p>No se pudo eliminar el paciente con NUHSA <%= nuhsaEliminar%></p>
        <%
                    }
                    // Cerrar la conexión a la base de datos
                    ps.close();
                    con.close();
                } catch (Exception e) {
                    out.println(e);
                }
            }
        %>
