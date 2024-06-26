package misc;

import java.util.ArrayList;

public class Tarea {
	
	private String identificador;
	private String usuario; //usuario que realiza la tarea
	private String enunciado; //lo que debe conseguir
	private ArrayList<Interaccion> listaInteraccionesObjetivo;
	private ArrayList<Interaccion> listaInteraccionesCompletadas;
	private double tInicio;
	private double tFin;
	
	public Tarea(String identificador, String usuario, String enunciado) {
		this.identificador = identificador;
		this.usuario = usuario;
		this.enunciado = enunciado;
		this.listaInteraccionesObjetivo = new ArrayList<Interaccion>();
		this.listaInteraccionesCompletadas = new ArrayList<Interaccion>();
	}
	
	public void anhadeObjetivo(Interaccion i) {
		listaInteraccionesObjetivo.add(i);
	}
	
	public void completaObjetivo(Interaccion i) {
		listaInteraccionesCompletadas.add(i);
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public ArrayList<Interaccion> getListaInteraccionesObjetivo() {
		return listaInteraccionesObjetivo;
	}
	
	public ArrayList<Interaccion> getListaInteraccionesCompletadas() {
		return listaInteraccionesCompletadas;
	}
	
	public double getTInicio () {
		return tInicio;
	}
	
	public void setTInicio () {
		this.tInicio = System.currentTimeMillis();
	}
	
	public double getTFin () {
		return tFin;
	}
	
	public void setTFin () {
		this.tFin = System.currentTimeMillis();
	}
	
}
