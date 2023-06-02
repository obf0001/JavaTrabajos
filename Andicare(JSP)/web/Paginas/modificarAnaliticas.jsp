<%-- 
    Document   : modificarAnaliticas
    Created on : 17-may-2023, 13:10:26
    Author     : Oday
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="config.conexionSQL"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>AndiCare</title>
        <link rel="stylesheet" href="style.css">
        <style>
            .not-editable-text {
                color: red;
            }
            #NUHSAPac {
                background-color: #f2f2f2; /* Color de fondo grisáceo */
                opacity: 0.5; /* Opacidad baja */
                pointer-events: none; /* Deshabilitar eventos de puntero */
            }
            nav ul li:hover {
                background: #1b570e;
            }
            body {
                background-color: #f8f8f8;
                font-size: 16px;
                font-family: Calibri;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                border-spacing: 0;
            }
            td {
                border: 1px solid #ddd;
                padding: 8px;
            }
            #titulo {
                font-size: 24px;
                margin: 20px 0;
                text-align: center;
            }
            input[type="submit"] {
                background: rgba(32, 104, 16,1);
                color: #fff;
                font-size: 16px;
                padding: 10px;
                border: none;
                cursor: pointer;
                display: block; /* Aniadir esta propiedad para que el botón ocupe todo el ancho */
                margin: auto; /* Aniadir esta propiedad para centrar el botón */
            }
            input[type="submit"]:hover {
                background-color: #555;
            }

        </style>
    </head>
    <%
        // Obtener los parámetros de la URL del script de JavaScript de la página anterior
        String nuhsa = request.getParameter("NUHSA");
        String fechaAntigua = request.getParameter("Fecha_Analitica");
        String DNIMedicoAntiguo = request.getParameter("DNI_Medico");
    %>

    <!-- Aquí puedes utilizar las variables en tu código JSP -->

    <body id="baseSesion">
        <header>
            <nav>
                <ul>
                    <li>
                        <a class="nav-link" href="InicioSesion.jsp">Cuenta</a>
                    </li>
                    <li>
                        <a class="nav-link" href="acerca_de.jsp">Acerca de</a>
                    </li>
                    <li>
                        <a class="nav-link" href="contactanos.jsp">Contáctanos</a>
                    </li>
                    <li style= "float: right; padding-right: 2%"><a href="paginaIntermedia.jsp">Volver</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <br>
        <br>
        <br>
        <main class="container">
            <h1 class="titulo">Edite la analítica</h1>
            <hr>
            <form action="modificarAnaliticas.jsp" method="post">
                <table class="table">
                    <tbody>
                        <tr>
                            <td><label for="glucosaPromNuevo">Glucosa promedio:</label></td>
                            <td><input type="number" step="0.01" name="glucosaPromNuevo" id="glucosaPromNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="promLectDiaNuevo">Promedio de lectura/Vistas por día:</label></td>
                            <td><input type="number" step="0.01" name="promLectDiaNuevo" id="promLectDiaNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="porcObjetivoNuevo">% en el objetivo:</label></td>
                            <td><input type="number" step="0.01" name="porcObjetivoNuevo" id="porcObjetivoNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="sensorTiemPorcActNuevo"> Sensor de tiempo esta % activo</label></td>
                            <td><input type="number" step="0.01" name="sensorTiemPorcActNuevo" id="sensorTiemPorcActNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="sensorDurGlucBajaNuevo">Sensor de duración promedio de eventos de glucosa baja</label></td>
                            <td><input type="number" step="0.01" name="sensorDurGlucBajaNuevo" id="sensorDurGlucBajaNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="porcDebajoObjNuevo">% por debajo del objetivo</label></td>
                            <td><input type="number" step="0.01" name="porcDebajoObjNuevo" id="porcDebajoObjNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="porcDebUmbrEvenHipoNuevo">% por debajo del umbral de eventos de hipoglucemia</label></td>
                            <td><input type="number" step="0.01" name="porcDebUmbrEvenHipoNuevo" id="porcDebUmbrEvenHipoNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="porcEncUmbrEvenHiperNuevo">% por encima del umbral de eventos de hiperglucemia</label></td>
                            <td><input type="number" step="0.01" name="porcEncUmbrEvenHiperNuevo" id="porcEncUmbrEvenHiperNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="coeficienteVariacionNuevo">Coeficiente de variación</label></td>
                            <td><input type="number" step="0.01" name="coeficienteVariacionNuevo" id="coeficienteVariacionNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="evenGlucBajaNuevo">Eventos de glucosa baja</label></td>
                            <td><input type="number" step="0.01" name="evenGlucBajaNuevo" id="evenGlucBajaNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="promevenGlucBajaDiaNuevo">Promedio de eventos de glucosa baja por día</label></td>
                            <td><input type="number" step="0.01" name="promevenGlucBajaDiaNuevo" id="promevenGlucBajaDiaNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="IndiNuevo">Indicador de gestión de glucosa (GMI)%</label></td>
                            <td><input type="number" step="0.01" name="IndiNuevo" id="IndiNuevo"></td>
                        </tr>
                        <tr>
                            <td><label for="fechAnaNuevo">Fecha de la Analítica</label></td>
                            <td><input type="date" name="fechAnaNuevo" id="fechAnaNuevo" required value="<%=fechaAntigua%>"></td>
                        </tr>
                        <tr>
                            <td><label for="NUHSAPac">NUHSA <span class="not-editable-text">*No editable*</span></label></td>
                            <td><input type="text" name="NUHSAPac" id="NUHSAPac" readonly value="<%=nuhsa%>"></td>
                        </tr>
                        <tr>
                            <td><label for="dniMedicoNuevo">DNI del Medico</label></td>
                            <td><input type="text" name="dniMedicoNuevo" id="dniMedicoNuevo" required value="<%=DNIMedicoAntiguo%>"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button type="submit" class="btn btn-primary" name="guardarCambios">Guardar cambios</button></td>
                        </tr>
                </table>
            </form>
    </body>
</html>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="config.conexionSQL"%>
<%@ page import="java.sql.Date"%>
<%
    //El boton 
    if (request.getParameter("guardarCambios") != null) {
        // Aquí va todo el código Java que deseas ejecutar cuando se pulsa el botón "Guardar cambios"

//Inicializamos las variables para que borren su contenido antes de meter nuevos
        String fechAnaPacStr = "0001-01-01";
        String DNIMedicoNuevo = "";
        // Obtener los datos ingresados por el usuario desde el formulario
        DNIMedicoNuevo = request.getParameter("dniMedicoNuevo");

        //Inicializamos y comprobamos que no es nulo o vacio
        float glucosaPromNuevo = 0;
        String glucosaPromPacStr = request.getParameter("glucosaPromNuevo");
        if (glucosaPromPacStr != null && !glucosaPromPacStr.isEmpty()) {
            glucosaPromNuevo = Float.parseFloat(glucosaPromPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float promLectDiaNuevo = 0;
        String promLectDiaPacStr = request.getParameter("promLectDiaNuevo");
        if (promLectDiaPacStr != null && !promLectDiaPacStr.isEmpty()) {
            promLectDiaNuevo = Float.parseFloat(promLectDiaPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float porcObjetivoNuevo = 0;
        String porcObjetivoPacStr = request.getParameter("porcObjetivoNuevo");
        if (porcObjetivoPacStr != null && !porcObjetivoPacStr.isEmpty()) {
            porcObjetivoNuevo = Float.parseFloat(porcObjetivoPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float sensorTiemPorcActNuevo = 0;
        String sensorTiemPorcActPacStr = request.getParameter("sensorTiemPorcActNuevo");
        if (sensorTiemPorcActPacStr != null && !sensorTiemPorcActPacStr.isEmpty()) {
            sensorTiemPorcActNuevo = Float.parseFloat(sensorTiemPorcActPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float sensorDurGlucBajaNuevo = 0;
        String sensorDurGlucBajaPacStr = request.getParameter("sensorDurGlucBajaNuevo");
        if (sensorDurGlucBajaPacStr != null && !sensorDurGlucBajaPacStr.isEmpty()) {
            sensorDurGlucBajaNuevo = Float.parseFloat(sensorDurGlucBajaPacStr);
        }

        float porcDebajoObjNuevo = 0;
        String porcDebajoObjPacStr = request.getParameter("porcDebajoObjNuevo");
        if (porcDebajoObjPacStr != null && !porcDebajoObjPacStr.isEmpty()) {
            porcDebajoObjNuevo = Float.parseFloat(porcDebajoObjPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float porcDebUmbrEvenHipoNuevo = 0;
        String porcDebUmbrEvenHipoPacStr = request.getParameter("porcDebUmbrEvenHipoNuevo");
        if (porcDebUmbrEvenHipoPacStr != null && !porcDebUmbrEvenHipoPacStr.isEmpty()) {
            porcDebUmbrEvenHipoNuevo = Float.parseFloat(porcDebUmbrEvenHipoPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float porcEncUmbrEvenHiperNuevo = 0;
        String porcEncUmbrEvenHiperPacStr = request.getParameter("porcEncUmbrEvenHiperNuevo");
        if (porcEncUmbrEvenHiperPacStr != null && !porcEncUmbrEvenHiperPacStr.isEmpty()) {
            porcEncUmbrEvenHiperNuevo = Float.parseFloat(porcEncUmbrEvenHiperPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float coeficienteVariacionNuevo = 0;
        String coeficienteVariacionPacStr = request.getParameter("coeficienteVariacionNuevo");
        if (coeficienteVariacionPacStr != null && !coeficienteVariacionPacStr.isEmpty()) {
            coeficienteVariacionNuevo = Float.parseFloat(coeficienteVariacionPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float evenGlucBajaNuevo = 0;
        String evenGlucBajaPacStr = request.getParameter("evenGlucBajaNuevo");
        if (evenGlucBajaPacStr != null && !evenGlucBajaPacStr.isEmpty()) {
            evenGlucBajaNuevo = Float.parseFloat(evenGlucBajaPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float promevenGlucBajaDiaNuevo = 0;
        String promevenGlucBajaDiaPacStr = request.getParameter("promevenGlucBajaDiaNuevo");
        if (promevenGlucBajaDiaPacStr != null && !promevenGlucBajaDiaPacStr.isEmpty()) {
            promevenGlucBajaDiaNuevo = Float.parseFloat(promevenGlucBajaDiaPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float IndiNuevo = 0;
        String IndiPacStr = request.getParameter("IndiNuevo");
        if (IndiPacStr != null && !IndiPacStr.isEmpty()) {
            IndiNuevo = Float.parseFloat(IndiPacStr);

        }

        //pasamos String a Date y aseguramos que si es nulo se quede con el valor de 0001-01-01
        if (request.getParameter("fechAnaNuevo") != null && !request.getParameter("fechAnaNuevo").isEmpty()) {
            fechAnaPacStr = request.getParameter("fechAnaNuevo");
        }
        Date fechAnaNuevoDate = java.sql.Date.valueOf(fechAnaPacStr);

        java.sql.Date fechaAntiguaSql;
        if (fechaAntigua == null || fechaAntigua.isEmpty()) {
            fechaAntiguaSql = java.sql.Date.valueOf("0001-01-01");
        } else {
            fechaAntiguaSql = java.sql.Date.valueOf(fechaAntigua);
        }

        String dniMedicoSql;
        if (DNIMedicoNuevo == null || DNIMedicoNuevo.isEmpty()) {
            dniMedicoSql = "0";
        } else {
            dniMedicoSql = DNIMedicoNuevo;
        }

        // Conectarse a la base de datos
        conexionSQL conexionBD = new conexionSQL();
        Connection con = conexionBD.getConexion();

        // Preparar la consulta SQL para actualizar los datos del paciente
        PreparedStatement ps = con.prepareStatement("UPDATE analiticas SET Glucosa_Promedio = ?, Prom_Vistas_Dias = ?, Porc_Objetivo = ?, Sensor_tiempo_Porc_Act = ?, "
                + "Sensor_Duracion_Promedio_Glucosa_Baja = ?, Porc_Debajo_Objetivo = ?, Porc_Debajo_Eventos_Hipoglucemia = ?, "
                + "Porc_Encima_Eventos_Hiperglucemia = ?, Coeficiente_Variacion = ?, Eventos_Glucosa_Baja = ?, Prom_Glucosa_Baja_Dia = ?, "
                + "Ind_Gestion_Glucosa_GMI_Porc = ?, Fecha_Analitica = ?, Tecnico_implicado = ? WHERE NUHSA = ? AND Fecha_Analitica = ?;");

        // Asignar los valores a los parámetros de la consulta SQL
        ps.setFloat(1, glucosaPromNuevo);
        ps.setFloat(2, promLectDiaNuevo);
        ps.setFloat(3, porcObjetivoNuevo);
        ps.setFloat(4, sensorTiemPorcActNuevo);
        ps.setFloat(5, sensorDurGlucBajaNuevo);
        ps.setFloat(6, porcDebajoObjNuevo);
        ps.setFloat(7, porcDebUmbrEvenHipoNuevo);
        ps.setFloat(8, porcEncUmbrEvenHiperNuevo);
        ps.setFloat(9, coeficienteVariacionNuevo);
        ps.setFloat(10, evenGlucBajaNuevo);
        ps.setFloat(11, promevenGlucBajaDiaNuevo);
        ps.setFloat(12, IndiNuevo);
        ps.setDate(13, fechAnaNuevoDate);
        ps.setString(14, dniMedicoSql);
        ps.setString(15, nuhsa);
        ps.setDate(16, fechaAntiguaSql);

        // Ejecutar la consulta SQL
        int resultado = ps.executeUpdate();

        // Comprobar si la actualización ha sido exitosa
        if (resultado > 0) {
            out.println("<p>Los datos de la Analitica han sido actualizados correctamente.</p>");
        } else {
            out.println("<p>No se ha podido actualizar los datos de la Analitica.</p>");
        }
        // Cerrar la conexión a la base de datos
        ps.close();
        con.close();

    }
%>