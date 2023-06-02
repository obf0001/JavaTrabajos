
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conexionSQL {
    Connection conexion;
    public Connection getConexion() throws ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        try{//(direccion de la base de datos, usuario, contrasenia)
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","obyf2003");
            //Antes estaba asi Connection conexion = DriverManager.getConnection(etc) y no estaba inicializado arriba
            System.out.println("Conexión establecida correctamente");
            return conexion;
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }
    
    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
