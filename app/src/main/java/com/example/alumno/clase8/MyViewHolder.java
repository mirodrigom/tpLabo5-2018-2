package com.example.alumno.clase8;

import android.media.Image;
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

    public MainActivity mainActivity;
    public TextView txtDescripcion;
    public TextView txtTitulo;
    public ImageView imgFuente;
    public TextView txtFecha;
    public ImageView imgNoticia;
    private int position;

    public MyViewHolder(View itemView,MainActivity mainActivity) {
        super(itemView);
        this.txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
        this.txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
        this.imgFuente = (ImageView) itemView.findViewById(R.id.imgFuente);
        this.txtFecha = (TextView) itemView.findViewById(R.id.txtFecha);
        this.imgNoticia = (ImageView) itemView.findViewById(R.id.imgNoticia);
        this.mainActivity = mainActivity;
        itemView.setOnClickListener(this);
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        mainActivity.abrirWebsite(this.position);
    }
}
