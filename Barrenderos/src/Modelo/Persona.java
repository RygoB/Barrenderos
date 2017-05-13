
package Modelo;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Persona {
    //atributos
    public String Rut;
    public String Nombre;
    public int Edad;
    public String Genero;
    
    public Conexion C = new Conexion();
    Connection cn;
    
    //constructor
    public Persona(){}
    
    public Persona(String Rut,String Nombre,int Edad,String Genero){
        this.Rut=Rut;
        this.Nombre=Nombre;
        this.Edad=Edad;
        this.Genero=Genero;
    }
    
    //metodos
    //metodos que extraen datos desde la base de datos
    public ResultSet mostrarPersona(String rut,String var){
        PreparedStatement pst;//comunicador con base de dato que usa la variable "cn".
        ResultSet rs=null;
        try
        {
            cn=C.conexion();
            
            //retorna el dato dependiendo de la variable "var", del rut ingresado.
            if(var=="rut")
            {
                pst=cn.prepareStatement("SELECT Rut FROM Persona WHERE Rut=?");
                pst.setString(1,rut);
                rs= pst.executeQuery();
            }
            if(var=="nombre")
            {
                pst=cn.prepareStatement("SELECT Nombre FROM Persona WHERE Rut=?");
                pst.setString(1,rut);
                rs= pst.executeQuery();
            }
            if(var=="edad")
            {
                pst=cn.prepareStatement("SELECT Edad FROM Persona WHERE Rut=?");
                pst.setString(1,rut);
                rs= pst.executeQuery();
            }
            if(var=="genero")
            {
                pst=cn.prepareStatement("SELECT Genero FROM Persona WHERE Rut=?");
                pst.setString(1,rut);
                rs= pst.executeQuery();
            }
        }
        catch(Exception e){}
        return rs;
    }
    
    public String extraer_dato(String rut,String var){
        String dato="";
        ResultSet rs = mostrarPersona(rut,var);
        try{
            rs.next();
            if(rs.getRow()!=0){
                dato=String.valueOf(rs.getString(1));
                rs.close();
            }
            else{
                dato="-";
            }
            cn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return dato;
    }
    
    //metodos que insertan datos en la base de datos
    public boolean agregarPersona(){
        try {
            cn=C.conexion();
            PreparedStatement pst= cn.prepareStatement("INSERT INTO Persona(Rut,Nombre,Edad,Genero) VALUES(?,?,?,?)");
            pst.setString(1,this.Rut);
            pst.setString(2,this.Nombre);
            pst.setInt(3,this.Edad);
            pst.setString(4,this.Genero);
            pst.executeUpdate();
            pst.close();
            cn.close();
            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public boolean modificarPersona(String RutInicial){
        ResultSet rs = mostrarPersona(RutInicial,"rut");
        try{
            rs.next();
            if(rs.getRow()!=0){
                boolean valido=true;
                //se recorre la lista "Atributos_validos" y en el caso de no haber ningun "false", valido sera "true".
        //        boolean valido=true;
        //        for( boolean v: Atributos_validos){
        //            if(v==false){
        //                valido=false;
        //            }
        //        }

                //si la validacion es correcta...
                if(valido==true){
                    //si la coneccion es correcta...
                    try {
                        cn=C.conexion();
                        //borra la persona anterior.
                        PreparedStatement pstE= cn.prepareStatement("DELETE FROM Persona WHERE Rut='"+RutInicial+"'");
                        pstE.executeUpdate();
                        cn.close();
                        // crea un trabajador nuevo con los parametros actualizados.
                        this.agregarPersona();
                        return true;
                    }
                    //si la coneccion no es correcta...
                    catch (SQLException ex) {
                        return false;
                    }
                }
                //si la validacion no es correcta...
                else{
                    //JOptionPane.showMessageDialog(null,"Error al modificar");
                    return false;
                } 
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    
    public boolean eliminarPersona(String Rut){
        ResultSet rs = mostrarPersona(Rut,"rut");
        try{
            rs.next();
            if(rs.getRow()!=0){
                rs.close();
                cn=C.conexion();
                Statement s=cn.createStatement();
                String query="DELETE FROM Persona WHERE Rut='"+Rut+"'";
                s.executeUpdate(query);
                s.close();
                cn.close();
               return true;
            }else{
               return false;
            }
       }catch(Exception e){
            System.out.println(e);
            return false;
       }
    }
    
}
