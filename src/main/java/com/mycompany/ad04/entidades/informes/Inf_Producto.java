
package com.mycompany.ad04.entidades.informes;

/**
 * Clase Inf_Producto
 * 
 * @author Marcelino Álvarez García
 */
public class Inf_Producto{

    public long idProducto;
    public String nombre;
    public int stock;

    
    /**
     * Constructor
     * 
     * @param idProducto        Identificador del producto
     * @param nombre            Nombre del producto
     * @param stock             Cantidad 
     */
    public Inf_Producto(long idProducto, String nombre, int stock) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.stock = stock;
    }

 }  
