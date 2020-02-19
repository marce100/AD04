
package com.mycompany.ad04.entidades.informes;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Informe
 * 
 * @author Marcelino Álvarez García
 */
public class Informe {
    
    public List<Inf_Tienda> tiendas;

    
    /**
     * Constructor por defecto
     */
    public Informe() {
        this.tiendas = new ArrayList<>();
    }

    
    /**
     * Añade una tienda al informe
     * 
     * @param tienda        Tienda a añadir
     */
    public void addTienda(Inf_Tienda tienda){
        this.tiendas.add(tienda);
    }

    
    /**
     * Devuelve la última tienda del informe
     * 
     * @return              Tienda
     */
    public Inf_Tienda getTienda(){
        return tiendas.get(tiendas.size()-1);
    }
    
    
    /**
     * Comprueba si ya existe la tienda en el informe
     * 
     * @param tienda        Tienda
     * @return              Si existe o no
     */
    public boolean existeTienda(Inf_Tienda tienda){
        for (int i=0; i<tiendas.size(); i++){
            if (tienda.idTienda==tiendas.get(i).idTienda) return true;
        }   
        return false;
    }        
  
}
