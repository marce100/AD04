
package com.mycompany.ad04.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Clase Provincia
 * 
 * @author Marcelino Álvarez García
 */
@Entity(name="Provincia")
@Table(name="provincias")
public class Provincia implements Serializable{

    @Id
    @Column(name="idProvincia")
    private int id;
    @Column(name="nombre")    
    private String nome;    

    @OneToMany(mappedBy = "provincia",cascade=CascadeType.ALL)
    private Set<Tienda> tiendas;    

    
    /**
     * Constructor por defecto
     */
    public Provincia() {
    }

    
    /**
     * Constructor
     * 
     * @param id            Identificador de la provincia
     * @param nome          Nombre de la provincia
     */
    public Provincia(int id, String nome) {
        this.id = id;
        this.nome = nome;        
        this.tiendas = new HashSet<>();        
    }
    
    
    /**
     * Getters
     */

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    
    /**
     * Devuelve formateados los datos de la provincia
     * 
     * @return          Datos de la provincia
     */  
    public String toString(){                
        return(getNome());        
    }    
    
    
}


