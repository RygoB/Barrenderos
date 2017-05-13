
package Modelo;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sector {
    //atributos
    public int Id;
    public boolean Prioridad;
    public boolean Conflicto;
    
    public Conexion C = new Conexion();
    Connection cn;
    
    //constructor
    public Sector(){}
    
    public Sector(int Id,boolean Prioridad,boolean Conflicto){
        this.Id=Id;
        this.Prioridad=Prioridad;
        this.Conflicto=Conflicto;
    }
    
    //metodos
    //metodos que extraen datos desde la base de datos
    public ResultSet mostrarSector(int id,String var){
        PreparedStatement pst;//comunicador con base de dato que usa la variable "cn".
        ResultSet rs=null;
        try
        {
            cn=C.conexion();
            
            //retorna el dato dependiendo de la variable "var", del id ingresado.
            if(var=="id")
            {
                pst=cn.prepareStatement("SELECT Id FROM Sector WHERE Id=?");
                pst.setInt(1,id);
                rs= pst.executeQuery();
            }
            if(var=="prioridad")
            {
                pst=cn.prepareStatement("SELECT Prioridad FROM Sector WHERE Id=?");
                pst.setInt(1,id);
                rs= pst.executeQuery();
            }
            if(var=="conflicto")
            {
                pst=cn.prepareStatement("SELECT Conflicto FROM Sector WHERE Id=?");
                pst.setInt(1,id);
                rs= pst.executeQuery();
            }
        }
        catch(Exception e){}
        return rs;
    }
    
    public String extraer_dato(int id,String var){
        String dato="";
        ResultSet rs = mostrarSector(id,var);
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
    
    public String[][] extraer_dato_todos(){
        int cantidad_sector;
        try {
            cn=C.conexion();
            //conecta el resultset con la base de datos.
            Statement st=cn.createStatement();
            //se calcula la cantidad de barrenderos que hay
            String sql="SELECT COUNT(*) FROM Sector";
            ResultSet rs= st.executeQuery(sql);
            rs.next();
            
            cantidad_sector=Integer.valueOf(rs.getString(1));
            
            rs.close();
            
            //se crea el arreglo que contiene los datos
            String datos[][]=new String[cantidad_sector][3];
            
            //se guardan los datos del resultset en arreglo.
            sql="SELECT * FROM Sector";
            
            rs= st.executeQuery(sql);
            int i=0;
            while(rs.next()){
                datos[i][0]=String.valueOf(rs.getInt(1));
                datos[i][1]=String.valueOf(rs.getBoolean(2));
                datos[i][2]=String.valueOf(rs.getBoolean(3));
                i++;
            }
            return datos;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    //metodos que insertan datos en la base de datos
    public boolean agregarSector(){
        try {
            cn=C.conexion();
            PreparedStatement pst= cn.prepareStatement("INSERT INTO Sector(Id,Prioridad,Conflicto) VALUES(?,?,?)");
            pst.setInt(1,this.Id);
            pst.setBoolean(2,this.Prioridad);
            pst.setBoolean(3,this.Conflicto);
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
    
    public boolean modificarSector(int IdInicial){
        ResultSet rs = mostrarSector(IdInicial,"id");
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
                        //borra el sector anterior.
                        PreparedStatement pstE= cn.prepareStatement("DELETE FROM Sector WHERE Id='"+IdInicial+"'");
                        pstE.executeUpdate();
                        cn.close();
                        // crea un sector nuevo con los parametros actualizados.
                        this.agregarSector();
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
    
    
    public boolean eliminarSector(int Id){
        ResultSet rs = mostrarSector(Id,"id");
        try{
            rs.next();
            if(rs.getRow()!=0){
                rs.close();
                cn=C.conexion();
                Statement s=cn.createStatement();
                String query="DELETE FROM Sector WHERE Id='"+Id+"'";
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
