
package com.mycompany.ad03;

import com.mycompany.ad03.entidades.Cliente;
import com.mycompany.ad03.entidades.Empleado;
import com.mycompany.ad03.entidades.Producto;
import com.mycompany.ad03.entidades.Horario;
import com.mycompany.ad03.entidades.Tienda;
import com.mycompany.ad03.entidades.Stock;
import com.mycompany.ad03.bd.BD;
import java.sql.Connection;
import java.util.List;       


/**
 * Clase Franquicia
 * 
 * @author Marcelino Álvarez García
 */
public class Franquicia {
    
    Connection con;
    
    /**
     * Constructor
     *
     * @param con           Sesión con la base de datos
     */
    public Franquicia(Connection con){            
        this.con=con;
    }
    
    /**
     * Crea una tienda
     * 
     * @param tienda
     */
    public void addTienda(Tienda tienda){
        BD.insertTienda(con, tienda);
    }
  
    /**
     * Crea un cliente
     * 
     * @param cliente       Objeto de la clase cliente
     */
    public void addCliente(Cliente cliente){
         BD.insertCliente(con, cliente);
    }  

    /**
     * Crea un producto
     * 
     * @param producto      Objeto de la clase Producto
     */
    public void addProducto(Producto producto){
        BD.insertProducto(con, producto);
    }    
    
    /**
     * Crea un empleado
     * 
     * @param empleado      Objeto de la clase Empleado
     */
    public void addEmpleado(Empleado empleado){
        BD.insertEmpleado(con, empleado);
    }
    
    /**
     * Añade un empleado a una tienda
     * 
     * @param horario       Objeto de la clase Horario
     */
    public void addHorario(Horario horario){
        BD.insertHorario(con, horario);
    }   
    
    /**
     * Añade stock a una tienda
     * 
     * @param stock         Objeto de la clase Stock
     */
    public void addStock(Stock stock){
        BD.insertStock(con, stock);
    }     
    
    /**
     * Actualiza el stock de una tienda
     * 
     * @param stock         Objeto de la clase Tienda
     */
    public void updateStock(Stock stock){
        BD.updateStock(con, stock);
    }    
    
    /**
     * Actualiza el horario de un empleado en una tienda
     * 
     * @param horario       Objeto de la clase Horario
     */
    public void updateHorario(Horario horario) {
        BD.updateHorario(con, horario);
    }    
    
    /**
     * Comprueba si existe Stock de un producto en una tienda
     * 
     * @param idProducto    Identificador del producto
     * @param idTienda      Identificador de la tienda
     * @return              Si existe o no
     */
    public boolean existeStock(int idProducto, int idTienda){
        return BD.existeStock(con, idProducto, idTienda); 
    }    
     
    /**
     * Comprueba si existe una tienda
     * 
     * @param idTienda      Identificador de la tienda
     * @return              Si existe o no
     */
    public boolean existeTienda(int idTienda){
        return BD.existeTienda(con, idTienda);        
    }   
    
    /**
     * Comprueba si existe un producto
     * 
     * @param idProducto    Identificador del producto
     * @return              Si existe o no
     */
    public boolean existeProducto(int idProducto){
        return BD.existeProducto(con, idProducto); 
    }
    
    /**
     * Comprueba si existe un cliente
     * 
     * @param idCliente    Identificador del cliente
     * @return              Si existe o no
     */
    public boolean existeCliente(int idCliente){
        return BD.existeCliente(con, idCliente);       
    }
    
    /**
     * Comprueba si exite un empleado
     * 
     * @param idEmpleado    Identificador del empleado
     * @return              Si existe o no
     */
    public boolean existeEmpleado(int idEmpleado){
        return BD.existeEmpleado(con, idEmpleado);  
    }  

    /**
     * Comprueba si existe un empleado en una tienda
     * 
     * @param idTienda      Identificador de la tienda
     * @param idEmpleado    Identificador del producto
     * @return              Si existe o no
     */
    public boolean existeEmpleadoTienda(int idTienda,int idEmpleado){
        return BD.existeEmpleadoTienda(con, idTienda, idEmpleado);  
    }
    
    /**
     * Elimina una tienda
     * 
     * @param idTienda      Identificador de la tienda
     */
    public void delTienda(int idTienda){
        BD.deleteTienda(con, idTienda);
    }
    
    /**
     * Elimina un cliente
     * 
     * @param idCliente     Identificador del cliente
     */
    public void delCliente(int idCliente){
        BD.deleteCliente(con, idCliente);
    } 
    
    /**
     * Elimina un empleado
     * 
     * @param idEmpleado    Identificador del empleado
     */
    public void delEmpleado(int idEmpleado){
        BD.deleteEmpleado(con, idEmpleado);
    }
    
    /**
     * Elimina un producto
     * 
     * @param idProducto    Identificador del producto
     */
    public void delProducto(int idProducto){
        BD.deleteProducto(con, idProducto);
    }    
    
    /**
     * Elimina un producto de una tienda
     * 
     * @param idProducto    Identificador del cliente
     * @param idTienda      Identificador de la tienda
     */
    public void delProductoTienda(int idProducto,int idTienda){
        BD.deleteProductoTienda(con, idProducto, idTienda);
    }

    /**
     * Elimina un empleado de una tienda
     * 
     * @param idEmpleado    Identificador del empleado
     * @param idTienda      Identificador de la tienda
     */
    public void delEmpleadoTienda(int idEmpleado, int idTienda) {
        BD.deleteEmpleadoTienda(con, idEmpleado, idTienda);
    }
         
    /**
     * Devuelve el total de productos de una tienda
     * 
     * @param idTienda      Identificador de la tienda
     * @return              Total
     */  
    public int getTotalProductos(int idTienda){  
        List<Producto> productos= BD.selectProductos(con,idTienda);    
        return productos.size();
    }

    /**
     * Devuelve el total de empleados de una tienda
     * 
     * @param idTienda      Identificador de la tienda
     * @return              Total
     */
    public int getTotalEmpleados(int idTienda) {
        List<Empleado> empleados= BD.selectEmpleados(con,idTienda);    
        return empleados.size();
    }     

    /**
     * Devuelve el total de tiendas
     * 
     * @return              Total
     */
    public int getTotalTiendas() {
        List<Tienda> tiendas= BD.selectTiendas(con);    
        return tiendas.size();
    }     

    /**
     * Devuelve el total de productos
     * 
     * @return              Total
     */
    public int getTotalProductos() {
        List<Producto> productos= BD.selectProductos(con);    
        return productos.size();
    }     

    /**
     * Devuelve el total de empleados
     * 
     * @return              Total
     */
    public int getTotalEmpleados() {
        List<Empleado> empleados= BD.selectEmpleados(con);    
        return empleados.size();
    }      

    /**
     * Devuelve el total de clientes
     * 
     * @return              Total
     */
    public int getTotalClientes() {
        List<Cliente> clientes= BD.selectClientes(con);    
        return clientes.size();
    }     
    
    /**
     * Muesta por pantalla el listado de tiendas
     */
    public void getTiendas(){  
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("LISTADO DE TIENDAS");          
        System.out.println("------------------------------------------------------------------------------");             
        for (Tienda t : BD.selectTiendas(con)){           
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
        for (Producto p : BD.selectProductos(con)){           
            System.out.printf("%-5s "+p+"\n",p.getIdProducto());      
        }
    }  
    
    /**
     * Muesta por pantalla el listado de productos de una tienda
     * 
     * @param idTienda      Identificador de la tienda
     */
    public void getProductos(int idTienda){  
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("LISTADO DE PRODUCTOS");          
        System.out.println("------------------------------------------------------------------------------");             
        for (Producto p : BD.selectProductos(con,idTienda)){           
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
        for (Cliente c : BD.selectClientes(con)){           
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
        for (Empleado e : BD.selectEmpleados(con)){           
            System.out.printf("%-5s "+e+"\n",e.getIdEmpleado());      
        }
    }  
  
    /**
     * Muesta por pantalla el listado de empleados de una tienda
     * 
     * @param idTienda      Identificador de la tienda
     */
    public void getEmpleados(int idTienda) {
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("LISTADO DE EMPLEADOS");          
        System.out.println("------------------------------------------------------------------------------");             
        for (Empleado e : BD.selectEmpleados(con,idTienda)){           
            System.out.printf("%-5s "+e+"\n",e.getIdEmpleado());      
        }
    }        
    
    /**
     * Muesta por pantalla un listado con el total de existencias de los productos de una tienda
     * 
     * @param stock      Objeto de la clase Stock
     */    
    public void getStock(Stock stock){  
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("STOCK");          
        System.out.println("------------------------------------------------------------------------------");             
        BD.selectStock(con,stock);
        
    }  
    
    /**
     * Muesta por pantalla un listado con el total de horas de empleados de una tienda
     * 
     * @param horario      Objeto de la clase Horario
     */       
    void getHorario(Horario horario) {
        System.out.println("------------------------------------------------------------------------------");  
        System.out.println("HORARIO");          
        System.out.println("------------------------------------------------------------------------------");             
        BD.selectHorario(con,horario);
    }    
  
}
