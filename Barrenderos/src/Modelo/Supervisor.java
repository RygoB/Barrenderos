
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Supervisor extends Persona {
    //atributos
    public String Usuario;
    public String Pass;
    
    public Barrendero barrendero=new Barrendero();
    public Sector sector=new Sector();
    public Reclamo reclamo=new Reclamo();
    
    //constructor
    public Supervisor(){}
    
    public Supervisor(String Rut,String Nombre,int Edad,String Sexo, String Usuario, String Pass){
        super(Rut,Nombre,Edad,Sexo);
        this.Usuario=Usuario;
        this.Pass=Pass;
    }
    
    //metodos
    //-------------------------------------metodos barrendero-------------------------------------
    public boolean agregarBarrendero(String rut,String nombre,int edad,String genero,int rendimiento,boolean parttime){
        barrendero.Rut=rut;
        barrendero.Nombre=nombre;
        barrendero.Edad=edad;
        barrendero.Genero=genero;
        barrendero.Rendimiento=rendimiento;
        barrendero.PartTime=parttime;
        return barrendero.agregarBarrendero();
    }
    
    public boolean modificarBarrendero(String rutInicial, String nuevoRut,String nombre,int edad,String genero,int rendimiento,boolean parttime){
        barrendero.Rut=nuevoRut;
        barrendero.Nombre=nombre;
        barrendero.Edad=edad;
        barrendero.Genero=genero;
        barrendero.Rendimiento=rendimiento;
        barrendero.PartTime=parttime;
        return barrendero.modificarBarrendero(rutInicial);
    }
    
    public boolean eliminarBarrendero(String rut){
        return barrendero.eliminarBarrendero(rut);
    }
    
    public String ver_dato_barrendero(String rut,String var){
        return barrendero.extraer_dato(rut, var);
    }
    
    public String[][] ver_dato_barrendero_todos(boolean parttime){
        return barrendero.extraer_dato_todos(parttime);
    }
    
    //-------------------------------------metodos sector-------------------------------------
    public boolean modificarSector(int Id, boolean prioridad, boolean conflicto){
        sector.Id=Id;
        sector.Prioridad=prioridad;
        sector.Conflicto=conflicto;
        return sector.modificarSector(Id);
    }
    
    public String ver_dato_sector(int Id, String var){
        return sector.extraer_dato(Id, var);
    }
    
    public String[][] ver_dato_sector_todos(){
        return sector.extraer_dato_todos();
    }
    
    //-------------------------------------metodos reclamo-------------------------------------
    public String ver_dato_reclamo(int Id,String var){
        return reclamo.extraer_dato(Id, var);
    }
    
    public String[][] ver_dato_reclamo_todos(){
        return reclamo.extraer_dato_todos();
    }
    
}
