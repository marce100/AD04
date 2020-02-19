
package com.mycompany.ad04.entidades.composite;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase HorarioId
 * 
 * @author Marcelino Álvarez García
 */
@Embeddable
public class HorarioId implements Serializable {

    @Column(name = "idTienda")
    private Long idTienda;
    @Column(name = "idEmpleado")
    private Long idEmpleado;
    
    
    /**
     * Constructor por defecto
     */
    private HorarioId() {}

    
    /**
     * Constructor
     * 
     * @param idTienda          Clave primaria de la tienda
     * @param idEmpleado        Clave primaria del empleado
     */
    public HorarioId(Long idTienda, Long idEmpleado) {
        this.idTienda = idTienda;
        this.idEmpleado = idEmpleado;
    }

    
    /**
     * Getters 
     */
         
    public Long getIdTienda() {
        return idTienda;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

 }    
    
    
    