/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ad03.parser;

import com.mycompany.ad03.parser.ParserXML;
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author axians
 */
public class Titulares {
    
    /*
     * Leer titulares
     */
    public void mostrarTitulares(int opcion){

        //Procesar archivo XML
        XMLReader procesadorXML = null;
        try {
            
            System.out.println("------------------------------------------------------------------------------");            
            //Creamos un parseador de texto y le añadimos la clase que va a parsear el texto
            procesadorXML = XMLReaderFactory.createXMLReader();
            ParserXML persoasXML = new ParserXML();
            procesadorXML.setContentHandler(persoasXML);

            //Indicamos el archivo XML                        
            InputSource archivoXML;
            if(opcion==1) 
                archivoXML = new InputSource("portada.xml");
            else                
                archivoXML = new InputSource("http://ep00.epimg.net/rss/elpais/portada.xml");           
            procesadorXML.parse(archivoXML);

            //Imprimimos los datos leidos del XML
            ArrayList<Noticia> noticias = persoasXML.getNoticias();
            for(int i=0;i<noticias.size();i++){
                Noticia noticiaAux = noticias.get(i);
                System.out.println("Titulo " +(i+1)+ ": " + noticiaAux.getTitulo());
            }

        } catch (SAXException e) {
            System.out.println("Error al leer el archivo XML !!!");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo XML !!!");
        }                         
    }
}
