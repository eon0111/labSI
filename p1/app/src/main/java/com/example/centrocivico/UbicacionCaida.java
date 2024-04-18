package com.example.centrocivico;

import android.annotation.SuppressLint;

import java.time.LocalDateTime;
import java.util.Date;

public class UbicacionCaida {

    private long latitud;
    private long longitud;
    private LocalDateTime instante;

    public UbicacionCaida() {

    }

    @SuppressLint("NewApi")
    public UbicacionCaida(long latitud, long longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.instante = LocalDateTime.now();
    }

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public void setInstante(LocalDateTime instante) {
        this.instante = instante;
    }

    public String toString() {
        return "Latitud: " + this.latitud + "\nLongitud: " + this.longitud + "\nInstante: " + this.instante.toString() + "\n";
    }
}
