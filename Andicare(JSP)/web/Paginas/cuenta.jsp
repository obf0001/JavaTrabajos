<%-- 
    Document   : cuenta
    Created on : 29-may-2023, 13:16:37
    Author     : Oday
--%>

<%@page import="config.conexionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<%
  conexionSQL conexionBD = new conexionSQL();
  Connection connection = null;
  PreparedStatement statement = null;
  ResultSet resultSet = null;
  try {
    connection = conexionBD.getConexion();

    // Consulta para obtener los datos del usuario
    String query = "SELECT nombre, apellido, correo FROM empleados WHERE dni = ?";
    int userId = 1; // ID del usuario que deseas mostrar

    statement = connection.prepareStatement(query);
    statement.setInt(1, userId);
    resultSet = statement.executeQuery();

    if (resultSet.next()) {
      String nombre = resultSet.getString("nombre");
      String apellido = resultSet.getString("apellido");
      String correo = resultSet.getString("correo");

      // Guardar los valores en variables para mostrarlos en la interfaz
      request.setAttribute("nombre", nombre);
      request.setAttribute("apellido", apellido);
      request.setAttribute("correo", correo);
    }
  } catch (Exception e) {
    e.printStackTrace();
  } finally {
    // Cerrar la conexión y liberar los recursos
    try {
      if (resultSet != null) resultSet.close();
      if (statement != null) statement.close();
      if (connection != null) connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
%>

<!DOCTYPE html>
<html>
<head>
  <title>Interfaz Bonita</title>
  <style>
    body {
      background-color: green;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      font-family: Arial, sans-serif;
    }
    
    .profile-container {
      background-color: white;
      padding: 20px;
      border-radius: 10px;
      text-align: center;
    }
    
    .profile-pic {
      width: 150px;
      height: 150px;
      border-radius: 50%;
      object-fit: cover;
      margin-bottom: 10px;
    }
    
    .name {
      font-size: 24px;
      color: green;
      margin-bottom: 5px;
    }
    
    .email {
      font-size: 18px;
      color: green;
    }
  </style>
</head>
<body>
  <div class="profile-container">
    <img src="ruta-a-la-foto-de-perfil.jpg" alt="Foto de Perfil" class="profile-pic">
    <h1 class="name"><%= request.getAttribute("nombre") %> <%= request.getAttribute("apellido") %></h1>
    <p class="email"><%= request.getAttribute("correo") %></p>
  </div>
</body>
</html>