package com.example.alumno.clase8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private List<Noticias> listaOriginal;

    public MyAdapter(List<Noticias> lista, MainActivity act)
    {
        this.listaNoticias = lista;
        this.mainActivity = act;
        this.handler = act.handler;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = this.mainActivity.getLayoutInflater().from(this.mainActivity).inflate(R.layout.noticias,parent, false);
        MyViewHolder myv = new MyViewHolder(vista,this.mainActivity);
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
                Hilos hiloImagen = new Hilos(this.listaNoticias.get(position).getImageUrl(),this.handler,"img",position);
                hiloImagen.start();
            }else{
                Bitmap bitmap = BitmapFactory.decodeByteArray(this.listaNoticias.get(position).getImageByte(), 0, this.listaNoticias.get(position).getImageByte().length);
                holder.imgNoticia.setImageBitmap(bitmap);
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

    public void setListaOriginal(List<Noticias> lista )
    {
        this.listaOriginal = lista;
    }

    public List<Noticias> getListaOriginal()
    {
        return this.listaOriginal;
    }

    public List<Noticias> getLista()
    {
        return this.listaNoticias;
    }

    public void setImage(int position,byte[] imageBytes)
    {
        this.listaNoticias.get(position).setImageByte(imageBytes);
    }


}
