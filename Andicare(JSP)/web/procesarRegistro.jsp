<%-- 
    Document   : procesarRegistro
    Created on : 03-may-2023, 11:47:50
    Author     : Oday
--%>

<%@page import="config.conexionSQL"%>
<%@page import="javax.sql.DataSource"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*, javax.naming.InitialContext"%>
<%
// Obtener los valores ingresados por el usuario
String nombre = request.getParameter("nombre");
String apellidos = request.getParameter("apellidos");
String correo = request.getParameter("email");
String contrasena = request.getParameter("password");
String dni = request.getParameter("DNI");
String idCentro = request.getParameter("idCentro");

// Validar que la contraseña y la confirmación de contraseña coincidan
String confirmContrasena = request.getParameter("confirm_password");
if (!contrasena.equals(confirmContrasena)) {
    out.println("Las contraseñas no coinciden.");
    return;
}

// Crear un objeto de conexión utilizando la clase conexionSQL
conexionSQL conexionBD = new conexionSQL();
Connection conexion = null;
PreparedStatement statement = null;
conexion = conexionBD.getConexion();

// Insertar los datos del usuario en la base de datos
PreparedStatement consulta = conexion.prepareStatement("INSERT INTO usuarios (Nombre, Apellidos, email, password) VALUES (?, ?, ?, ?);");
consulta.setString(1, nombre);
consulta.setString(2, apellidos);
consulta.setString(3, correo);
consulta.setString(4, contrasena);
int filasAfectadas = consulta.executeUpdate();

 consulta = conexion.prepareStatement("INSERT INTO empleados (Nombre, Apellidos, DNI, idCentro, email) VALUES (?, ?, ?, ?, ?);");
                    consulta.setString(1, nombre);
                    consulta.setString(2, apellidos);
                    consulta.setString(3, dni);
                    consulta.setString(4, idCentro);
                    consulta.setString(5, correo);
                    int result2 = consulta.executeUpdate();

if (result2 > 0) {
    // El registro fue exitoso, redirigir a la página de inicio de sesión
    response.sendRedirect("Paginas/InicioSesion.jsp");
} else {
    // El registro falló, mostrar un mensaje de error y volver a la página de registro
    out.println("El registro falló, por favor intente nuevamente.");
}

// Cerrar la conexión a la base de datos
consulta.close();
conexion.close();

%>