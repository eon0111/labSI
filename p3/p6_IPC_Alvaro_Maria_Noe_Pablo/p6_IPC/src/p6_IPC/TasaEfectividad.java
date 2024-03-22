package p6_IPC;

import java.util.ArrayList;

public class TasaEfectividad extends Medida {

    public TasaEfectividad(String nombre, Tarea tarea, double optimo, double objetivo, double peorAceptable) {
        super(nombre, tarea, optimo, objetivo, peorAceptable);
    }

    public void calcularMedida (ArrayList<Interaccion> interacciones) {
        int numObj = 0;
        ArrayList<Interaccion> objetivos = getTarea().getListaInteraccionesObjetivo();
        for (Interaccion i: interacciones) {
            if (objetivos.contains(i)) {
                numObj++;
            }
        }
        setActual(numObj / objetivos.size());
    }
}
