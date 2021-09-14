package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public String URL = "jdbc:mysql://localhost:3306/gauss?zeroDateTimeBehavior=CONVERT_TO_NULL&test&useSSL=false";
    public String USUARIO = "root";
    public String CLAVE = "2977";

    
    public Connection getConnection()
    {
        Connection conexion = null;
         try{
             Class.forName("com.mysql.cj.jdbc.Driver");
             conexion=(Connection)DriverManager.getConnection(URL, USUARIO, CLAVE);
             //JOptionPane.showMessageDialog(null, "Conexion exitosa");
             
         }catch(Exception ex){
             System.err.println("Error No se puede abrir la base de datos "+ex.getMessage());
         }
         
      return conexion;
    }
}

