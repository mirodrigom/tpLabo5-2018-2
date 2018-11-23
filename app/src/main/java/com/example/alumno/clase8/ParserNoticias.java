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

public class ParserNoticias {

    public static synchronized List<Noticias> getNoticias(String xmlString)
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
                            }else if("link".equals(xpp.getName()))
                            {
                                n.setUrlDestino(xpp.nextText());
                            }else if("description".equals(xpp.getName()))
                            {
                                n.setDescripcion(xpp.nextText());
                            }else if("pubDate".equals(xpp.getName()))
                            {
                                n.setFecha(xpp.nextText());

                            }else if("creator".equals(xpp.getName()))
                            {
                                n.setFuente(xpp.nextText());
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
                        break;
                    case XmlPullParser.END_DOCUMENT:
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