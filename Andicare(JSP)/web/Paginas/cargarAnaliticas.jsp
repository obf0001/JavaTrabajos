<%-- 
    Document   : cargarAnaliticas
    Created on : 26-abr-2023, 10:13:59
    Author     : Oday
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>AndiCare</title>
        <link rel="stylesheet" href="style.css">
        <style>
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
                display: block; /* Añadir esta propiedad para que el botón ocupe todo el ancho */
                margin: auto; /* Añadir esta propiedad para centrar el botón */
            }
            input[type="submit"]:hover {
                background-color: #555;
            }

        </style>
    </head>
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
            <h1 class="titulo">Escriba los datos de la analítica</h1>
            <hr>
            <form action="../procesarAnaliticas.jsp" method="post" class="form">
                <table class="table">
                    <tbody>
                        <tr>
                            <td><label for="glucosaPromPac">Glucosa promedio:</label></td>
                            <td><input type="number" name="glucosaPromPac" id="glucosaPromPac"></td>

                        </tr>
                        <tr>
                            <td><label for="promLectDiaPac">Promedio de lectura/Vistas por día:</label></td>
                            <td><input type="number" name="promLectDiaPac" id="promLectDiaPac"></td>
                        </tr>
                        <tr>
                            <td><label for="porcObjetivoPac">% en el objetivo:</label></td>
                            <td><input type="number" name="porcObjetivoPac" id="porcObjetivoPac"></td>
                        </tr>
                        <tr>
                            <td><label for="sensorTiemPorcActPac"> Sensor de tiempo esta % activo</label></td>
                            <td><input type="number" name="sensorTiemPorcActPac" id="sensorTiemPorcActPac"></td>
                        </tr>
                        <tr>
                            <td><label for="sensorDurGlucBajaPac">Sensor de duración promedio de eventos de glucosa baja</label></td>
                            <td><input type="number" name="sensorDurGlucBajaPac" id="sensorDurGlucBajaPac"></td>
                        </tr>
                        <tr>
                            <td><label for="porcDebajoObjPac">% por debajo del objetivo</label></td>
                            <td><input type="number" name="porcDebajoObjPac" id="porcDebajoObjPac"></td>
                        </tr>
                        <tr>
                            <td><label for="porcDebUmbrEvenHipoPac">% por debajo del umbral de eventos de hipoglucemia</label></td>
                            <td><input type="number" name="porcDebUmbrEvenHipoPac" id="porcDebUmbrEvenHipoPac"></td>
                        </tr>
                        <tr>
                            <td><label for="porcEncUmbrEvenHiperPac">% por encima del umbral de eventos de hiperglucemia</label></td>
                            <td><input type="number" name="porcEncUmbrEvenHiperPac" id="porcEncUmbrEvenHiperPac"></td>
                        </tr>
                        <tr>
                            <td><label for="coeficienteVariacionPac">Coeficiente de variación</label></td>
                            <td><input type="number" step="0.01" name="coeficienteVariacionPac" id="coeficienteVariacionPac"></td>
                        </tr>
                        <tr>
                            <td><label for="evenGlucBajaPac">Eventos de glucosa baja</label></td>
                            <td><input type="number" name="evenGlucBajaPac" id="evenGlucBajaPac"></td>
                        </tr>
                        <tr>
                            <td><label for="promevenGlucBajaDiaPac">Promedio de eventos de glucosa baja por día</label></td>
                            <td><input type="number" step="0.01" name="promevenGlucBajaDiaPac" id="promevenGlucBajaDiaPac"></td>
                        </tr>
                        <tr>
                            <td><label for="IndiPac">Indicador de gestión de glucosa (GMI)%</label></td>
                            <td><input type="number" step="0.01" name="IndiPac" id="IndiPac"></td>
                        </tr>
                        <tr>
                            <td><label for="fechAnaPac">Fecha de la Analítica</label></td>
                            <td><input type="date" name="fechAnaPac" id="fechAnaPac" required></td>
                        </tr>
                        <tr>
                            <td><label for="NUHSAPac">NUHSA</label></td>
                            <td><input type="text" name="NUHSAPac" id="NUHSAPac" required></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="enviarBoton" id="enviarBoton" value="Enviar"></td>
                        </tr>
                </table>
            </form>
    </body>
</html>