
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
 * Clase Empleado
 * 
 * @author Marcelino Álvarez García
 */
@Entity(name = "Empleado")
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empleado")
    @SequenceGenerator(name = "empleado", sequenceName = "_hibernate_sequence_empleados",allocationSize=1)        
    private Long idEmpleado;
    @Column(name="nombre") 
    private String nombre;
    @Column(name="apellidos") 
    private String apellidos;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Horario> tiendas;

    
    /**
     * Constructor por defecto
     */
    public Empleado() {
    }

    
    /**
     * Constructor
     * 
     * @param nombre        Nombre del empleado
     * @param apellidos     Apellidos del empleado
     */
    public Empleado(String nombre,String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tiendas = new ArrayList<>();
    }

    
    /**
     * Getters
     */

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    
    /**
     * Devuelve formateados los datos del empleado
     * 
     * @return          Datos del empleado
     */
    public String toString(){
        return(String.format("%-40s  %-40s",nombre,apellidos));          
    }   

}







  
