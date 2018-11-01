package com.example.alumno.clase8;

/**
 * Created by alumno on 01/11/2018.
 */

public class Noticias {
    private String titulo;
    private String descripcion;
    private String imageUrl;
    private String fecha;
    private String fuente;
    private String categoria;
    private String urlDestino;

    public Noticias(){}

    public Noticias(String tit, String des, String img, String fec, String fue,String cat, String urlDestino)
    {
        this.titulo = tit;
        this.descripcion = des;
        this.imageUrl = img;
        this.fecha = fec;
        this.fuente = fue;
        this.categoria = cat;
        this.urlDestino = urlDestino;
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
}
