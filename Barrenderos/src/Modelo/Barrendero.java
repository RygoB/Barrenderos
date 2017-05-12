
package Modelo;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Barrendero extends Persona{
    //atributos
    public int Rendimiento;
    public int []Exp_sector=new int[19];
    
    //constructor
    public Barrendero(String Rut,String Nombre,int Edad,String Sexo){
        super(Rut,Nombre,Edad,Sexo);
    }
    
}