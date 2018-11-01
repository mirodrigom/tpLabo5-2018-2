package com.example.alumno.clase8;

/**
 * Created by alumno on 01/11/2018.
 */

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedInputStream;

/**
 * Created by alumno on 04/10/2018.
 */

public class ParserNoticias {

    public static List<Noticias> getNoticias(String xmlString)
    {
        List<Noticias>listaNoticias = new ArrayList<Noticias>();

        XmlPullParser xpp = Xml.newPullParser();
        try {
            xpp.setInput(new StringReader(xmlString));

            int event = xpp.getEventType();
            Noticias n = null;
            while( event != XmlPullParser.END_DOCUMENT)
            {

                switch (event)
                {
                    case XmlPullParser.START_DOCUMENT:
                        Log.d("TAG","START DOCUMENT");
                        break;
                    case XmlPullParser.START_TAG:
                        if("item".equals(xpp.getName()))
                        {
                            n = new Noticias();
                        }else if("title".equals(xpp.getName()))
                        {
                            n.setTitulo(xpp.nextText());
                            Log.d("TAG","START FIRSTNAME");
                        }else if("last_name".equals(xpp.getName()))
                        {

                            Log.d("TAG","START LASTNAME");
                        }else if("phone".equals(xpp.getName()))
                        {

                            Log.d("TAG","START PHONE");
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if("persona".equals(xpp.getName()))
                        {
                            listaNoticias.add(n);
                        }
                        Log.d("TAG","FIN TAG");
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        Log.d("TAG","FIN DOCUMENT");
                        break;
                }
                event = xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaNoticias;
    }
}