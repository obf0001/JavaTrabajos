<%-- 
    Document   : principal
    Created on : 16-mar-2023, 10:13:58
    Author     : Oday
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AndiCare - Iniciar sesión</title>
        <script>
            var params = new URLSearchParams(window.location.search);
            var inicioSesionFallido = params.get("inicioSesionFallido");
            if (inicioSesionFallido) {
                alert("Usuario o contraseña incorrectos, asegurese de haber introducido los datos correctos o de haberse registrado.");
            }
        </script>
        <link rel="stylesheet" href="style.css">
        <style>
            header {
                position: fixed !important;
                top: 0 !important;
                left: 0 !important;
            }
            body {
                font-family: Calibri;
                background-color: white;
                text-align: center;

            }
            header {
                background-color: #206810;
                color: white;

            }
            nav ul li:hover {
                background: #1b570e;
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
            input[type="email"],
            input[type="password"],
            input[type="submit"] {
                font-size: 16px;
                padding: 8px;
                border-radius: 9px;
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
        <br>
        <br>
        <br>
        <div>
            <h2>Iniciar sesión</h2>
        </div>
        <form action="../procesarInicioSesion.jsp" method="post">
            <label for="email">Correo electrónico:</label>
            <input type="email" name="email" id="email" required>
            <label for="password">Contraseña:</label>
            <input type="password" name="password" id="password" required>
            <input type="submit" value="ENTRAR">
            <p class="register">¿No tienes cuenta? <a href="registro.jsp">Regístrate</a></p>
        </form>
    </body>
</html>
