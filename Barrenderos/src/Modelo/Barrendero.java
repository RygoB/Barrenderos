
package Modelo;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Barrendero extends Persona{
    //atributos
    public int Rendimiento;
    public boolean PartTime;
    public int []Exp_sector=new int[19];
    
    //constructor
    public Barrendero(){}
    
    public Barrendero(String Rut,String Nombre,int Edad,String Sexo, int Rendimeinto, boolean PartTime){
        super(Rut,Nombre,Edad,Sexo);
        this.Rendimiento=Rendimiento;
        this.PartTime=PartTime;
    }
    
    //metodos
    //extraccion de datos de la base de datos
    public ResultSet mostrarBarrendero(String rut,String var){
        PreparedStatement pst;//comunicador con base de dato que usa la variable "cn".
        ResultSet rs=null;
        try
        {
            cn=C.conexion();
            
            //retorna el dato dependiendo de la variable "var", del rut ingresado.
            if(var=="rendimiento")
            {
                pst=cn.prepareStatement("SELECT Rendimiento FROM Barrendero WHERE Rut=?");
                pst.setString(1,rut);
                rs= pst.executeQuery();
            }
            else if(var=="parttime")
            {
                pst=cn.prepareStatement("SELECT PartTime FROM Barrendero WHERE Rut=?");
                pst.setString(1,rut);
                rs= pst.executeQuery();
            }
            else{
                rs=this.mostrarPersona(rut, var);
            }
        }
        catch(Exception e){}
        return rs;
    }
    
    public String extraer_dato(String rut,String var){
        String dato="";
        ResultSet rs = mostrarBarrendero(rut,var);
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
    
    public String[][] extraer_dato_todos(boolean parttime){
        int cantidad_barrenderos;
        try {
            cn=C.conexion();
            //conecta el resultset con la base de datos.
            Statement st=cn.createStatement();
            //se calcula la cantidad de barrenderos que hay
            String sql="SELECT COUNT(*) FROM Barrendero WHERE PartTime="+parttime+"";
            ResultSet rs= st.executeQuery(sql);
            rs.next();
            
            cantidad_barrenderos=Integer.valueOf(rs.getString(1));
            
            rs.close();
            
            //se crea el arreglo que contiene los datos
            String datos[][]=new String[cantidad_barrenderos][5];
            
            //se guardan los datos del resultset en arreglo.
            sql="SELECT * FROM Persona,Barrendero"+
                    " WHERE Persona.Rut=Barrendero.Rut"+
                    " AND Barrendero.PartTime="+parttime+"";
            
            rs= st.executeQuery(sql);
            int i=0;
            while(rs.next()){
                datos[i][0]=String.valueOf(rs.getString(1));
                datos[i][1]=String.valueOf(rs.getString(2));
                datos[i][2]=String.valueOf(rs.getInt(3));
                datos[i][3]=String.valueOf(rs.getString(4));
                datos[i][4]=String.valueOf(rs.getInt(6));
                i++;
            }
            return datos;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    //metodos que insertan datos en la base de datos
    public boolean agregarBarrendero(){
        this.agregarPersona();
        try {
            cn=C.conexion();
            PreparedStatement pst= cn.prepareStatement("INSERT INTO Barrendero(Rut,Rendimiento,PartTime) VALUES(?,?,?)");
            pst.setString(1,this.Rut);
            pst.setInt(2,this.Rendimiento);
            pst.setBoolean(3,this.PartTime);
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
    
    public boolean modificarBarrendero(String RutInicial){
        ResultSet rs = mostrarBarrendero(RutInicial,"rut");
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
                        //borra el barrendero incial.
                        PreparedStatement pstE= cn.prepareStatement("DELETE FROM Barrendero WHERE Rut='"+RutInicial+"'");
                        pstE.executeUpdate();
                        cn.close();
                        //borra a la persona incial.
                        this.eliminarPersona(RutInicial);

                        // crea un trabajador nuevo con los parametros actualizados.
                        this.agregarBarrendero();
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
    
    public boolean eliminarBarrendero(String Rut){
        ResultSet rs = mostrarBarrendero(Rut,"rut");
        try{
            rs.next();
            if(rs.getRow()!=0){
                rs.close();
                cn=C.conexion();
                //boora al barrendero.
                Statement s=cn.createStatement();
                String query="DELETE FROM Barrendero WHERE Rut='"+Rut+"'";
                s.executeUpdate(query);
                s.close();
                cn.close();
                //borra a la persona.
                this.eliminarPersona(Rut);
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