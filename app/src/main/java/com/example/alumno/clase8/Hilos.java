package com.example.alumno.clase8;

import android.os.Message;

import android.os.Handler;
import android.util.Log;

/**
 * Created by alumno on 01/11/2018.
 */

public class Hilos extends Thread {

    private String url;
    private Handler elHandler;
    private String quebusco;
    private int position;

    public Hilos(String url, Handler elHandler,String quebusco)
    {
        this.url = url;
        this.elHandler = elHandler;
        this.quebusco = quebusco;
        this.position = 0;
    }

    public Hilos(String url, Handler elHandler,String quebusco,int position)
    {
        this.url = url;
        this.elHandler = elHandler;
        this.quebusco = quebusco;
        this.position = position;
    }

    public void run()
    {
        Message msg = new Message();
        if(this.quebusco == "xml")
        {
            msg.arg1 = 1;
            Conexion conexion = new Conexion();
            byte[] info = conexion.obtenerString("https://www.clarin.com/rss/"+ this.url +"/");
            msg.obj = ParserNoticias.getNoticias(new String(info));
        }else if(this.quebusco == "img")
        {
            Log.wtf("url",this.url);
            Log.wtf("quebusco",this.quebusco);
            msg.arg1 = 2;
            msg.arg2 = this.position;
            Conexion conexion = new Conexion();
            byte[] info = conexion.obtenerString(this.url);
            msg.obj = info;
        }
            this.elHandler.sendMessage(msg);
    }
}
