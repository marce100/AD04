
package com.mycompany.ad03.entidades;

/**
 * Clase Tienda
 * 
 * @author Marcelino Álvarez García
 */
public class Tienda {
    
    private int idTienda;
    private String nombre;
    private String ciudad;
    private Provincia provincia;

    /**
     * Constructor
     *
     * @param idTienda      Clave primaria
     * @param nombre        Nombre de la tienda
     * @param ciudad        Ciudad
     * @param provincia     Provincia
     */
    public Tienda(int idTienda, String nombre, String ciudad, Provincia provincia){
        this.idTienda=idTienda;
        this.nombre=nombre;
        this.ciudad=ciudad;   
        this.provincia=provincia;     
    }

    /*
     *  Getters 
     */
    
    public int getIdTienda() {
        return idTienda;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * Devuelve formateados los datos de la tienda
     * 
     * @return          Datos de la tienda
     */
    public String toString(){       
        return(String.format("%-24s %-24s %-24s",nombre,ciudad,provincia.getNome()));        
    }
  
}
