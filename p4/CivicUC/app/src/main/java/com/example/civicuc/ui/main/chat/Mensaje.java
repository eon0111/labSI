package com.example.civicuc.ui.main.chat;

import java.time.LocalDateTime;
import java.util.Date;

public class Mensaje {

    private String emisor;
    private String contenido;
    private String momento;

    public Mensaje() { }

    public Mensaje(String emisor, String contenido, String momento) {
        this.emisor = emisor;
        this.contenido = contenido;
        this.momento = momento;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    @Override
    public String toString() {
        LocalDateTime momentoParseado = LocalDateTime.parse(this.momento);
        int hora = momentoParseado.getHour();
        return emisor + "\n"
               + contenido + "\n"
               + ((hora < 10) ? "0" : "") + hora + ":"
               + momentoParseado.getMinute();
    }
}
