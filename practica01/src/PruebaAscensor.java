
/**
 * Prueba de la simulacion del ascensor sin control de las reglas de movimiento de las personas
 * 
 * @author (J. Javier Gutierrez) 
 * @version (8-9-2022)
 */

import fundamentos.*;

public class PruebaAscensor
{

    public static void main(String[] args) {

    	Ascensor a=new Ascensor();
        Persona p1=new Persona(0,4,4000,ColorFig.rosa,a);
        Persona p2=new Persona(3,1,8000,ColorFig.magenta,a);
      
     
        while (true) {
        	a.mueve();
        	p1.camina();
        	p2.camina();
        }
    }

}
