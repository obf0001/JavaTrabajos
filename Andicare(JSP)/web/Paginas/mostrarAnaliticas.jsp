<%-- 
    Document   : mostrarAnaliticas
    Created on : 26-abr-2023, 11:30:34
    Author     : Oday
--%>

<%@ page import="config.conexionSQL" %>
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
            <script>
                window.onload = function () {
                    // Obtener todas las filas de la tabla
                    var filas = document.getElementsByTagName("tr");

                    // Recorrer las filas y asignar el evento de clic
                    for (var i = 1; i < filas.length; i++) { // Comenzar en 1 para omitir la fila de encabezados
                        filas[i].addEventListener("click", function () {
                            seleccionarFila(this);
                        });
                    }
                };

                function seleccionarFila(fila) {
                    // Obtener los datos de la fila seleccionada
                    var nuhsa = fila.cells[0].innerHTML;
                    var fechaAnalitica = fila.cells[1].innerHTML;
                    var dniMedico = fila.cells[2].innerHTML;
                    // Redirigir a la página para modificar los datos con los parámetros correspondientes
                    window.location.href = "modificarAnaliticas.jsp?NUHSA=" + nuhsa + "&Fecha_Analitica=" + fechaAnalitica + "&DNI_Medico=" + dniMedico;
                }
            </script>

            <br>
            <br>
            <br>
            <br>
            <table align="center">
                <tr>
                    <th>NUHSA</th>
                    <th>Fecha_Analitica</th>
                    <th>DNI Médico</th>
                    <th>Glucosa_Promedio</th>
                    <th>Prom_Vistas_Dias</th>
                    <th>Porc_Objetivo</th>
                    <th>Sensor_tiempo_Porc_Act</th> 
                    <th>Sensor_Duracion_Promedio_Glucosa_Baja</th> 
                    <th>Porc_Debajo_Objetivo</th> 
                    <th>Porc_Debajo_Eventos_Hipoglucemia</th> 
                    <th>Porc_Encima_Eventos_Hiperglucemia</th> 
                    <th>Coeficiente_Variacion</th> 
                    <th>Eventos_Glucosa_Baja</th> 
                    <th>Prom_Glucosa_Baja_Dia</th> 
                    <th>Ind_Gestion_Glucosa_GMI_Porc</th> 
                </tr>

                <%
                    String nuhsa = request.getParameter("NUHSA");
                    // Crear un objeto de conexión utilizando la clase conexionSQL
                    conexionSQL conexionBD = new conexionSQL();
                    Connection con = null;
                    PreparedStatement statement = null;
                    con = conexionBD.getConexion();

                    // Preparar la consulta SQL para buscar todas las analíticas del paciente
                    PreparedStatement ps = con.prepareStatement("SELECT Glucosa_Promedio, Prom_Vistas_Dias, Porc_Objetivo, "
                            + "Sensor_tiempo_Porc_Act, Sensor_Duracion_Promedio_Glucosa_Baja, Porc_Debajo_Objetivo, Porc_Debajo_Eventos_Hipoglucemia, "
                            + "Porc_Encima_Eventos_Hiperglucemia, Coeficiente_Variacion, Eventos_Glucosa_Baja, Prom_Glucosa_Baja_Dia, Ind_Gestion_Glucosa_GMI_Porc, NUHSA, Fecha_Analitica,"
                            + "Tecnico_implicado FROM analiticas WHERE NUHSA = ? ;");

                    ps.setString(1, nuhsa);

                    // Ejecutar la consulta SQL
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        out.print("<tr>");
                        out.print("<td>" + rs.getString("NUHSA") + "</td>");
                        out.print("<td>" + rs.getString("Fecha_Analitica") + "</td>");
                        out.print("<td>" + rs.getString("Tecnico_implicado") + "</td>");
                        out.print("<td>" + rs.getString("Glucosa_Promedio") + "</td>");
                        out.print("<td>" + rs.getString("Prom_Vistas_Dias") + "</td>");
                        out.print("<td>" + rs.getString("Porc_Objetivo") + "</td>");
                        out.print("<td>" + rs.getString("Sensor_tiempo_Porc_Act") + "</td>");
                        out.print("<td>" + rs.getString("Sensor_Duracion_Promedio_Glucosa_Baja") + "</td>");
                        out.print("<td>" + rs.getString("Porc_Debajo_Objetivo") + "</td>");
                        out.print("<td>" + rs.getString("Porc_Debajo_Eventos_Hipoglucemia") + "</td>");
                        out.print("<td>" + rs.getString("Porc_Encima_Eventos_Hiperglucemia") + "</td>");
                        out.print("<td>" + rs.getString("Coeficiente_Variacion") + "</td>");
                        out.print("<td>" + rs.getString("Eventos_Glucosa_Baja") + "</td>");
                        out.print("<td>" + rs.getString("Prom_Glucosa_Baja_Dia") + "</td>");
                        out.print("<td>" + rs.getString("Ind_Gestion_Glucosa_GMI_Porc") + "</td>");
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
