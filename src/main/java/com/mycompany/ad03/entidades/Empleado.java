
package com.mycompany.ad03.entidades;

/**
 * Clase Empleado
 * 
 * @author Marcelino Álvarez García
 */
public class Empleado {
    
    private int idEmpleado;
    private String nombre;
    private String apellidos;


    /**
     * Constructor
     *
     * @param idEmpleado    Clave primaria
     * @param nombre        Nombre
     * @param apellidos     Apellidos
     */
    public Empleado(int idEmpleado, String nombre, String apellidos) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    /*
     *  Getters 
     */
    
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getIdEmpleado() {
        return idEmpleado;
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