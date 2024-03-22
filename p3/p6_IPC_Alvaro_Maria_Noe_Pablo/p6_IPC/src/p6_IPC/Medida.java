package p6_IPC;

import java.util.ArrayList;

public abstract class Medida {
    private String nombre;
    private Tarea tarea;
	private double optimo;
	private double objetivo;
	private double peorAceptable;
	private double actual;

    public Medida (String nombre, Tarea tarea, double optimo, double objetivo, double peorAceptable) {
        this.nombre = nombre;
        this.tarea = tarea;
        this.optimo = optimo;
        this.objetivo = objetivo;
        this.peorAceptable = peorAceptable;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre () {
        return nombre;
    }

    public void setTarea (Tarea tarea) {
        this.tarea = tarea;
    }

    public Tarea getTarea () {
        return tarea;
    }

    public void setOptimo (double optimo) {
        this.optimo = optimo;
    }

    public double getOptimo () {
        return optimo;
    }

    public void setObjetivo (double objetivo) {
        this.objetivo = objetivo;
    }

    public double getObjetivo () {
        return objetivo;
    }

    public void setPeorAceptable (double peorAceptable) {
        this.peorAceptable = peorAceptable;
    }

    public double getPeorAceptable () {
        return peorAceptable;
    }

    public void setActual (double actual) {
        this.actual = actual;
    }

    public double getActual () {
        return actual;
    }

    public void calcularMedida (ArrayList<Interaccion> interacciones) {}
}
