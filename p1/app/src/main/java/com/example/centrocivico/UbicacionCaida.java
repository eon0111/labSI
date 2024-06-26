package com.example.centrocivico;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.Date;

public class UbicacionCaida {

    private String latitud;
    private String longitud;
    private String instante;

    public UbicacionCaida() {

    }

    @SuppressLint("NewApi")
    public UbicacionCaida(String latitud, String longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.instante = LocalDateTime.now().toString();
    }

    public String getLatitud() {
        return latitud;
    }

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
        return "Latitud: " + this.latitud + "\nLongitud: " + this.longitud + "\nInstante: " + this.instante + "\n";
    }
}
