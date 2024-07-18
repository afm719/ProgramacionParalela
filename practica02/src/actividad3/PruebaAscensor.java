package actividad3;

import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    	p1.start();
    	p2.start();
        p3.start();
        p4.start();
        p5.start();
    	p6.start();
        p7.start();
        p8.start();
        
        Timer temporizador = new Timer();
        Timer temp2 = new Timer();
		temporizador.schedule(a, 0, 2000);
		Refresca ref = new Refresca (a);
		temp2.scheduleAtFixedRate(ref, 0, 200);
		
	}
}
