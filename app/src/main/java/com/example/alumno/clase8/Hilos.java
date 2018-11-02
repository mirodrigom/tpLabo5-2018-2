package com.example.alumno.clase8;

import android.os.Message;

import android.os.Handler;

/**
 * Created by alumno on 01/11/2018.
 */

public class Hilos extends Thread {

    private String genero;
    private Handler mainActivtyHandler;

    public Hilos(String genero, Handler mainActivityHandler)
    {
        this.genero = genero;
        this.mainActivtyHandler = mainActivityHandler;
    }

    public void run()
    {
        Message msg = new Message();

        msg.arg1 = 1;
        Conexion conexion = new Conexion();
        byte[] info = conexion.obtenerString("https://www.clarin.com/rss/"+ this.genero +"/");
        msg.obj = ParserNoticias.getNoticias(new String(info));

        this.mainActivtyHandler.sendMessage(msg);
    }
}
