package com.example.alumno.clase8;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alumno on 01/11/2018.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtDescripcion;
    public TextView txtTitulo;
    public TextView txtFuente;
    public TextView txtFecha;
    public TextView txtImageURL;
    private int position;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
        this.txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
        this.txtFuente = (TextView) itemView.findViewById(R.id.txtFuente);
        this.txtFecha = (TextView) itemView.findViewById(R.id.txtFecha);
        itemView.setOnClickListener(this);
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        Log.wtf("Abro el webview", "Posicion: " + String.valueOf(this.position));
    }
}
