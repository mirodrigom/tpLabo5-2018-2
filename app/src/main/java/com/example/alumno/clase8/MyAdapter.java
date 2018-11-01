package com.example.alumno.clase8;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by alumno on 01/11/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>
{

    private List<Noticias> listaNoticias;
    private MainActivity mainActivity;

    public MyAdapter(List<Noticias> lista, MainActivity act)
    {
        this.listaNoticias = lista;
        this.mainActivity = act;
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
        //Falta imagen, y fecha parsear
    }

    @Override
    public int getItemCount() {
        return this.listaNoticias.size();
    }
}
