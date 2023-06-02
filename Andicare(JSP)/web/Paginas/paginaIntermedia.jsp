<%-- 
    Document   : paginaIntermedia
    Created on : 18-abr-2023, 10:04:25
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
            .imagen-boton {
                display: none; /* ocultar la imagen */
            }
            button {
                border: none; /* quitar el borde del botón */
                background-position: center bottom;/* establecer la imagen de fondo */
                background-repeat: no-repeat; /* evitar que se repita la imagen */
                background-size: cover; /* ajustar la imagen al tamaño del botón */
                width: 200px; /* ajustar el ancho del botón según tus necesidades */
                height: 50px; /* ajustar la altura del botón según tus necesidades */
            }
            body {
                font-family: Calibri;
                margin: 0;
                padding: 0;
            }
            nav ul li:hover {
                background: #1b570e;
            }
            #boton1, #boton2, #boton3, #boton4 {
                position: absolute;
                width: 20%;
                height: 25%;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
            }
            #boton1 {
                top: 20%;
                left: 25%;
                background-image: url('./imagenes/agregarPaciente.png');
            }
            #boton2 {
                top: 20%;
                right: 25%;
                background-image: url('./imagenes/modificarPaciente.png');
            }
            #boton3 {
                bottom: 20%;
                left: 25%;
                background-image: url('./imagenes/consultarPaciente.png');
            }
            #boton4 {
                bottom: 20%;
                right: 25%;
                background-image: url('./imagenes/eliminarPaciente.png');
            }
            #boton1:hover, #boton2:hover, #boton3:hover, #boton4:hover {
                background-color: #1b570e;
            }
            #texto1, #texto2, #texto3, #texto4 {
                position: absolute;
                color: white;
                font-size: 20px;
                text-align: center;
                top: 270px;
            }
            #texto1 {
                -webkit-text-stroke: 0.5px black;
                font-size: 24px;
                color: white;
                left: 25%;
                top: 41%;
            }
            #texto2 {
                -webkit-text-stroke: 0.5px black;
                font-size: 24px;
                color: white;
                right: 25%;
                top: 41%;
            }
            #texto3 {
                -webkit-text-stroke: 0.5px black;
                font-size: 24px;
                color: white;
                top: 76%;
                left: 25%;
            }
            #texto4 {
                -webkit-text-stroke: 0.5px black;
                font-size: 24px;
                color: white;
                top: 76%;
                right: 26%
            }
            .imagen-boton {
                width: 150px;
                height: 150px;
                margin: auto;
            }
        </style>
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="cuenta.jsp">Cuenta</a></li>
                    <li><a href="acerca_de.jsp">Acerca de</a></li>
                    <li><a href="contactanos.jsp">Contáctanos</a></li>
                </ul>
            </nav>
        </header>
        <%
            String tipo = (String) session.getAttribute("tipo");
            switch (tipo.toLowerCase()) {
                case "tecnico":
        %>
        <div>
            <button id="boton3" onclick="window.location.href = 'mostrarPacientes.jsp'">
            </button>
            <div id="texto3">Mostrar Pacientes o Analíticas</div>
        </div>
        <%
                break;
            case "enfermero":
        %>
        <div>

            <button id="boton1" onclick="window.location.href = 'intermediaAgregar.jsp'">
            </button>
            <div id="texto1">Agregar Pacientes o Analíticas</div>

            <button id="boton2" onclick="window.location.href = 'intermediaModificar.jsp'">
            </button>
            <div id="texto2">Modificar Pacientes o Analíticas</div>

            <button id="boton3" onclick="window.location.href = 'mostrarPacientes.jsp'">
            </button>
            <div id="texto3">Mostrar Pacientes o Analíticas</div>
            <%
                    break;
                case "medico":
            %>
            <div id="center-div">
                <div>
                    <button id="boton1" onclick="window.location.href = 'intermediaAgregar.jsp'">
                    </button>
                    <div id="texto1">Agregar Pacientes o Analíticas</div>

                    <button id="boton2" onclick="window.location.href = 'intermediaModificar.jsp'">
                    </button>
                    <div id="texto2">Modificar Pacientes o Analíticas</div>

                    <button id="boton3" onclick="window.location.href = 'mostrarPacientes.jsp'">
                    </button>
                    <div id="texto3">Mostrar Pacientes o Analíticas</div>

                    <button id="boton4" onclick="window.location.href = 'elegirPacienteEliminar.jsp'">
                    </button>
                    <div id="texto4">Eliminar Pacientes y Analíticas</div>
                </div>

            </div>

            <%       break;
            default:%>
            <div id="center-div">
                <%
                            // El inicio de sesión falló, mostrar un mensaje de error y volver a la página de inicio de sesión
                            out.println("No se le ha seleccionado ningún tipo de empleado, espere o contacte con el servicio de ayuda");
                    }
                %>
            </div>
    </body>
</html>
