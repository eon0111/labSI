package misc;

import java.util.Date;

public class Interaccion {
	private String identificador;
	private String nombre; //lo que realiza
	private String infoComplementaria; //saber que busca, fragmento que selecciona
	private long tiempoMilisegundos; //instante en el que se realiza la interaccion
	
	public Interaccion(String identificador, String nombre, String infoComplementaria,
					   long tiempoMilisegundos) {
		this.identificador = identificador;
		this.nombre = nombre;
		this.infoComplementaria = infoComplementaria;
		this.tiempoMilisegundos = tiempoMilisegundos;
	}
	
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getInfoComplementaria() {
		return infoComplementaria;
	}

	public void setInfoComplementaria(String infoComplementaria) {
		this.infoComplementaria = infoComplementaria;
	}

	public long getTiempoMilisegundos() {
		return tiempoMilisegundos;
	}

	public void setTiempoMilisegundos() {
		Date date = new Date();
		this.tiempoMilisegundos = date.getTime();
	}
}
