
package modelo;

import config.conexionSQL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class prueba {
    static Connection conexion;
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        conexionSQL con = new conexionSQL();
        conexion = con.getConexion();
        Statement sentencia = conexion.createStatement();
        String sql = "CREATE TABLE prueba " +
                         "(id INT NOT NULL AUTO_INCREMENT, " +
                         " nombre VARCHAR(255), " +
                         " apellido VARCHAR(255), " +
                         " edad INT, " +
                         " PRIMARY KEY ( id ))";

        sentencia.executeUpdate(sql);
    }
    
}
