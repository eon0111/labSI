package medidas;

import misc.Tarea;

public abstract class Medida {
	
	private String id;
	private String nombre;
	private Tarea tarea;
	private double optimo;
	private double objetivo;
	private double peorAceptable;
	private double medida;

	public Medida (String id, String nombre, Tarea tarea, double optimo,
				   double objetivo, double peorAceptable) {
		this.id = id;
		this.nombre = nombre;
		this.tarea = tarea;
		this.optimo = optimo;
		this.objetivo = objetivo;
		this.peorAceptable = peorAceptable;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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

	public void setMedida (double actual) {
		this.medida = actual;
	}

	public double getMedida () {
		return medida;
	}
	
	public void calculaMedida () {}
	
}
