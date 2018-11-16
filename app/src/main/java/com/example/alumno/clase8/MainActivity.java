package com.example.alumno.clase8;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback,SearchView.OnQueryTextListener {

    private List<Noticias> listaNoticias;
    private MyAdapter adapter;
    public Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Para probar */
        /*
        Noticias n1 = new Noticias("titulo","descripcion","image","fecha","fuente","categoria","urldestino");
        Noticias n2 = new Noticias("titulo","descripcion","image","fecha","fuente","categoria","urldestino");
        Noticias n3 = new Noticias("titulo","descripcion","image","fecha","fuente","categoria","urldestino");
        Noticias n4 = new Noticias("titulo","descripcion","image","fecha","fuente","categoria","urldestino");
        Noticias n5 = new Noticias("titulo","descripcion","image","fecha","fuente","categoria","urldestino");
        */
        this.listaNoticias = new ArrayList<Noticias>();
        /*
        this.listaNoticias.add(n1);
        this.listaNoticias.add(n2);
        this.listaNoticias.add(n3);
        this.listaNoticias.add(n4);
        this.listaNoticias.add(n5);
        */
        this.handler = new Handler(this);
        Hilos hiloUno = new Hilos("lo-ultimo",handler,"xml");
        hiloUno.start();
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        this.adapter = new MyAdapter(this.listaNoticias,this);
        rv.setAdapter(this.adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    /* Menu */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.buscar);
        SearchView sv = (SearchView) MenuItemCompat.getActionView(item);
        sv.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.rss)
        {
            Log.wtf("Seleccionado", "RSS");
            MyDialog md = new MyDialog();
            md.show(getSupportFragmentManager(),"");
        }else if(item.getItemId() == R.id.configuracion)
        {
            Log.wtf("Seleccionado", "Configuracion");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(Message msg) {

        if( msg.arg1 == 1)
        {
            this.adapter.setLista((List<Noticias>) msg.obj);
            this.adapter.notifyDataSetChanged();
        }else if( msg.arg1 == 2)
        {
            Log.wtf("handle","llego");
            this.adapter.setImage(msg.arg2,(byte[]) msg.obj);
            this.adapter.notifyItemChanged(msg.arg2);
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //Guardo la lista original x si esta null el string
        //notify data set changed
        //creo una lista nueva
        Log.wtf("Submit: ",query);
        List<Noticias> listaBuscada = new ArrayList<Noticias>();
        for(Noticias noticia : this.listaNoticias)
        {
            if(noticia.getTitulo().contains(query))
            {
                //listaBuscada.
            }
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.wtf("cambiotexto:",newText);
        return false;
    }
}
