package com.example.alumno.clase8;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alumno on 15/11/2018.
 */

public class MyDialog extends DialogFragment implements Dialog.OnClickListener {

    public View v;
    public CheckBox cboxUltimo;
    public CheckBox cboxPolitica;
    public CheckBox cboxMundo;
    public CheckBox cboxSociedad;
    public JSONArray listaChecked;

    private void setAttributes()
    {
        this.v = LayoutInflater.from(getActivity()).inflate(R.layout.rss_items,null);
        this.cboxUltimo = (CheckBox) v.findViewById(R.id.lo_ultimo);
        this.cboxUltimo.setChecked(false);
        this.cboxPolitica = (CheckBox) v.findViewById(R.id.politica);
        this.cboxPolitica.setChecked(false);
        this.cboxMundo = (CheckBox) v.findViewById(R.id.mundo);
        this.cboxMundo.setChecked(false);
        this.cboxSociedad = (CheckBox) v.findViewById(R.id.sociedad);
        this.cboxSociedad.setChecked(false);
        this.listaChecked =  new JSONArray();
    }

    private void getPreferences()
    {
        SharedPreferences prefs = getContext().getSharedPreferences("configuracionCompartida", Context.MODE_PRIVATE);
        String listaPreferences = prefs.getString("listaChecked",null);
        if(listaPreferences != null)
        {
            try {
                this.listaChecked = new JSONArray(listaPreferences);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < this.listaChecked.length(); i++) {
                try {
                    JSONObject checkbox = this.listaChecked.getJSONObject(i);
                    if(checkbox.has((String) this.cboxMundo.getText()))
                    {
                        this.cboxMundo.setChecked(checkbox.getBoolean((String) this.cboxMundo.getText()));
                    }else if(checkbox.has((String) this.cboxSociedad.getText()))
                    {
                        this.cboxSociedad.setChecked(checkbox.getBoolean((String) this.cboxSociedad.getText()));
                    }else if(checkbox.has((String) this.cboxPolitica.getText()))
                    {
                        this.cboxPolitica.setChecked(checkbox.getBoolean((String) this.cboxPolitica.getText()));
                    }else if(checkbox.has((String) this.cboxUltimo.getText()))
                    {
                        this.cboxUltimo.setChecked(checkbox.getBoolean((String) this.cboxUltimo.getText()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setSharedPreferences(JSONArray lista)
    {
        SharedPreferences prefs = getContext().getSharedPreferences("configuracionCompartida", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("listaChecked", lista.toString());
        editor.commit();
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Seleccionar RSS");
        this.setAttributes();
        builder.setView(this.v);
        builder.setPositiveButton("Guardar",this);
        builder.setNegativeButton("Cancelar",this);
        AlertDialog ad = builder.create();
        return ad;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == Dialog.BUTTON_POSITIVE)
        {
            this.listaChecked = new JSONArray();
            JSONObject objeto = null;
            if(this.cboxMundo.isChecked())
            {
                try {
                    objeto = new JSONObject().put((String) this.cboxMundo.getText(),true);
                    this.listaChecked.put(objeto);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(this.cboxPolitica.isChecked())
            {
                try {
                    objeto = new JSONObject().put((String) this.cboxPolitica.getText(),true);
                    this.listaChecked.put(objeto);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(this.cboxSociedad.isChecked())
            {
                try {
                    objeto = new JSONObject().put((String) this.cboxSociedad.getText(),true);
                    this.listaChecked.put(objeto);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(this.cboxUltimo.isChecked())
            {
                try {
                    objeto = new JSONObject().put((String) this.cboxUltimo.getText(),true);
                    this.listaChecked.put(objeto);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }else if(which == Dialog.BUTTON_NEGATIVE)
        {

        }
    }




}
