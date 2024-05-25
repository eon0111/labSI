package com.example.civicuc.ui.main.caida;

import android.annotation.SuppressLint;
import android.os.LocaleList;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class UbicacionCaida {

    private String usuario;
    private String latitud;
    private String longitud;
    private String instante;

    public UbicacionCaida() { }

    public UbicacionCaida(String latitud, String longitud, String usuario) {
        this.usuario = usuario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.instante = LocalDateTime.now().toString();
    }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getLatitud() { return latitud; }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getInstante() {
        return instante;
    }

    public void setInstante(String instante) {
        this.instante = instante;
    }

    @NonNull
    public String toString() {
        LocalDateTime instante_parseado = LocalDateTime.parse(this.instante);
        int hora = instante_parseado.getHour();
        return "Usuario: " + usuario + "\n"
                + "Aula: " + ((int)(Double.parseDouble(this.latitud) + Double.parseDouble(this.longitud)) % 50) + "\n"
                + instante_parseado.getDayOfMonth() + "-"
                + instante_parseado.getMonth().toString() + "-"
                + instante_parseado.getYear() + " --- "
                + ((hora < 10) ? "0" : "") + hora + ":"
                + instante_parseado.getMinute();
    }

}
