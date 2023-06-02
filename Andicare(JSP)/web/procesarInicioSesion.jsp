<%-- 
    Document   : procesarInicioSesion
    Created on : 11-abr-2023, 10:04:44
    Author     : Oday
--%>

<%@page import="config.conexionSQL"%>
<%@page import="javax.sql.DataSource"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*, javax.naming.InitialContext"%>
<html>
    <head>
        <script>
            function validarFormulario() {
                var correo = document.getElementById("email").value;
                var contrasena = document.getElementById("password").value;
                if (correo == "" || contrasena == "") {
                    alert("Por favor, ingrese su correo electrónico y contraseña.");
                    return false;
                }
            }
        </script>

    </head>
    <body>
        <%
            // Obtener los valores ingresados por el usuario
            String correo = request.getParameter("email");
            String contrasena = request.getParameter("password");

            // Crear un objeto de conexión utilizando la clase conexionSQL
            conexionSQL conexionBD = new conexionSQL();
            Connection conexion = null;
            PreparedStatement statement = null;
            conexion = conexionBD.getConexion();

            // Validar los datos ingresados por el usuario contra la base de datos
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM usuarios WHERE email = ? AND password = ?;");
            consulta.setString(1, correo);
            consulta.setString(2, contrasena);
            ResultSet resultado = consulta.executeQuery();

            PreparedStatement consultaTipo = conexion.prepareStatement("SELECT Tipo FROM empleados WHERE email = ?;");
            consultaTipo.setString(1, correo);
            ResultSet resultadoTipo = consultaTipo.executeQuery();

            if (resultado.next()) {
                String tipo = "";
                if (resultadoTipo.next()) {
                    tipo = resultadoTipo.getString("tipo");
                    if (tipo == null) {
                        tipo = "NoType";
                    }
                }
                //Lo guardamos para usarlo mas tarde, session no borra las variables hasta cerrar la pagina
                session.setAttribute("tipo", tipo);

                PreparedStatement consultaDNI = conexion.prepareStatement("SELECT DNI FROM empleados WHERE email = ?;");
                consultaDNI.setString(1, correo);
                ResultSet resultadoDNI = consultaDNI.executeQuery();

                if (resultadoDNI.next()) {
                    String DNIMedico = resultadoDNI.getString("DNI");
                    // Lo guardamos para usarlo más tarde, session no borra las variables hasta cerrar la página
                    session.setAttribute("DNIMedico", DNIMedico);
                } else {
                    // Si no se encontraron resultados, haz algo en consecuencia
                }
                response.sendRedirect("Paginas/paginaIntermedia.jsp");

            } else {%>
        <form action="../procesarInicioSesion.jsp" method="post" onsubmit="return validarFormulario()">
            <%response.sendRedirect("./Paginas/InicioSesion.jsp?inicioSesionFallido=true");%>
        </form>
    </body>
</html>
<%
    }

    // Cerrar la conexión a la base de datos
    resultado.close();
    consulta.close();
    conexion.close();
%>
