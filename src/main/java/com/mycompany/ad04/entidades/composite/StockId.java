
package com.mycompany.ad04.entidades.composite;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase StockId
 * 
 * @author Marcelino Álvarez García
 */
@Embeddable
public class StockId implements Serializable {

    @Column(name = "idTienda")
    private Long idTienda;
    @Column(name = "idProducto")
    private Long idProducto;

    
    /**
     * Constructor por defecto
     */
    private StockId() {}

    
    /**
     * Constructor
     * 
     * @param idTienda          Clave primaria de la tienda
     * @param idProducto        Clave primaria del producto
     */
    public StockId(Long idTienda, Long idProducto) {
        this.idTienda = idTienda;
        this.idProducto = idProducto;
    }
    
    
    /**
     * Getters 
     */
     
    public Long getIdTienda() {
        return idTienda;
    }

    public Long getIdProducto() {
        return idProducto;
    }

}    
    
    
