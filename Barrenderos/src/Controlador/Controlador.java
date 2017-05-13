
package Controlador;

import Modelo.*;

public class Controlador {
    public Supervisor s=new Supervisor();
    public EmpleadoMunicipal e=new EmpleadoMunicipal();
    
    public void borrar(){
        String [][]a=s.ver_dato_barrendero_todos(false);
//        for(int i=0;i<a.length;i++){
//            for(int j=0;j<a[i].length;j++){
//                System.out.print(a[i][j]+" ");
//            }
//            System.out.println("");
//        }
        
        String [][]b=s.ver_dato_reclamo_todos();
//        for(int i=0;i<b.length;i++){
//            for(int j=0;j<b[i].length;j++){
//                System.out.print(b[i][j]+" ");
//            }
//            System.out.println("");
//        }
        
        String [][]c=e.ver_dato_reclamo_todos();
        for(int i=0;i<c.length;i++){
            for(int j=0;j<c[i].length;j++){
                System.out.print(c[i][j]+" ");
            }
            System.out.println("");
        }
        e.ver_dato_reclamo_todos();
    }
}
