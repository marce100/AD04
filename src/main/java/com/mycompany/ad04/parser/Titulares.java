
package com.mycompany.ad04.parser;

import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Clase Menu
 * 
 * @author Marcelino Álvarez García
 */
public class Titulares {
    
    private static final String ARCHIVO = "portada.xml";  
    private static final String WEB = "http://ep00.epimg.net/rss/elpais/portada.xml";  
      
    /**
     * Leer titulares
     * 
     * @param opcion    Leer desde archivo o desde la web
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
                archivoXML = new InputSource(ARCHIVO);
            else                
                archivoXML = new InputSource(WEB);           
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
