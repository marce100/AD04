
package com.mycompany.ad04.entidades;

import com.mycompany.ad04.entidades.composite.HorarioId;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Clase Horario
 * 
 * @author Marcelino Álvarez García
 */
@Entity(name = "Horario")
@Table(name = "horarios")  
public class Horario {

    @EmbeddedId
    private HorarioId id;
    @Column(name = "horasSemanales")
    private int horasSemanales;
     
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTienda")
    @JoinColumn(name = "idTienda")
    private Tienda tienda;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEmpleado")
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado;


    /**
     * Constructor por defecto
     */
    private Horario() {}

    
    /**
     * Constructor
     * 
     * @param tienda            Tienda
     * @param empleado          Empleado
     */
    public Horario(Tienda tienda, Empleado empleado) {
        this.tienda = tienda;
        this.empleado = empleado;
        this.id = new HorarioId(tienda.getIdTienda(), empleado.getIdEmpleado());
    }

    
    /**
     * Getters y setters
     */
                 
    public HorarioId getId() {
        return id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }
        
    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

}
