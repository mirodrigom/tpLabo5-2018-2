package com.example.alumno.clase8;

import java.util.ArrayList;

/**
 * Created by alumno on 01/11/2018.
 */

public class Noticias {
    private String titulo;
    private String descripcion;
    private String imageUrl;
    private byte[] imageByte;
    private boolean buscando;
    private String fecha; // SimpleDateFormat usar de java.utils
    private String fuente;
    private String categoria;
    private String urlDestino;
    private String[] rssLista;

    public Noticias()
    {
        this.titulo = "";
        this.descripcion = "";
        this.imageUrl = "";
        this.fecha = "";
        this.fuente = "";
        this.categoria = "";
        this.urlDestino = "";
        this.imageByte = null;
        this.buscando = false;
        this.rssLista = null;
    }

    public void SetListaRss()
    {
        this.rssLista = new String[] {"Lo Ultimo","Politica","Mundo","Sociedad","Policiales","Ciudades","Opinion","Cartas al pais","Cultura","Rural","Economia","Tecnologia","Revista Ñ"};
    }

    public Noticias(String tit, String des, String img, String fec, String fue,String cat, String urlDestino)
    {
        this.titulo = tit;
        this.descripcion = des;
        this.imageUrl = img;
        this.fecha = fec;
        this.fuente = fue;
        this.categoria = cat;
        this.urlDestino = urlDestino;
        this.imageByte = null;
        this.buscando = false;
        this.rssLista = null;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUrlDestino() {
        return urlDestino;
    }

    public void setUrlDestino(String urlDestino) {
        this.urlDestino = urlDestino;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    public boolean getBuscando() {
        return buscando;
    }

    public void setBuscando(boolean buscando) {
        this.buscando = buscando;
    }

    public boolean isBuscando() {
        return buscando;
    }

    public String[] getRssLista() {
        return rssLista;
    }

    public void setRssLista(String[] rssLista) {
        this.rssLista = rssLista;
    }
}
