<%-- 
    Document   : intermediaAgregar
    Created on : 26-abr-2023, 10:23:20
    Author     : Oday
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AndiCare - Opciones</title>
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

        <div id="center-div">
            <button onclick="window.location.href = 'cargaPacientes.jsp'">Agregar Paciente y Analítica</button>
            <button onclick="window.location.href = 'cargarAnaliticas.jsp'">Agregar Analítica</button>
        </div>
    </body>
</html>
