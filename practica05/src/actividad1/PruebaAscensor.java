package actividad1;

/**
 * Prueba de la simulacion del ascensor sin control de las reglas de movimiento de las personas
 * 
 * @author (J. Javier Gutierrez) 
 * @version (8-9-2022)
 */


public class PruebaAscensor extends Thread
{

	public static void main(String[] args) {

    	Ascensor a=new Ascensor();
        Persona p1=new Persona(0,4,4000,ColorFig.azul,a);
        Persona p2=new Persona(3,1,8000,ColorFig.verde,a);
        Persona p3=new Persona(2,1,5200,ColorFig.rojo,a);
        Persona p4=new Persona(4,3,1600,ColorFig.naranja,a);
        Persona p5=new Persona(1,4,850,ColorFig.gris,a);
        Persona p6=new Persona(0,1,550,ColorFig.negro,a);
        Persona p7=new Persona(1,3,350,ColorFig.magenta,a);
        Persona p8=new Persona(2,3,200,ColorFig.rosa,a);
        
        currentThread().setPriority(MAX_PRIORITY);
        a.start();
    	p1.start();
    	p2.start();
    	
        p3.start();
        p4.start();
        p5.start();
    	p6.start();
        p7.start();
        p8.start();
        
        
    	try {
    		while (true) {
    			a.refresca();
				sleep(200);
    		}
    	} catch (InterruptedException e) {
					return;
		}
    	
	}
}
