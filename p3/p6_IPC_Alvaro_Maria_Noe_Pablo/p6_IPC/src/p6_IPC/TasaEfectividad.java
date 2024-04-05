package p6_IPC;

import java.util.ArrayList;

public class TasaEfectividad extends Medida {

    public TasaEfectividad (String id, String nombre, Tarea tarea, double optimo,
    						double objetivo, double peorAceptable) {
        super(id, nombre, tarea, optimo, objetivo, peorAceptable);
    }

    /**
     * Calcula la tasa de efectividad de la tarea como el cociente entre el
     * numero de objetivos completados entre el numero de objetivos totales de
     * la tarea.
     */
    public void calculaMedida () {
        ArrayList<Interaccion> completadas = getTarea().getListaInteraccionesCompletadas();
        ArrayList<Interaccion> objetivos = getTarea().getListaInteraccionesObjetivo();
        int numObjCompletados = 0;
        
        for (Interaccion i: completadas)
            if (objetivos.contains(i))
                numObjCompletados++;

        setMedida(numObjCompletados / objetivos.size());
    }
    
}
