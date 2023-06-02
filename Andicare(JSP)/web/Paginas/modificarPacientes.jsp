<%-- 
    Document   : modificarPacientes
    Created on : 20-abr-2023, 11:34:54
    Author     : Oday
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.conexionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AndiCare - Modificar Paciente</title>
        <link rel="stylesheet" href="style.css">
        <style>
            nav ul li:hover {
                background: #1b570e;
            }
            #center-div {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
            }

            #center-div button {
                display: block;
                margin: 10px auto;
                padding: 10px 20px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
            }
            form {
                max-width: 500px;
                margin: 0 auto;
                padding: 20px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            .form-group {
                margin-bottom: 10px;
            }
            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            input[type="text"], input[type="date"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
                font-size: 16px;
            }
            button[type="submit"] {
                display: block;
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                border: none;
                border-radius: 3px;
                color: #fff;
                font-size: 16px;
                cursor: pointer;
            }
            button[type="submit"]:hover {
                background-color: #3d8a40;
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
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>

        <form action="modificarPacientes.jsp" method="post">
            <div class="form-group">
                <label for="dni">DNI:</label>
                <input type="text" name="dni" value="<%= request.getParameter("dni") != null ? request.getParameter("dni") : ""%>" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" name="nombre" value="<%= request.getParameter("nombre") != null ? request.getParameter("nombre") : ""%>" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="apellidos">Apellidos:</label>
                <input type="text" name="apellidos" value="<%= request.getParameter("apellidos") != null ? request.getParameter("apellidos") : ""%>" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="fechaNacimiento">Fecha de nacimiento:</label>
                <input type="date" name="fechaNacimiento" value="<%= request.getParameter("fechaNacimiento") != null ? request.getParameter("fechaNacimiento") : ""%>" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="estadoUsuario">Estado usuario:</label>
                <input type="text" name="estadoUsuario" value="<%= request.getParameter("estadoUsuario") != null ? request.getParameter("estadoUsuario") : ""%>" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="dniMedico">DNI médico:</label>
                <input type="text" name="dniMedico" value="<%= request.getParameter("dniMedico") != null ? request.getParameter("dniMedico") : ""%>" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary" name="guardarCambios">Guardar cambios</button>
        </form>


    </body>
</html>
<%-- Formulario para editar un paciente existente --%>
<!-- Formulario para editar un paciente existente -->

<%
    //El boton 
    if (request.getParameter("guardarCambios") != null) {
        // Aquí va todo el código Java que deseas ejecutar cuando se pulsa el botón "Guardar cambios"

        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String estadoUsuario = request.getParameter("estadoUsuario");
        String dniMedico = request.getParameter("dniMedico");

        java.sql.Date fechaNacimientoSql;
        if (fechaNacimiento == null || fechaNacimiento.isEmpty()) {
            fechaNacimientoSql = java.sql.Date.valueOf("0001-01-01");
        } else {
            fechaNacimientoSql = java.sql.Date.valueOf(fechaNacimiento);
        }
        String dniMedicoSql;
        if (dniMedico == null || dniMedico.isEmpty()) {
            dniMedicoSql = "0";
        } else {
            dniMedicoSql = dniMedico;
        }

        // Crear un objeto de conexión a la base de datos
        conexionSQL conexionBD = new conexionSQL();
        Connection con = null;
        con = conexionBD.getConexion();

        // Preparar la consulta SQL para actualizar los datos del paciente
        PreparedStatement ps = con.prepareStatement("UPDATE pacientes SET DNI = ?, nombre = ?, apellidos = ?, Fecha_Nacimiento = ?, "
                + "Estado_Usuario_LibreView = ?, DNI_Medico = ? WHERE DNI = ?");
        ps.setString(1, dni);
        ps.setString(2, nombre);
        ps.setString(3, apellidos);
        ps.setDate(4, fechaNacimientoSql);
        ps.setString(5, estadoUsuario);
        ps.setString(6, dniMedicoSql);
        ps.setString(7, dni);

        // Ejecutar la consulta SQL
        int resultado = ps.executeUpdate();

        // Comprobar si la actualización ha sido exitosa
        if (resultado > 0) {
            out.println("<p>Los datos del paciente han sido actualizados correctamente.</p>");
        } else {
            out.println("<p>No se ha podido actualizar los datos del paciente.</p>");
        }
    }
%>