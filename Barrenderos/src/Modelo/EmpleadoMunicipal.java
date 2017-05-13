
package Modelo;

public class EmpleadoMunicipal extends Persona{
    //atributos
    public String Usuario;
    public String Pass;

    public Reclamo reclamo=new Reclamo();
    
    //constructor
    public EmpleadoMunicipal(){}
    
    public EmpleadoMunicipal(String Rut,String Nombre,int Edad,String Sexo, String Usuario, String Pass){
        super(Rut,Nombre,Edad,Sexo);
        this.Usuario=Usuario;
        this.Pass=Pass;
    }
    
    //metodos
    //-------------------------------------metodos reclamo-------------------------------------
    public boolean agregarReclamo(int Id,String texto, String fecha){
        reclamo.Id=Id;
        reclamo.Texto=texto;
        reclamo.Fecha=fecha;
        return(reclamo.agregarReclamo());
    }
    
    
    public String ver_dato_reclamo(int Id,String var){
        return reclamo.extraer_dato(Id, var);
    }
    
    public String[][] ver_dato_reclamo_todos(){
        return reclamo.extraer_dato_todos();
    }   
}
