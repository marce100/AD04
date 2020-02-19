
package com.mycompany.ad04.disco;

import com.google.gson.GsonBuilder;
import com.mycompany.ad04.entidades.informes.Informe;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


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
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader 
            fr = new FileReader (new File (nombreFichero));
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
              entrada+=linea;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{     
                // Cerrar fichero               
                if( null != fr )
                    fr.close();     
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }   
        return entrada;
    }    

    
    /**
     * Guarda un informe en formato JSON
     *
     * @param nombreFichero Nombre del fichero
     * @param informe       Objeto que contiene el informe a guardar
     */ 
    public void guardarArchivo(String nombreFichero, Informe informe){
        try (FileWriter writer = new FileWriter(nombreFichero)) {
            //new Gson().toJson(informe, writer);
            new GsonBuilder().setPrettyPrinting().create().toJson(informe, writer);
            System.out.println("Se ha guardado el informe.");
        } catch (IOException e) {
            System.out.println("No se ha podido guardar el informe.");
            e.printStackTrace();
        }    
    }
    
}
