
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

import fundamentos.*;

public class Persona
{
    // Atributos de la persona en movimiento por el edificio
    private int plantaOrigen;   // Planta de partida
    private int plantaDestino;  // Planta destino
    private int retraso;        // Tiempo que transcurre en el cambio de posicion
    private ColorFig color;     // Color que representa a la persona en el dibujo
    private Ascensor ascensor;  // Ascensor que se utiliza
    private int estado = 0;
    private int contador = 0;

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
    
    public int estado () {

    	return estado;
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
    public void camina ()
    {
       // Implementar este metodo
    	int espera = retraso/2000;
    	int auxOrigen;
    	
    	switch (estado) {
    	
    	//Caso inicial, la persona se encuentra en el pasillo
    		case 0: 
    			contador++;
    			//simulacion del transito por el pasillo
    			if (espera == contador) {
    				//comienza la espera al ascensor
    				ascensor.insertaEnEspera(this, plantaOrigen);
    				//la persona ya ha conseguido caminar por el pasillo ahora esta esperando al ascensor
    				//el siguiente estado es en la espera
    				estado++;
    				//se borra del pasillo a la persona
    				ascensor.borraDePasillo(this, plantaOrigen);
    				contador = 0;
    			}
    			break;
    		//Simulacion del retraso en la zona de espera
    		case 1:	
    			contador++;
    			if(espera == contador) {
    				contador = 0;
    				estado++;
    			} 
    			break;
    		//Simulacion del tiempo esperando al ascensor a q recoja a las personas
    		case 2:
				if (ascensor.plantaActual() == plantaOrigen && ascensor.estaParado()) {
					ascensor.insertaEnAscensor(this);
					ascensor.borraDeEspera(this, plantaOrigen);
					estado++;
				}
    			break;
    		//Simulacion salida del ascensor
    		case 3: 
				if(ascensor.plantaActual() == plantaDestino) {
					ascensor.borraDeAscensor(this);
					ascensor.insertaEnEspera(this, plantaDestino);
					estado++;
				}
    			break;
    		//Simulacion camino de salida del ascensor
    		case 4:
    			contador++;
    			if (espera == contador) {
    				ascensor.borraDeEspera(this, plantaDestino);
    				estado = 0;
    				ascensor.insertaEnPasillo(this, plantaDestino);
    				contador = 0;
    				auxOrigen = plantaOrigen;
    				plantaOrigen = plantaDestino;
    				plantaDestino = auxOrigen;
    			} 
    			break;
    		default:
    			break;
    	}
    }

}
