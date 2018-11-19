package com.example.alumno.clase8;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    String url;
    String titulo;
    String descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Website");
        ab.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        this.url = intent.getExtras().getString("url");
        this.titulo = intent.getExtras().getString("titulo");
        this.descripcion = intent.getExtras().getString("descripcion");
        WebView wv = (WebView) findViewById(R.id.webview);
        wv.setWebViewClient(new WebViewClient());
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        wv.loadUrl(url);

        //Flowinactionbutton del share
        //boton de back en el menu
        //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuwebview,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if( id == android.R.id.home)
        {
            finish();
        }else {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = this.descripcion;
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, this.titulo);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody + "\nFuente: ( " + this.url + " )");
            startActivity(Intent.createChooser(sharingIntent, "Compartir via"));
        }
        return super.onOptionsItemSelected(item);
    }
}
