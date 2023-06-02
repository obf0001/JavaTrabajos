package modelo;

import config.conexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;

public class pacientesLista {

    Connection conexion;

    public pacientesLista() throws ClassNotFoundException {
        conexionSQL con = new conexionSQL();
        conexion = con.getConexion();
    }

    public List<pacientes> listaPacientes() {

        // Declaración de variables PreparedStatement y ResultSet
        PreparedStatement ps;
        ResultSet rs;

        // Creación de una lista vacía de objetos pacientes
        List<pacientes> lista = new ArrayList<>();

        try {

            ps = conexion.prepareStatement("SELECT idPaciente, DNI, Nombre, Apellidos, Fecha_Nacimiento, Estado_Usuario_LibreView, NUHSA FROM pacientes;");

            // Ejecución de la consulta SQL y obtención del ResultSet
            rs = ps.executeQuery();

            // Iteración sobre los resultados del ResultSet
            while (rs.next()) {

                // Obtención de los valores de los campos de la fila actual del ResultSet
                int idPac = rs.getInt("idPaciente");
                String DNI_Pac = rs.getString("DNI");
                String NombrePac = rs.getString("Nombre");
                String ApellidosPac = rs.getString("Apellidos");
                Date Fecha_Nac_Pac = rs.getDate("Fecha_Nacimiento");
                String NUHSA_Pac = rs.getString("NUHSA");
                String Estado_Usuario_LibreView_Pac = rs.getString("Estado_Usuario_LibreView");

                // Creación de un objeto pacientes con los valores obtenidos de la fila actual
                pacientes paciente = new pacientes(idPac, DNI_Pac, NombrePac, ApellidosPac, Fecha_Nac_Pac, Estado_Usuario_LibreView_Pac, NUHSA_Pac);

                // Agregación del objeto pacientes a la lista
                lista.add(paciente);
            }

            // Devolución de la lista completa de objetos pacientes
            return lista;

        } catch (SQLException e) {
            // Manejo de excepciones
            System.out.println(e.toString());
            return null;
        }
    }

    public pacientes mostrarPacientes(String _DNI) {

        PreparedStatement ps;
        ResultSet rs;
        pacientes paciente = null;

        try {
            // Creamos un objeto PreparedStatement y especificamos la consulta SQL a ejecutar.
            ps = conexion.prepareStatement("SELECT idPaciente, DNI, Nombre, Apellidos, Fecha_Nacimiento, Estado_Usuario_LibreView, NUHSA"
                    + " FROM pacientes WHERE DNI = ?");
            // Establecemos el valor del parámetro de consulta '?' en la posición '1' con el valor del parámetro _DNI.
            ps.setString(1, _DNI);
            // Ejecutamos la consulta SQL y almacenamos los resultados en un objeto ResultSet.
            rs = ps.executeQuery();

            // Iteramos a través de los resultados y creamos un objeto 'paciente' para cada registro.
            while (rs.next()) {

                int idPac = rs.getInt("idPaciente");
                String DNI_Pac = rs.getString("DNI");
                String NombrePac = rs.getString("Nombre");
                String ApellidosPac = rs.getString("Apellidos");
                Date Fecha_Nac_Pac = rs.getDate("Fecha_Nacimiento");
                String NUHSA_Pac = rs.getString("NUHSA");
                String Estado_Usuario_LibreView_Pac = rs.getString("Estado_Usuario_LibreView");

                paciente = new pacientes(idPac, DNI_Pac, NombrePac, ApellidosPac, Fecha_Nac_Pac, Estado_Usuario_LibreView_Pac, NUHSA_Pac);

            }

            // Devolvemos el objeto 'paciente'.
            return paciente;

        } catch (SQLException e) {
            // En caso de error, imprimimos la excepción y devolvemos 'null'.
            System.out.println(e.toString());
            return null;
        }
    }

    public boolean insertar(pacientes paciente) {

        PreparedStatement ps;

        try {
            // Creamos un objeto PreparedStatement y especificamos la consulta SQL a ejecutar.
            ps = conexion.prepareStatement("INSERT INTO pacientes (DNI, Nombre, Apellidos, Fecha_Nacimiento, Estado_Usuario_LibreView, NUHSA) \n"
                    + " VALUES (?,?,?,?,?,?)");
            // Establecemos los valores de los parámetros de consulta '?' con los valores de las propiedades del objeto 'paciente'.
            ps.setString(1, paciente.getDNI());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getAppelidos());
            ps.setDate(4, paciente.getFecha_nacimiento());
            ps.setString(5, paciente.getEstado_LibreView());
            ps.setString(6, paciente.getNUHSA());
            // Ejecutamos la consulta SQL.
            ps.execute();

            // Devolvemos 'true' si se ejecuta correctamente.
            return true;

        } catch (SQLException e) {
            // En caso de error, imprimimos la excepción y devolvemos 'false'.
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean modificar(pacientes paciente) {

        PreparedStatement ps;

        try {
            // Creamos un objeto PreparedStatement y especificamos la consulta SQL a ejecutar.
            ps = conexion.prepareStatement("UPDATE pacientes SET DNI=?, Nombre=?, Apellidos=?, Fecha_Nacimiento=?, Estado_Usuario_LibreView=?, NUHSA=?) \n"
                    + " WHERE idPaciente=?");

            // Establecemos los valores de los parámetros de consulta '?' con los valores de las propiedades del objeto 'paciente'.
            ps.setString(1, paciente.getDNI()); // Primer parámetro.
            ps.setString(2, paciente.getNombre()); // Segundo parámetro.
            ps.setString(3, paciente.getAppelidos()); // Tercer parámetro.
            ps.setDate(4, paciente.getFecha_nacimiento()); // Cuarto parámetro.
            ps.setString(5, paciente.getEstado_LibreView()); // Quinto parámetro.
            ps.setString(6, paciente.getNUHSA()); // Sexto parámetro.
            ps.setInt(7, paciente.getIdPaciente()); // Séptimo parámetro.

            ps.execute(); // Ejecutamos la consulta.

            return true; // Devolvemos 'true' si todo ha ido bien.

        } catch (SQLException e) { // Si algo falla, capturamos la excepción SQL.
            System.out.println(e.toString()); // Mostramos el error en la consola.
            return false; // Devolvemos 'false' si hay algún error.
        }
    }

    public boolean eliminar(int _idPac) {

        PreparedStatement ps;

        try {
            // Creamos un objeto PreparedStatement y especificamos la consulta SQL a ejecutar.
            ps = conexion.prepareStatement("DELETE FROM pacientes WHERE idPaciente=?");

            // Establecemos el valor del parámetro de consulta '?' con el valor de '_idPac'.
            ps.setInt(1, _idPac); // Primer parámetro.

            ps.execute(); // Ejecutamos la consulta.

            return true; // Devolvemos 'true' si todo ha ido bien.

        } catch (SQLException e) { // Si algo falla, capturamos la excepción SQL.
            System.out.println(e.toString()); // Mostramos el error en la consola.
            return false; // Devolvemos 'false' si hay algún error.
        }
    }
}
