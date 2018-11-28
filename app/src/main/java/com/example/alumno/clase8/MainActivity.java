package com.example.alumno.clase8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback,SearchView.OnQueryTextListener, iMyDialog {

    private List<Noticias> listaNoticias;
    private MyAdapter adapter;
    private RecyclerView rv;
    public Handler handler;
    private TextView txtMain;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtMain = (TextView) findViewById(R.id.txtMain);
        this.listaNoticias = new ArrayList<Noticias>();
        this.handler = new Handler(this);
        this.rv = (RecyclerView) findViewById(R.id.rv);
        getPreferences();
        this.adapter = new MyAdapter(this.listaNoticias,this);
        rv.setAdapter(this.adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getPreferences()
    {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("configuracionCompartida", Context.MODE_PRIVATE);
        String listaPreferences = prefs.getString("listaChecked",null);
        JSONArray listaChecked = new JSONArray();
        if(listaPreferences != null)
        {
            try {
                listaChecked = new JSONArray(listaPreferences);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(listaChecked.length() == 0)
            {
                comenzarHilo("lo-ultimo");
            }else{
                for (int i = 0; i < listaChecked.length(); i++) {
                    try {
                        JSONObject checkbox = listaChecked.getJSONObject(i);
                        String url = checkbox.names().get(0).toString().replace(" ","-").toLowerCase();
                        Log.wtf("url",url);
                        comenzarHilo(url);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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
            MyDialog md = new MyDialog();
            md.show(getSupportFragmentManager(),"");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(Message msg) {

        if( msg.arg1 == 1) {
            if (msg.obj != null) {
                Log.wtf("Llego el request","hilo");
                this.adapter.addLista((List<Noticias>) msg.obj);
                this.adapter.setListaOriginal((List<Noticias>) msg.obj);
                Collections.sort(this.adapter.getLista(),Collections.reverseOrder());
                Log.wtf("Cantidad noticias:",String.valueOf(this.adapter.getLista().size()));
                this.adapter.notifyDataSetChanged();
            }
        }else if( msg.arg1 == 2)
        {
            this.adapter.setImage(msg.arg2,(byte[]) msg.obj);
            this.adapter.notifyItemChanged(msg.arg2);
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        List<Noticias> listaBuscada = new ArrayList<Noticias>();
        if(this.adapter.getListaOriginal() != null) {
            for (Noticias noticia : this.adapter.getListaOriginal()) {
                if (noticia.getTitulo().toLowerCase().contains(query.toLowerCase()) || noticia.getDescripcion().toLowerCase().contains(query.toLowerCase())) {
                    listaBuscada.add(noticia);
                }
            }
        }
        if(! listaBuscada.isEmpty()){
            this.adapter.setLista(listaBuscada);
            this.adapter.notifyDataSetChanged();
        }else{
            this.rv.setVisibility(View.GONE);
            this.txtMain.setText(R.string.NoEncontrado);
            this.txtMain.setVisibility(View.VISIBLE);
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length() >= 3) {
            this.onQueryTextSubmit(newText);
        }else if(newText.length() == 0){
            this.rv.setVisibility(View.VISIBLE);
            this.txtMain.setVisibility(View.GONE);
            if(this.adapter.getListaOriginal() != null) {
                this.adapter.setLista(this.adapter.getListaOriginal());
            }
            this.adapter.notifyDataSetChanged();
        }
        return false;
    }

    public void abrirWebsite(int posicion)
    {
        Intent i = new Intent(this,WebActivity.class);
        i.putExtra("url",this.adapter.getLista().get(posicion).getUrlDestino());
        i.putExtra("titulo",this.adapter.getLista().get(posicion).getTitulo());
        i.putExtra("descripcion",this.adapter.getLista().get(posicion).getDescripcion());
        startActivity(i);
    }

    public void comenzarHilo(String url)
    {
        Log.wtf("Entro al hilo",url);
        Hilos hiloUno = new Hilos(url,handler,"xml");
        hiloUno.start();
    }

    @Override
    public void onChangeRss(Boolean val, Boolean vacio) {

        this.adapter.setLista(new ArrayList<Noticias>());

        if(vacio == true)
        {
            this.adapter.notifyDataSetChanged();
        }
        if(val == true)
        {
            getPreferences();
        }
    }
}
