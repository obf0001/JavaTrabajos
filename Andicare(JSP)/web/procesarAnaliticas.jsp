<%-- 
    Document   : cargarAnaliticas
    Created on : 26-abr-2023, 10:58:30
    Author     : Oday
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="config.conexionSQL"%>
<%@ page import="java.sql.Date"%>
<%

//Inicializamos las variables para que borren su contenido antes de meter nuevos
        String NUHSAPac = "";
        String fechAnaPacStr = "0001-01-01";
        String profSanitarioPac = "";

        // Obtener los datos ingresados por el usuario desde el formulario
        NUHSAPac = request.getParameter("NUHSAPac");
        profSanitarioPac = (String) session.getAttribute("DNIMedico");
        
        //Inicializamos y comprobamos que no es nulo o vacio
        float glucosaPromPac = 0;
        String glucosaPromPacStr = request.getParameter("glucosaPromPac");
        if (glucosaPromPacStr != null && !glucosaPromPacStr.isEmpty()) {
            glucosaPromPac = Float.parseFloat(glucosaPromPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float promLectDiaPac = 0;
        String promLectDiaPacStr = request.getParameter("promLectDiaPac");
        if (promLectDiaPacStr != null && !promLectDiaPacStr.isEmpty()) {
            promLectDiaPac = Float.parseFloat(promLectDiaPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float porcObjetivoPac = 0;
        String porcObjetivoPacStr = request.getParameter("porcObjetivoPac");
        if (porcObjetivoPacStr != null && !porcObjetivoPacStr.isEmpty()) {
            porcObjetivoPac = Float.parseFloat(porcObjetivoPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float sensorTiemPorcActPac = 0;
        String sensorTiemPorcActPacStr = request.getParameter("sensorTiemPorcActPac");
        if (sensorTiemPorcActPacStr != null && !sensorTiemPorcActPacStr.isEmpty()) {
            sensorTiemPorcActPac = Float.parseFloat(sensorTiemPorcActPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float sensorDurGlucBajaPac = 0;
        String sensorDurGlucBajaPacStr = request.getParameter("sensorDurGlucBajaPac");
        if (sensorDurGlucBajaPacStr != null && !sensorDurGlucBajaPacStr.isEmpty()) {
            sensorDurGlucBajaPac = Float.parseFloat(sensorDurGlucBajaPacStr);
        }

        float porcDebajoObjPac = 0;
        String porcDebajoObjPacStr = request.getParameter("porcDebajoObjPac");
        if (porcDebajoObjPacStr != null && !porcDebajoObjPacStr.isEmpty()) {
            porcDebajoObjPac = Float.parseFloat(porcDebajoObjPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float porcDebUmbrEvenHipoPac = 0;
        String porcDebUmbrEvenHipoPacStr = request.getParameter("porcDebUmbrEvenHipoPac");
        if (porcDebUmbrEvenHipoPacStr != null && !porcDebUmbrEvenHipoPacStr.isEmpty()) {
            porcDebUmbrEvenHipoPac = Float.parseFloat(porcDebUmbrEvenHipoPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float porcEncUmbrEvenHiperPac = 0;
        String porcEncUmbrEvenHiperPacStr = request.getParameter("porcEncUmbrEvenHiperPac");
        if (porcEncUmbrEvenHiperPacStr != null && !porcEncUmbrEvenHiperPacStr.isEmpty()) {
            porcEncUmbrEvenHiperPac = Float.parseFloat(porcEncUmbrEvenHiperPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float coeficienteVariacionPac = 0;
        String coeficienteVariacionPacStr = request.getParameter("coeficienteVariacionPac");
        if (coeficienteVariacionPacStr != null && !coeficienteVariacionPacStr.isEmpty()) {
            coeficienteVariacionPac = Float.parseFloat(coeficienteVariacionPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float evenGlucBajaPac = 0;
        String evenGlucBajaPacStr = request.getParameter("evenGlucBajaPac");
        if (evenGlucBajaPacStr != null && !evenGlucBajaPacStr.isEmpty()) {
            evenGlucBajaPac = Float.parseFloat(evenGlucBajaPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float promevenGlucBajaDiaPac = 0;
        String promevenGlucBajaDiaPacStr = request.getParameter("promevenGlucBajaDiaPac");
        if (promevenGlucBajaDiaPacStr != null && !promevenGlucBajaDiaPacStr.isEmpty()) {
            promevenGlucBajaDiaPac = Float.parseFloat(promevenGlucBajaDiaPacStr);
        }
        //Inicializamos y comprobamos que no es nulo o vacio
        float IndiPac = 0;
        String IndiPacStr = request.getParameter("IndiPac");
        if (IndiPacStr != null && !IndiPacStr.isEmpty()) {
            IndiPac = Float.parseFloat(IndiPacStr);

        }

        //pasamos String a Date y aseguramos que si es nulo se quede con el valor de 0001-01-01
        if (request.getParameter("fechAnaPac") != null && !request.getParameter("fechAnaPac").isEmpty()) {
            fechAnaPacStr = request.getParameter("fechAnaPac");
        }
        Date fechAnaPac = java.sql.Date.valueOf(fechAnaPacStr);

        // Crear un objeto de conexión utilizando la clase conexionSQL
        conexionSQL conexionBD = new conexionSQL();
        Connection conexion = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;

        try {
            // Establecer la conexión con la base de datos
            conexion = conexionBD.getConexion();

            String sql2 = "INSERT INTO analiticas (Glucosa_Promedio, Prom_Vistas_Dias, Porc_Objetivo, Sensor_tiempo_Porc_Act, Sensor_Duracion_Promedio_Glucosa_Baja, "
                    + "Porc_Debajo_Objetivo, Porc_Debajo_Eventos_Hipoglucemia, Porc_Encima_Eventos_Hiperglucemia, Coeficiente_Variacion, "
                    + "Eventos_Glucosa_Baja, Prom_Glucosa_Baja_Dia, Ind_Gestion_Glucosa_GMI_Porc, NUHSA, Fecha_Analitica, Tenico_implicado)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            statement = conexion.prepareStatement(sql2);

            statement.setFloat(1, glucosaPromPac);
            statement.setFloat(2, promLectDiaPac);
            statement.setFloat(3, porcObjetivoPac);
            statement.setFloat(4, sensorTiemPorcActPac);
            statement.setFloat(5, sensorDurGlucBajaPac);
            statement.setFloat(6, porcDebajoObjPac);
            statement.setFloat(7, porcDebUmbrEvenHipoPac);
            statement.setFloat(8, porcEncUmbrEvenHiperPac);
            statement.setFloat(9, coeficienteVariacionPac);
            statement.setFloat(10, evenGlucBajaPac);
            statement.setFloat(11, promevenGlucBajaDiaPac);
            statement.setFloat(12, IndiPac);
            statement.setString(13, NUHSAPac);
            statement.setDate(14, fechAnaPac);
            statement.setString(15, profSanitarioPac);

            // Ejecutar la sentencia SQL
            statement.executeUpdate();

            // Redirigir al usuario a una página de confirmación o mostrar un mensaje de éxito en la misma página
            response.sendRedirect("./Paginas/cargarAnaliticas.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROOOOOOR");
            request.setAttribute("error", e.getMessage());

        } finally {
            // Cerrar la conexión y la sentencia SQL
            try {
                System.out.println("CERRANDO CONEXION");
                statement.close();
                conexionBD.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
                // El inicio de sesión falló, mostrar un mensaje de error y volver a la página de inicio de sesión
                out.println("Correo electrónico o contraseña incorrectos.");
            }
        }
%>
