
package com.mycompany.ad04.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase Tienda
 * 
 * @author Marcelino Álvarez García
 */
@Entity(name = "Tienda")
@Table(name = "tiendas")
public class Tienda {

    @Id    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tienda")
    @SequenceGenerator(name = "tienda", sequenceName = "_hibernate_sequence_tiendas",allocationSize=1)
    @Column(name="idTienda")  
    private Long idTienda;
    @Column(name="nombre")  
    private String nombre;
    @Column(name="ciudad")  
    private String ciudad;

    @OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> productos;

    @OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Horario> empleados;
    
    @ManyToOne
    @JoinColumn(name="idProvincia")        
    private Provincia provincia;    

    
    /**
     * Constructor por defecto
     */
    public Tienda() {
    }

    
    /**
     * Constructor
     * 
     * @param nombre            Nombre de la tienda
     * @param ciudad            Ciudad
     */
    public Tienda(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.productos = new ArrayList<>();
        this.empleados = new ArrayList<>();   
        this.provincia = new Provincia();                     
    }

    
    /**
     * Getters y setters
     */
     
    public Long getIdTienda() {
        return idTienda;
    }

    public String getNombre() {
        return nombre;
    }
    
    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }  
    
    
    /**
     * Devuelve formateados los datos de la tienda
     * 
     * @return          Datos de la tienda
     */      
    public String toString(){                
        return(String.format("%-24s %-24s %-24s", nombre, ciudad, provincia));        
    }

}