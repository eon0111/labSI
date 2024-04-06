package medidas;

import java.util.ArrayList;

import misc.Interaccion;
import misc.Tarea;

public class VelocidadTarea extends Medida {

    public VelocidadTarea (String id, String nombre, Tarea tarea, double optimo,
    					   double objetivo, double peorAceptable) {
        super(id, nombre, tarea, optimo, objetivo, peorAceptable);
    }

    public void calcularMedida () {
    	ArrayList<Interaccion> completadas = getTarea().getListaInteraccionesCompletadas(); 
        double tiempoIni = completadas.get(0).getTiempoMilisegundos();
        double tiempoFin = completadas.get(completadas.size() - 1).getTiempoMilisegundos();
        double tiempo = tiempoFin - tiempoIni;
        setMedida((double)getTarea().getListaInteraccionesObjetivo().size() / tiempo);
    }
    
}
