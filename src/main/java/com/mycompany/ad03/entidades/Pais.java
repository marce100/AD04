
package com.mycompany.ad03.entidades;

import com.mycompany.ad03.bd.BD;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Pais
 * 
 * @author Marcelino Álvarez García
 */
public class Pais {
    
    private List<Provincia> provincias;

    /**
     * Constructor
     */
    public Pais(){      
        provincias=new ArrayList();
    }
    
    /**
     * Devuelve la lista de todas las provincias
     * 
     * @return  Lista de provincias
     */
    public List<Provincia> getProvincias(){
        return this.provincias;
    }
    
    /**
     * Comprueba si existe una provincia
     * 
     * @param con           Sesión con la base de datos
     * @param idProvincia   Identificador de la provincia a buscar
     * @return              Si existe o no la provincia
     */
    public boolean existeProvincia(Connection con,int idProvincia){                 
        return BD.existeProvincia(con, idProvincia);     
    }    
    
    /**
     * Muestra por pantalla el listado de las provincias
     *
     * @param con           Sesión con la base de datos
     */    
    public void getProvinciasStr(Connection con){    
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("LISTADO DE PROVINCIAS");      
        System.out.println("------------------------------------------------------------------------------");             
        List<Provincia> provincias=BD.selectProvincias(con);
        Provincia p;
        for (int i=1; i<=provincias.size(); i++){
            p=provincias.get(i-1);
            System.out.printf("%-3s %-24s",p.getId(),p.getNome());
            if (i%3==0 )System.out.println();
        }
        System.out.println();
    }

}
