
package com.mycompany.ad04;

import com.mycompany.ad04.bd.BD;
import com.mycompany.ad04.entidades.Cliente;
import com.mycompany.ad04.entidades.Empleado;
import com.mycompany.ad04.entidades.Producto;
import com.mycompany.ad04.entidades.Horario;
import com.mycompany.ad04.entidades.Tienda;
import com.mycompany.ad04.entidades.Stock;
import java.util.List;       
import org.hibernate.Session;


/**
 * Clase Franquicia
 * 
 * @author Marcelino Álvarez García
 */
public class Franquicia {
    
    public Session session;
    
    
    /**
     * Constructor
     * 
     * @param session       Sesión con la base de datos
     */
    public Franquicia(Session session){            
        this.session=session;
    }
    
    
    /**
     * Crea una tienda
     * 
     * @param tienda        Objeto de la clase tienda
     */
    public void addTienda(Tienda tienda){
        BD.insertTienda(session, tienda);
    }
  
    
    /**
     * Crea un cliente
     * 
     * @param cliente       Objeto de la clase cliente
     */
    public void addCliente(Cliente cliente){
         BD.insertCliente(session, cliente);
    }  

    
    /**
     * Crea un producto
     * 
     * @param producto      Objeto de la clase Producto
     */
    public void addProducto(Producto producto){
        BD.insertProducto(session, producto);
    }    
    
    
    /**
     * Crea un empleado
     * 
     * @param empleado      Objeto de la clase Empleado
     */
    public void addEmpleado(Empleado empleado){
        BD.insertEmpleado(session, empleado);
    }
    
    
    /**
     * Añade un empleado a una tienda
     * 
     * @param horario       Objeto de la clase Horario
     */
    public void addHorario(Horario horario){
        BD.insertHorario(session, horario);
    }   
    
    
    /**
     * Añade stock a una tienda
     * 
     * @param stock         Objeto de la clase Stock
     */
    public void addStock(Stock stock){
        BD.insertStock(session, stock);
    }     
    
    
    /**
     * Actualiza el stock de una tienda
     * 
     * @param stock         Objeto de la clase Stock
     */
    public void updateStock(Stock stock){
        BD.updateStock(session, stock);
    }    
    
    
    /**
     * Actualiza el horario de un empleado en una tienda
     * 
     * @param horario       Objeto de la clase Horario
     */
    public void updateHorario(Horario horario) {
        BD.updateHorario(session, horario);
    }    
    
    
    /**
     * Comprueba si existe Stock de un producto en una tienda
     * 
     * @param idProducto    Identificador del producto
     * @param idTienda      Identificador de la tienda
     * @return              Si existe o no
     */
    public boolean existeStock(long idProducto, long idTienda){
        return BD.existeStock(session, idProducto, idTienda); 
    }    
     
    
    /**
     * Comprueba si existe una tienda
     * 
     * @param idTienda      Identificador de la tienda
     * @return              Si existe o no
     */
    public boolean existeTienda(long idTienda){
        return BD.existeTienda(session, idTienda);        
    }   
    
    
    /**
     * Comprueba si existe un producto
     * 
     * @param idProducto    Identificador del producto
     * @return              Si existe o no
     */
    public boolean existeProducto(long idProducto){
        return BD.existeProducto(session, idProducto); 
    }
    
    
    /**
     * Comprueba si existe un cliente
     * 
     * @param idCliente    Identificador del cliente
     * @return             Si existe o no
     */
    public boolean existeCliente(long idCliente){
        return BD.existeCliente(session, idCliente);       
    }
    
    
    /**
     * Comprueba si exite un empleado
     * 
     * @param idEmpleado    Identificador del empleado
     * @return              Si existe o no
     */
    public boolean existeEmpleado(long idEmpleado){
        return BD.existeEmpleado(session, idEmpleado);  
    }  

    
    /**
     * Comprueba si existe un empleado en una tienda
     * 
     * @param idTienda      Identificador de la tienda
     * @param idEmpleado    Identificador del producto
     * @return              Si existe o no
     */
    public boolean existeEmpleadoTienda(long idTienda,long idEmpleado){
        return BD.existeEmpleadoTienda(session, idTienda, idEmpleado);  
    }
    
    
    /**
     * Elimina una tienda
     * 
     * @param idTienda      Identificador de la tienda
     */
    public void delTienda(long idTienda){
        BD.deleteTienda(session, idTienda);
    }
    
    
    /**
     * Elimina un cliente
     * 
     * @param idCliente     Identificador del cliente
     */
    public void delCliente(long idCliente){
        BD.deleteCliente(session, idCliente);
    } 
    
    
    /**
     * Elimina un empleado
     * 
     * @param idEmpleado    Identificador del empleado
     */
    public void delEmpleado(long idEmpleado){
        BD.deleteEmpleado(session, idEmpleado);
    }
    
    
    /**
     * Elimina un producto
     * 
     * @param idProducto    Identificador del producto
     */
    public void delProducto(long idProducto){
        BD.deleteProducto(session, idProducto);
    }    
    
    
    /**
     * Elimina un producto de una tienda
     *
     * @param stock         Objeto de la clase Stock
     */
    public void delProductoTienda(Stock stock){
        BD.deleteProductoTienda(session, stock);
    }

    
    /**
     * Elimina un empleado de una tienda
     * 
     * @param horario       Objeto de la clase Horario
     */
    public void delEmpleadoTienda(Horario horario) {
        BD.deleteEmpleadoTienda(session, horario);
    }
        
    
    /**
     * Devuelve el total de productos de una tienda
     * 
     * @param idTienda      Identificador de la tienda
     * @return              Total
     */  
    public long getTotalProductos(long idTienda){  
        List<Producto> productos= BD.selectProductos(session,idTienda);    
        return productos.size();
    }

    
    /**
     * Devuelve el total de empleados de una tienda
     * 
     * @param idTienda      Identificador de la tienda
     * @return              Total
     */
    public int getTotalEmpleados(long idTienda) {
        List<Empleado> empleados= BD.selectEmpleados(session,idTienda);    
        return empleados.size();
    }     

    
    /**
     * Devuelve el total de tiendas
     * 
     * @return              Total
     */
    public int getTotalTiendas() {
        List<Tienda> tiendas= BD.selectTiendas(session);    
        return tiendas.size();
    }     

    
    /**
     * Devuelve el total de productos
     * 
     * @return              Total
     */
    public long getTotalProductos() {
        List<Producto> productos= BD.selectProductos(session);    
        return productos.size();
    }     

    
    /**
     * Devuelve el total de empleados
     * 
     * @return              Total
     */
    public long getTotalEmpleados() {
        List<Empleado> empleados= BD.selectEmpleados(session);    
        return empleados.size();
    }      

    
    /**
     * Devuelve el total de clientes
     * 
     * @return              Total
     */
    public long getTotalClientes() {
        List<Cliente> clientes= BD.selectClientes(session);    
        return clientes.size();
    }     
    
    
    /**
     * Muesta por pantalla el listado de tiendas
     */
    public void getTiendas(){  
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("LISTADO DE TIENDAS");          
        System.out.println("------------------------------------------------------------------------------");             
        for (Tienda t : BD.selectTiendas(session)){           
            System.out.printf("%-5s "+t+"\n",t.getIdTienda());      
        }
    }  
     
    
    /**
     * Muesta por pantalla el listado de productos
     */    
    public void getProductos(){  
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("LISTADO DE PRODUCTOS");          
        System.out.println("------------------------------------------------------------------------------");             
        for (Producto p : BD.selectProductos(session)){           
            System.out.printf("%-5s "+p+"\n",p.getIdProducto());      
        }
    }  
    
    
    /**
     * Muesta por pantalla el listado de productos de una tienda
     * 
     * @param idTienda      Identificador de la tienda
     */
    public void getProductos(long idTienda){  
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("LISTADO DE PRODUCTOS");          
        System.out.println("------------------------------------------------------------------------------");             
        for (Producto p : BD.selectProductos(session,idTienda)){           
            System.out.printf("%-5s "+p+"\n",p.getIdProducto());      
        }
    }    
    
    
    /**
     * Muesta por pantalla el listado de clientes
     */
    public void getClientes(){  
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("LISTADO DE CLIENTES");          
        System.out.println("------------------------------------------------------------------------------");             
        for (Cliente c : BD.selectClientes(session)){           
            System.out.printf("%-5s "+c+"\n",c.getIdCliente());      
        }
    }  
    
    
    /**
     * Muesta por pantalla el listado de empleados
     */
    public void getEmpleados(){  
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("LISTADO DE EMPLEADOS");          
        System.out.println("------------------------------------------------------------------------------");             
        for (Empleado e : BD.selectEmpleados(session)){           
            System.out.printf("%-5s "+e+"\n",e.getIdEmpleado());      
        }
    }  
  
    
    /**
     * Muesta por pantalla el listado de empleados de una tienda
     * 
     * @param idTienda      Identificador de la tienda
     */
    public void getEmpleados(long idTienda) {
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("LISTADO DE EMPLEADOS");          
        System.out.println("------------------------------------------------------------------------------");             
        for (Empleado e : BD.selectEmpleados(session,idTienda)){           
            System.out.printf("%-5s "+e+"\n",e.getIdEmpleado());      
        }
    }        
    
    
    /**
     * Muesta por pantalla un listado con el total de existencias de un producto de una tienda
     * 
     * @param stock      Objeto de la clase Stock
     */    
    public void getStock(Stock stock){  
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("STOCK");          
        System.out.println("------------------------------------------------------------------------------");             
        Stock s = BD.selectStock(session,stock);
        System.out.printf("%-55s  STOCK: %-25s \n",s.getProducto().getNombre(),s.getCantidad());       
    }  
    
    
    /**
     * Muesta por pantalla un listado con el total de horas de un empleado de una tienda
     * 
     * @param horario      Objeto de la clase Horario
     */       
    public void getHorario(Horario horario) {
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("HORARIO");          
        System.out.println("------------------------------------------------------------------------------");             
        Horario h = BD.selectHorario(session,horario);
        System.out.printf("%-55s  HORAS SEMANALES: %-25s \n",h.getEmpleado().getNombre(),h.getHorasSemanales()); 
    }    
  
}
