
package com.mycompany.ad04.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Clase Producto
 * 
 * @author Marcelino Álvarez García
 */
@Entity(name = "Producto")
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto")
    @SequenceGenerator(name = "producto", sequenceName = "_hibernate_sequence_productos",allocationSize=1)        
    private Long idProducto;
    @Column(name="nombre") 
    private String nombre;
    @Column(name="descripcion") 
    private String descripcion;
    @Column(name="precio") 
    private float precio;
 
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> tiendas;

    
    /**
     * Constructor por defecto
     */
    public Producto() {
    }

    
    /**
     * Constructor
     * 
     * @param nombre            Nombre del producto
     * @param descripcion       Descripción
     * @param precio            Precio
     */
    public Producto(String nombre,String descripcion,float precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tiendas = new ArrayList<>();
    }

    
    /**
     * Getters
     */

    public Long getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
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

