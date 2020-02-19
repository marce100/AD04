
package com.mycompany.ad04.entidades;

import com.mycompany.ad04.bd.BD;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 * Clase Pais
 * 
 * @author Marcelino Álvarez García
 */
public class Pais {
    
    private List<Provincia> provincias;

    
    /**
     * Constructor por defecto
     */
    public Pais(){      
        provincias=new ArrayList();
    }
    
    
    /**
     * Devuelve la lista de todas las provincias
     * 
     * @return              Lista de provincias
     */
    public List<Provincia> getProvincias(){
        return this.provincias;
    }
    
    
    /**
     * Comprueba si existe una provincia
     * 
     * @param session       Sesión con la base de datos
     * @param idProvincia   Identificador de la provincia a buscar
     * @return              Si existe o no la provincia
     */
    public boolean existeProvincia(Session session,int idProvincia){                 
        return BD.existeProvincia(session, idProvincia);     
    }    
    
    
    /**
     * Muestra por pantalla el listado de las provincias
     *
     * @param session       Sesión con la base de datos
     */    
    public void getProvinciasStr(Session session){    
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("LISTADO DE PROVINCIAS");      
        System.out.println("------------------------------------------------------------------------------");             
        List<Provincia> provincias=BD.selectProvincias(session);
        Provincia p;
        for (int i=1; i<=provincias.size(); i++){
            p=provincias.get(i-1);
            System.out.printf("%-3s %-24s",p.getId(),p.getNome());
            if (i%3==0 )System.out.println();
        }
        System.out.println();
   }

}
