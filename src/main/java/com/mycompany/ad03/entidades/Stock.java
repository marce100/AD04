
package com.mycompany.ad03.entidades;

import java.sql.Connection;

/**
 * Clase Stock
 * 
 * @author Marcelino Álvarez García
 */
public class Stock {
    
    private int idStock;
    private int cantidad;
    private int idTienda;
    private int idProducto;  

    /**
     * Constructor
     * 
     * @param idStock       Clave primaria
     * @param cantidad      Cantidad de productos de una tienda
     * @param idTienda      Identificador de la tienda
     * @param idProducto    Identificador del producto
     */
    public Stock(int idStock, int cantidad, int idTienda, int idProducto) {
        this.idStock = idStock;
        this.cantidad = cantidad;
        this.idTienda = idTienda;
        this.idProducto = idProducto;
    }

    /*
     *  Getters 
     */
    
    public int getIdStock() {
        return idStock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public int getIdProducto() {
        return idProducto;
    }

}

