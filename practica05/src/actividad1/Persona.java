package actividad1;

/**
 * Clase que almacena la informacion del movimiento de una persona en un edificio
 * El movimiento de una se persona se caracterizará por el paso del pasillo de una planta origen
 * a la zona de espera de esa planta, para tomar el ascensor.
 * Una vez en la planta de destino, sale del ascensor a la zona de espera, y de alli al pasillo.
 * En ese momento se permutan las plantas origen y destino y se vuelve a iniciar el movimiento.
 * En todos los cambios de posicion se deja pasar el retraso indicado.
 * 
 * @author (J. Javier Gutierrez) 
 * @version (27-9-2020)
 */



public class Persona extends Thread 
{
    // Atributos de la persona en movimiento por el edificio
    private int plantaOrigen;   // Planta de partida
    private int plantaDestino;  // Planta destino
    private int retraso;        // Tiempo que transcurre en el cambio de posicion
    private ColorFig color;     // Color que representa a la persona en el dibujo
    private Ascensor ascensor;  // Ascensor que se utiliza

    /**
     * Constructor al que se le suministran los valores de los atributos
     */
    public Persona(int plantaOrigen, int plantaDestino,int retraso,ColorFig color, Ascensor ascensor)
    {
        this.plantaOrigen=plantaOrigen;
        this.plantaDestino=plantaDestino;
        this.retraso=retraso;
        this.color=color;
        this.ascensor=ascensor;
        //inicialmente al crear a la persona, esta ya estara en el pasillo
        ascensor.insertaEnPasillo(this,plantaOrigen);
    }

    /**
     * Observador que devuelve la planta de origen
     * 
     * @return entero con el valor de la planta de origen
     */
    public int plantaOrigen ()
    {
        return plantaOrigen;
    }
    

    /**
     * Observador que devuelve la planta de destino
     * 
     * @return entero con el valor de la planta de destino
     */
    public int plantaDestino ()
    {
        return plantaDestino;
    }

    /**
     * Observador que devuelve el retraso en el cambio de posicion
     * 
     * @return entero con el valor del retraso
     */
    public int retraso ()
    {
        return retraso;
    }

    /**
     * Observador que devuelve el color que representa a la persona en el dibujo
     * 
     * @return ColorFig con el color de la personan
     */
    public ColorFig color ()
    {
        return color;
    }

    /**
     * Observador que devuelve el color que representa a la persona en el dibujo
     * 
     * @return ColorFig con el color de la personan
     */
    public Ascensor ascensor ()
    {
        return ascensor;
    }

    /**
     * Realiza el movimiento de la persona con la temporizacion adecuada entre cambios de posicion
     * y controlando la subida y bajada de la persona al ascensor en las plantas adecuadas y cuando 
     * el ascensor esta parado 
     */
	private void camina () {
    	try {
	    	int auxOrigen;
	    	//espera inicial, la persona se encuentra en pasillo
	    	sleep(retraso);
	    	//se borra del pasillo a la persona, ya que pasa a espera
	    	ascensor.borraDePasillo(this, plantaOrigen);
			//comienza la espera al ascensor
			ascensor.insertaEnEspera(this, plantaOrigen);
			
			sleep(retraso);
	    	//Simulacion del retraso en la zona de espera
			ascensor.determinaPlanta(plantaOrigen);
			ascensor.borraDeEspera(this, plantaOrigen);
			
			ascensor.botonPulsado();
			
			ascensor.insertaEnAscensor(this);
	    	
			ascensor.determinaPlanta(plantaDestino);		
			ascensor.borraDeAscensor(this);
			ascensor.insertaEnEspera(this, plantaDestino);
			
	    	//Simulacion camino de salida del ascensor
			sleep(retraso);
			ascensor.borraDeEspera(this, plantaDestino);
			ascensor.insertaEnPasillo(this, plantaDestino);
		
			auxOrigen = plantaOrigen;
			plantaOrigen = plantaDestino;
			plantaDestino = auxOrigen;
			
        } catch (InterruptedException e) {
        	return;
        }
    }
	
	@Override
	//Implementacion de la clase run 
	public void run() {
		while(true) {
			camina();
		}
	}
	
	


}
