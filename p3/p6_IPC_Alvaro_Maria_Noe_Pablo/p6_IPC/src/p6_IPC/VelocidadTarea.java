package p6_IPC;

import java.util.ArrayList;

public class VelocidadTarea extends Medida {

    public VelocidadTarea (String nombre, Tarea tarea, double optimo,
    					   double objetivo, double peorAceptable) {
        super(nombre, tarea, optimo, objetivo, peorAceptable);
    }

    public void calcularMedida () {
    	ArrayList<Interaccion> completadas = getTarea().getListaInteraccionesCompletadas(); 
        double tiempoIni = completadas.get(0).getTiempoMilisegundos();
        double tiempoFin = completadas.get(completadas.size() - 1).getTiempoMilisegundos();
        double tiempo = tiempoFin - tiempoIni;
        setMedida(getTarea().getListaInteraccionesObjetivo().size() / tiempo);
    }
}
