
package com.mycompany.ad04.entidades.informes;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Inf_Tienda
 * 
 * @author Marcelino Álvarez García
 */
public class Inf_Tienda{

    public long idTienda;
    public String nombre;
    public List<Inf_Producto> productos;

    
    /**
     * Constructor
     * 
     * @param idTienda      Identificador de la tienda
     * @param nombre        Nombre de la tienda
     */
    public Inf_Tienda(long idTienda, String nombre) {
        this.idTienda = idTienda;
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    
    /**
     * Añade un producto a la lista de productos de la tienda
     * 
     * @param producto      Producto a añadir
     */
    public void addProducto(Inf_Producto producto){
       this.productos.add(producto);
    }

}
