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
                        }
                        if(n != null)
                        {
                            if("title".equals(xpp.getName()))
                            {
                                n.setTitulo(xpp.nextText());
                                Log.d("TAG","START TITLE");
                            }else if("link".equals(xpp.getName()))
                            {
                                n.setUrlDestino(xpp.nextText());
                                Log.d("TAG","START LINK");
                            }else if("description".equals(xpp.getName()))
                            {
                                n.setDescripcion(xpp.nextText());
                                Log.d("TAG","START DESCRIPTION");
                            }else if("pubDate".equals(xpp.getName()))
                            {
                                n.setFecha(xpp.nextText());
                                Log.d("TAG","START PUBDATE");
                            }else if("dc:creator".equals(xpp.getName()))
                            {
                                n.setFecha(xpp.getAttributeValue(null,"url"));
                                Log.d("TAG","START CREATOR");
                            }else if("enclosure".equals(xpp.getName()))
                            {
                                n.setImageUrl(xpp.getAttributeValue(null,"url"));
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if("item".equals(xpp.getName()))
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