
package com.mycompany.ad03.disco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 * Clase Archivo
 * 
 * @author Marcelino Álvarez García
 */
public class Archivo {
    
    /**
     * Lee el archivo
     *
     * @param nombreFichero Nombre del fichero
     * @return              Contenido del archivo
     */   
    public String leerArchivo(String nombreFichero){
        String entrada="";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
           // Apertura del fichero y creacion de BufferedReader 
           archivo = new File (nombreFichero);
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);

           // Lectura del fichero
           String linea;
           while((linea=br.readLine())!=null)
              entrada+=linea;
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           try{                    
              if( null != fr ){   
                  // Cerrar fichero
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }   
        return entrada;
    }        
    
}
