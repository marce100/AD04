
package com.mycompany.ad04.parser;

/**
 * Clase Noticia
 * 
 * @author Marcelino Álvarez García
 */
public class Noticia {

    private String titulo;

    /*
     * Constructor por defecto
     */
    public Noticia(){
        this.titulo=new String("");
    }

    
    /*
     * Constructor 
     *
     * @param   titulo  Título de la noticia
     */
    public Noticia(String titulo){
        this.titulo=titulo;        
    }

    
    /*
     * Devuelve el título de una noticia
     *
     * @return  Título de la noticia
     */      
    public String getTitulo(){
        return titulo;
    }

    
    /*
     * Guarda el título de una noticia
     *
     * @param   titulo  Título de la noticia
     */      
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }    

}
