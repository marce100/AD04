/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ad04.entidades;

import com.mycompany.ad04.entidades.composite.StockId;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Clase Stock
 * 
 * @author Marcelino Álvarez García
 */
@Entity(name = "Stock")
@Table(name = "stocks")  
public class Stock {

    @EmbeddedId
    private StockId id;
    @Column(name = "cantidad")
    private int cantidad;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTienda")
    @JoinColumn(name = "idTienda")
    private Tienda tienda;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProducto")
    @JoinColumn(name = "idProducto")
    private Producto producto;


    /**
     * Constructor por defecto
     */    
    private Stock() {}

    
    /**
     * Constructor 
     * 
     * @param tienda            Tienda
     * @param producto          Producto
     */
    public Stock(Tienda tienda, Producto producto) {
        this.tienda = tienda;
        this.producto = producto;
        this.id = new StockId(tienda.getIdTienda(), producto.getIdProducto());
    }

    
    /**
     * Getters y setters
     */
          
    public StockId getId() {
        return id;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
