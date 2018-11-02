package com.example.alumno.clase8;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private List<Noticias> listaNoticias;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Para probar */
        Noticias n1 = new Noticias("titulo","descripcion","image","fecha","fuente","categoria","urldestino");
        Noticias n2 = new Noticias("titulo","descripcion","image","fecha","fuente","categoria","urldestino");
        Noticias n3 = new Noticias("titulo","descripcion","image","fecha","fuente","categoria","urldestino");
        Noticias n4 = new Noticias("titulo","descripcion","image","fecha","fuente","categoria","urldestino");
        Noticias n5 = new Noticias("titulo","descripcion","image","fecha","fuente","categoria","urldestino");
        this.listaNoticias = new ArrayList<Noticias>();
        this.listaNoticias.add(n1);
        this.listaNoticias.add(n2);
        this.listaNoticias.add(n3);
        this.listaNoticias.add(n4);
        this.listaNoticias.add(n5);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        this.adapter = new MyAdapter(this.listaNoticias,this);
        rv.setAdapter(this.adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Handler handler = new Handler(this);
        Hilos hiloUno = new Hilos("lo-ultimo",handler);
        hiloUno.start();
    }


    @Override
    public boolean handleMessage(Message msg) {

        if( msg.arg1 == 1)
        {
            this.adapter.setLista((List<Noticias>) msg.obj);
            this.adapter.notifyDataSetChanged();


        }
        return false;
    }
}
