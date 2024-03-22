package p6_IPC;

import java.util.ArrayList;

public class TiempoTarea extends Medida {
    
    public TiempoTarea (String nombre, Tarea tarea, double optimo, double objetivo, double peorAceptable) {
        super(nombre, tarea, optimo, objetivo, peorAceptable);
    }

    public void calcularMedida (ArrayList<Interaccion> interacciones) {
        double tiempoIni = interacciones.get(0).getTiempoMilisegundos();
        double tiempoFin = interacciones.get(interacciones.size() - 1).getTiempoMilisegundos();
        this.setActual(tiempoFin - tiempoIni);
    }
}
