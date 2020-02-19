
package com.mycompany.ad04.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase Cliente
 * 
 * @author Marcelino Álvarez García
 */
@Entity(name="Cliente")
@Table(name="clientes")
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente")
    @SequenceGenerator(name = "cliente", sequenceName = "_hibernate_sequence_clientes",allocationSize=1)   
    @Column(name="idCliente")    
    private long idCliente;
    @Column(name="nombre")        
    private String nombre;
    @Column(name="apellidos")        
    private String apellidos;
    @Column(name="email")        
    private String email;

    
    /**
     * Constructor por defecto
     */
    public Cliente() {
    }
       
    
    /**
     * Constructor
     *
     * @param nombre        Nombre
     * @param apellidos     Apellidos
     * @param email         Correo electrónico
     */
    public Cliente(String nombre, String apellidos, String email){
        this.nombre=nombre;
        this.apellidos=apellidos;        
        this.email=email;        
    }  

    
    /**
     * Getters
     */
    
    public long getIdCliente() {
        return idCliente;
    }

    
    /**
     * Devuelve formateados los datos del cliente
     * 
     * @return          Datos del cliente
     */
    public String toString(){
        return(String.format("%-20s  %-30s  %-20s",nombre,apellidos,email));  
    }    
    
}
