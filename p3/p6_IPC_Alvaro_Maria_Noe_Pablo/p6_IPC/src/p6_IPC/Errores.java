package p6_IPC;

import java.util.ArrayList;

public class Errores extends Medida {

    public Errores(String nombre, Tarea tarea, double optimo, double objetivo, double peorAceptable) {
        super(nombre, tarea, optimo, objetivo, peorAceptable);
    }

    public void calcularMedida (ArrayList<Interaccion> interacciones) {
        int errores = interacciones.size();
        ArrayList<Interaccion> objetivos = getTarea().getListaInteraccionesObjetivo();
        for (Interaccion i: interacciones) {
            if (objetivos.contains(i)) {
                errores--;
            }
        }
        setActual(errores);
    }
}
