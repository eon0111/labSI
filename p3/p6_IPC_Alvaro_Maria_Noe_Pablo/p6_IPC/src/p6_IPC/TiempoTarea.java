package p6_IPC;

import java.util.ArrayList;

public class TiempoTarea extends Medida {
    
    public TiempoTarea (String id, String nombre, Tarea tarea, double optimo,
    					double objetivo, double peorAceptable) {
        super(id, nombre, tarea, optimo, objetivo, peorAceptable);
    }

    public void calcularMedida () {
    	ArrayList<Interaccion> completadas = getTarea().getListaInteraccionesCompletadas(); 
        double tiempoIni = completadas.get(0).getTiempoMilisegundos();
        double tiempoFin = completadas.get(completadas.size() - 1).getTiempoMilisegundos();
        this.setMedida(tiempoFin - tiempoIni);
    }
    
}
