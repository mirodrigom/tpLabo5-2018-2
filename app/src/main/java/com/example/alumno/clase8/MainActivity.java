package com.example.alumno.clase8;

import android.content.Intent;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback,SearchView.OnQueryTextListener {

    private List<Noticias> listaNoticias;
    private MyAdapter adapter;
    private RecyclerView rv;
    public Handler handler;
    private TextView txtMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtMain = (TextView) findViewById(R.id.txtMain);
        this.listaNoticias = new ArrayList<Noticias>();

        this.handler = new Handler(this);
        Hilos hiloUno = new Hilos("lo-ultimo",handler,"xml");
        hiloUno.start();
        this.rv = (RecyclerView) findViewById(R.id.rv);
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
            this.adapter.setListaOriginal((List<Noticias>) msg.obj);
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
        Log.wtf("Submit: ",query);
        List<Noticias> listaBuscada = new ArrayList<Noticias>();
        for(Noticias noticia : this.adapter.getListaOriginal())
        {
            if(noticia.getTitulo().toLowerCase().contains(query.toLowerCase()) || noticia.getDescripcion().toLowerCase().contains(query.toLowerCase()))
            {
                listaBuscada.add(noticia);
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
            this.adapter.setLista(this.adapter.getListaOriginal());
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

}
