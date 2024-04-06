package medidas;

import java.util.ArrayList;

import misc.Interaccion;
import misc.Tarea;

public class TiempoTarea extends Medida {
    
    public TiempoTarea (String id, String nombre, Tarea tarea, double optimo,
    					double objetivo, double peorAceptable) {
        super(id, nombre, tarea, optimo, objetivo, peorAceptable);
    }

    public void calculaMedida () {
    	ArrayList<Interaccion> completadas = getTarea().getListaInteraccionesCompletadas();
    	
    	/* Si no se ha logrado completar ninguna de las interacciones objetivo
    	 * de la tarea se toma como tiempo de finalizacion de la tarea aquel
    	 * que fue registrado desde el editor al hacer clic sobre alguno de los
    	 * botones ("Tarea 1", "Tarea 2" o "Finalizar") que indique la finalizacion
    	 * de la tarea.
    	 * En caso contrario, se toma como tiempo de finalizacion el instante de
    	 * realizacion de la ultima actividad objetivo registrada */
    	double tFin;
    	if (completadas.isEmpty())
    		tFin = getTarea().getTFin();
    	else
    		tFin = completadas.get(completadas.size() - 1).getTiempoMilisegundos(); 
    		
        this.setMedida(tFin - getTarea().getTInicio());
    }
    
}
