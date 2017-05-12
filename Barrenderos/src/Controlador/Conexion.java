
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
    

public class Conexion {
    private static String User = "root"; //Nombre por defecto de la base de datos
    private static String Pass ="clave"; //Contraseña cuando instalo mysql
    private static String Server="jdbc:mysql://localhost:3306/Barrenderos"; // Debe reemplazar por el puerto colocado en la instalación y por la base de datos creada.
    private static String Driver="com.mysql.jdbc.Driver";
    private static Connection conectar=null;
    public Connection conexion()
    {
        try
        {
        Class.forName(Driver);
        conectar=(Connection)DriverManager.getConnection(Server,User,Pass);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return conectar;
    }
    
    public static String getUser() {
        return User;
    }

    public static void setUser(String User) {
        Conexion.User = User;
    }

    public static String getPass() {
        return Pass;
    }

    public static void setPass(String Pass) {
        Conexion.Pass = Pass;
    }

    public static String getServer() {
        return Server;
    }

    public static void setServer(String Server) {
        Conexion.Server = Server;
    }

    public static String getDriver() {
        return Driver;
    }

    public static void setDriver(String Driver) {
        Conexion.Driver = Driver;
    }

    public static Connection getConexion() {
        return conectar;
    }

    public static void setConexion(Connection conexion) {
        Conexion.conectar = conexion;
    }
}

