
package com.mycompany.ad03.entidades;

/**
 * Clase Provincia
 * 
 * @author Marcelino Álvarez García
 */
public class Provincia{

    private int id;
    private String nome;

    /**
     * Constructor
     * 
     * @param id        Clave primaria
     * @param nome      Nombre de la provincia
     */
    public Provincia(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }    

    /*
     *  Getters 
     */
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}    
