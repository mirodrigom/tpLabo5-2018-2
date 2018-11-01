package com.example.alumno.clase8;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by alumno on 01/11/2018.
 */

public class Conexion {

    public byte[] obtenerString(String urlString)
    {

        int response = 0;
        HttpsURLConnection conexion= null;
        URL url;
        try {
            url = new URL(urlString);
            conexion = (HttpsURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.connect();
            response = conexion.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response == 200)
        {
            try {
                InputStream is = conexion.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = is.read(buffer)) != -1)
                {
                    baos.write(buffer,0,length);
                }
                is.close();
                return baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
