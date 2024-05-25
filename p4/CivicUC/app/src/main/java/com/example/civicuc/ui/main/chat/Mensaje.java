package com.example.civicuc.ui.main.chat;

import java.util.Date;

public class Mensaje {

    private String emisor;
    private String contenido;
    private Date momento;

    public Mensaje(String emisor, String contenido, Date momento) {
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

    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }
}
