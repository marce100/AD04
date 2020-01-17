
package com.mycompany.ad03.entidades;

/**
 * Clase Producto
 * 
 * @author Marcelino Álvarez García
 */
public class Producto {
    
    private int idProducto;
    private String nombre;
    private String descripcion;
    private float precio;

    /**
     * Constructor
     * 
     * @param idProducto    Clave primaria
     * @param nombre        Nombre
     * @param descripcion   Descripción
     * @param precio        Importe
     */
    public Producto(int idProducto, String nombre, String descripcion, float precio){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }    

    /*
     *  Getters 
     */
    
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }
    
    /**
     * Devuelve formateados los datos del producto
     * 
     * @return          Datos del producto
     */
    public String toString(){       
        return(String.format("%-29s %-29s %10.2f €",nombre,descripcion,precio));        
    }    
    
}
