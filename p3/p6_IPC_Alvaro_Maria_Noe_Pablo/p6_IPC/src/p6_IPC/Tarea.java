package p6_IPC;

import java.util.ArrayList;

public class Tarea {
	private String identificador;
	private String usuario; //usuario que realiza la tarea
	private String enunciado; //lo que debe conseguir
	private ArrayList<Interaccion> listaInteracciones;
	
	public Tarea(String identificador, String usuario, String enunciado, ArrayList<Interaccion> listaInteracciones) {
		this.identificador = identificador;
		this.usuario = usuario;
		this.enunciado = enunciado;
		this.listaInteracciones = listaInteracciones;
	}
	
	public void anhadeInteraccion(Interaccion i) {
		listaInteracciones.add(i);
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

	public ArrayList<Interaccion> getListaInteracciones() {
		return listaInteracciones;
	}

	public void setListaInteracciones(ArrayList<Interaccion> listaInteracciones) {
		this.listaInteracciones = listaInteracciones;
	}
}
