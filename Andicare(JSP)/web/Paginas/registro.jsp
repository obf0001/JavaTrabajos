<%-- 
    Document   : registro
    Created on : 03-may-2023, 10:47:17
    Author     : Oday
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AndiCare - Registrarse</title>
        <style>
            nav ul li:hover {
                background: #1b570e;
            }
            body {
                font-family: Calibri;
                background-color: #f7f7f7;
                text-align: center;
                padding: 20px;
            }
            header {
                background-color: #206810;
                color: white;
                padding: 10px;
            }
            nav ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
            }
            nav li {
                float: left;
            }
            nav a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }
            nav a:hover {
                background-color: #1b570e;
            }
            h2 {
                color: #206810;
            }
            form {
                margin: 0 auto;
                width: 50%;
                background-color: white;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0px 0px 10px #d3d3d3;
            }
            label {
                display: block;
                margin-bottom: 10px;
                font-weight: bold;
                color: #206810;
            }
            input[type="text"],
            input[type="email"],
            input[type="password"],
            input[type="submit"] {
                font-size: 16px;
                padding: 8px;
                border-radius: 5px;
                border: none;
                margin-bottom: 10px;
                width: 100%;
            }
            input[type="submit"] {
                background-color: #206810;
                color: white;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #1b570e;
            }
            .register {
                margin-top: 20px;
                font-size: 14px;
                color: #206810;
            }
            .register a {
                color: #0b5ca5;
                text-decoration: none;
            }
            .register a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="InicioSesion.jsp">Iniciar sesión</a></li>
                    <li><a href="acerca_de.jsp">Acerca de</a></li>
                    <li><a href="contactanos.jsp">Contáctanos</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <br>
        <br>
        <div>
            <h2>Registrarse</h2>
        </div>
        <form action="../procesarRegistro.jsp" method="post" id="registroForm">
            <label for="nombre">Nombre</label>
            <input type="text" name="nombre" id="nombre" required>
            <label for="apellidos">Apellidos</label>
            <input type="text" name="apellidos" id="apellidos" required>
            <label for="DNI">DNI</label>
            <input type="text" name="DNI" id="DNI" required>
            <label for="idCentro">ID del Centro correspondiente</label>
            <input type="text" name="idCentro" id="idCentro" required>
            <label for="email">Correo electrónico:</label>
            <input type="email" name="email" id="email" required>
            <label for="password">Contraseña:</label>
            <input type="password" name="password" id="password"  pattern="^[A-Za-z\d@$!%*?&]{8,}$" title="La contraseña debe tener al menos 8 caracteres, y puede contener letras, números y los siguientes caracteres especiales: @$!%*?&" required>
            <label for="confirm_password">Confirmar contraseña:</label>
            <input type="password" name="confirm_password" id="confirm_password" pattern="^[A-Za-z\d@$!%*?&]{8,}$" title="La contraseña debe tener al menos 8 caracteres, y puede contener letras, números y los siguientes caracteres especiales: @$!%*?&" required oninput="check(this)">
            <input type="submit" value="registrarse">
        </form>
        <script>
            const registroForm = document.getElementById("registroForm");

            registroForm.addEventListener("submit", (event) => {
                const password = document.getElementById("password").value;
                const confirmPassword = document.getElementById("confirm_password").value;

                if (password !== confirmPassword) {
                    event.preventDefault(); // Evita que el formulario se envíe
                    alert("La confirmación de contraseña no coincide.", "Andicare");
                }
            });
        </script>

    </body>
</html>
