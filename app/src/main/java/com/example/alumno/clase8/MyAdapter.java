package com.example.alumno.clase8;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Created by alumno on 01/11/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>
{

    private List<Noticias> listaNoticias;
    private MainActivity mainActivity;
    private Handler handler;

    public MyAdapter(List<Noticias> lista, MainActivity act)
    {
        this.listaNoticias = lista;
        this.mainActivity = act;
        this.handler = act.handler;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = this.mainActivity.getLayoutInflater().from(this.mainActivity).inflate(R.layout.noticias,parent, false);
        MyViewHolder myv = new MyViewHolder(vista);
        return myv;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtDescripcion.setText(this.listaNoticias.get(position).getDescripcion());
        holder.txtTitulo.setText(this.listaNoticias.get(position).getTitulo());
        holder.txtFuente.setText(this.listaNoticias.get(position).getFuente());
        holder.txtFecha.setText(this.listaNoticias.get(position).getFecha());
        holder.setPosition(position);

        if(this.listaNoticias.get(position).getBuscando() == false)
        {
            if(this.listaNoticias.get(position).getImageByte() == null)
            {
                this.listaNoticias.get(position).setBuscando(true);
                Hilos hiloImagen = new Hilos(this.listaNoticias.get(position).getImageUrl(),this.handler,"img",position);
                hiloImagen.start();
            }
        }
        //Falta imagen, y fecha parsear
    }

    @Override
    public int getItemCount() {
        return this.listaNoticias.size();
    }

    public void setLista(List<Noticias> lista )
    {
        this.listaNoticias = lista;
    }

    public void setImage(int position,byte[] imageBytes)
    {
       // this.listaNoticias.get(position).setImageByte(imageBytes);
    }
}
