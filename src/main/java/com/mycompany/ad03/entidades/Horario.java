
package com.mycompany.ad03.entidades;

/**
 * Clase Horario
 * 
 * @author Marcelino Álvarez García
 */
public class Horario {
    
    private int idHorario;
    private int idEmpleado;
    private int idTienda;
    private int horasSemanales;

    /**
     * Constructor
     * 
     * @param idHorario         Clave primaria
     * @param idEmpleado        Identificador del cliente
     * @param idTienda          Identificador de la tienda
     * @param horasSemanales    Número de horas semanales
     */
    public Horario(int idHorario, int idEmpleado, int idTienda, int horasSemanales) {
        this.idHorario = idHorario;
        this.idEmpleado = idEmpleado;
        this.idTienda = idTienda;
        this.horasSemanales = horasSemanales;
    }

    /*
     *  Getters 
     */
    
    public int getIdHorario() {
        return idHorario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

}
