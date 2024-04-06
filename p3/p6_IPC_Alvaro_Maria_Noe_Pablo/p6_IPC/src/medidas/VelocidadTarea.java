package medidas;

import java.util.ArrayList;

import misc.Interaccion;
import misc.Tarea;

public class VelocidadTarea extends Medida {

    public VelocidadTarea (String id, String nombre, Tarea tarea, double optimo,
    					   double objetivo, double peorAceptable) {
        super(id, nombre, tarea, optimo, objetivo, peorAceptable);
    }

    public void calculaMedida () {
    	ArrayList<Interaccion> completadas = getTarea().getListaInteraccionesCompletadas();
    	
    	double tIni = (completadas.size() >= 1) ? completadas.get(0).getTiempoMilisegundos() : 0;
    	double tFin;
    	double tiempo;
    	
    	if (completadas.size() == 1)
    		tFin = getTarea().getTFin();
    	else if (completadas.size() > 1)
    		tFin = completadas.get(completadas.size() - 1).getTiempoMilisegundos();
    	else
    		tFin = 1;
    	
    	tiempo = tFin - tIni;
        
        setMedida(getTarea().getListaInteraccionesCompletadas().size() / tiempo);
    }
    
}
