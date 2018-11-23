package com.example.alumno.clase8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alumno on 01/11/2018.
 */

public class Noticias implements Comparable {
    private String titulo;
    private String descripcion;
    private String imageUrl;
    private byte[] imageByte;
    private boolean buscando;
    private Date fecha; // SimpleDateFormat usar de java.utils
    private String fuente;
    private String categoria;
    private String urlDestino;
    private String[] rssLista;
    private int MAXTITULO = 150;

    public Noticias()
    {
        this.titulo = "";
        this.descripcion = "";
        this.imageUrl = "";
        this.fecha = null;
        this.fuente = "";
        this.categoria = "";
        this.urlDestino = "";
        this.imageByte = null;
        this.buscando = false;
        this.rssLista = null;
    }


    public Noticias(String tit, String des, String img, Date fec, String fue,String cat, String urlDestino)
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

        if(this.titulo.length() > this.MAXTITULO)
        {
            return this.titulo.substring(0,this.MAXTITULO) + "...";
        }
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        if(this.titulo.length() > 0)
        {
            int cantCaracteres = this.titulo.length() - this.MAXTITULO;
            if(cantCaracteres < 0)
            {
                cantCaracteres = cantCaracteres * -1;
            }
            if(this.descripcion.length() > cantCaracteres)
            {
                return this.descripcion.substring(0,cantCaracteres) + "...";
            }
        }


        return this.descripcion;
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
        if(this.fecha == null) {
            return null;
        }
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return fmt.format(this.fecha);
    }

    public void setFecha(String fecha)
    {
        //Mon, 19 Nov 2018 19:03:52 -0300
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        try {
            this.fecha = format.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    @Override
    public boolean equals(Object obj)
    {
        Noticias noti = (Noticias) obj;
        if(this.titulo.equalsIgnoreCase(noti.titulo))
        {
            return true;
        }
            return false;
    }


    @Override
    public int compareTo(Object o) {
        Noticias noti = (Noticias) o;
        return getFecha().compareTo(noti.getFecha());
    }
}
