
package com.mycompany.ad03.entidades;

/**
 * Clase Cliente
 * 
 * @author Marcelino Álvarez García
 */
public class Cliente {
    
    private int idCliente;
    private String nombre;
    private String apellidos;
    private String email;

    /**
     * Constructor
     *
     * @param idCliente     Clave primaria
     * @param nombre        Nombre
     * @param apellidos     Apellidos
     * @param email         Correo electrónico
     */
    public Cliente(int idCliente, String nombre, String apellidos, String email){
        this.idCliente=idCliente;
        this.nombre=nombre;
        this.apellidos=apellidos;        
        this.email=email;        
    }  

    /*
     *  Getters 
     */
    
    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
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
