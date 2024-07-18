package actividad3;

import java.util.TimerTask;

public class Refresca extends TimerTask {
	
// Ventana en la que se dibuja la simulación
   private Ascensor a;    
   public Refresca(Ascensor a) {
	   this.a = a;
   }
	
	
	public void run() {
		a.refresca();
	}

}
