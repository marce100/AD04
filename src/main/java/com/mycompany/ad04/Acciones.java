
package com.mycompany.ad04;

import com.mycompany.ad04.entidades.Cliente;
import com.mycompany.ad04.entidades.Empleado;
import com.mycompany.ad04.entidades.Producto;
import com.mycompany.ad04.entidades.Horario;
import com.mycompany.ad04.entidades.Tienda;
import com.mycompany.ad04.menu.Menu;
import com.mycompany.ad04.bd.BD;
import com.mycompany.ad04.disco.Archivo;
import com.mycompany.ad04.entidades.Stock;
import com.mycompany.ad04.entidades.informes.Informe;
import com.mycompany.ad04.parser.Titulares;


/**
 * Clase Acciones
 * 
 * @author Marcelino Álvarez García
 */
public class Acciones {
        
    private Franquicia franquicia;
    
    
    /**
     * Constructor
     *
     * @param franquicia    Instancia de la clase Franquicia    
     */
    public Acciones(Franquicia franquicia) {
        this.franquicia = franquicia;
    }

    
    /**
     * 1. Añade una tienda
     */
    public void insertarTiendaFranquicia() {
        Tienda tienda = Menu.getFormCrearTienda(franquicia);
        franquicia.addTienda(tienda);
    }

    
    /**
     * 2. Añade un producto
     */
    public void insertarProductoFranquicia(){
        Producto producto = Menu.getFormCrearProducto();
        franquicia.addProducto(producto);
    }   

    
    /**
     * 3. Añade un producto a una tienda
     */
    public void insertarProductoTienda(){                
        Stock stock = Menu.getFormInsertarStock(franquicia);     
        if (stock!=null)
            franquicia.addStock(stock);
    }    
 
    
    /**
     * 4. Añade un cliente
     */
    public void insertarClienteFranquicia(){
        Cliente cliente = Menu.getFormCrearCliente();
        franquicia.addCliente(cliente);          
    }         

    
    /**
     * 5. Añade un empleado
     */
    public void insertarEmpleadoFranquicia() {
        Empleado empleado = Menu.getFormCrearEmpleado();
        franquicia.addEmpleado(empleado);
    }

    
    /**
     * 6. Añade un empleado a una tienda
     */
    public void insertarEmpleadoTienda() {
        Horario horario = Menu.getFormInsertarHorario(franquicia);     
        if (horario!=null)
            franquicia.addHorario(horario);
    }    

    
    /**
     * 7. Mostrar las tiendas
     */
    public void mostrarTiendasFranquicia(){
        franquicia.getTiendas();
    }

    
    /**
     * 8. Mostrar los productos de la franquicia
     */
    public void mostrarProductosFranquicia(){
        franquicia.getProductos();        
    }    
    
    
    /**
     * 9. Mostrar los productos de una tienda
     */
    public void mostrarProductosTienda(){
        long idTienda = Menu.getFormMostrarProductosTienda(franquicia);
        if (idTienda!=0)
            franquicia.getProductos(idTienda);              
    }    
 

    /**
     * 10. Mostrar el stock de un producto de una tienda
     */
    public void mostrarStockTienda(){
        Stock stock = Menu.getFormMostrarStockTienda(franquicia);        
        if (stock!=null)        
            franquicia.getStock(stock);
    }    
 
    
    /**
     * 11. Mostrar los clientes
     */
    public void mostrarClientesFranquicia(){
        franquicia.getClientes();                  
    }  

    
    /**
     * 12. Mostrar los empleados
     */
    public void mostrarEmpleadosFranquicia(){
        franquicia.getEmpleados();                  
    }    
    
    
    /**
     * 13. Mostrar los empleados de una tienda 
     */
    public void mostrarEmpleadosTienda() {
        long idTienda = Menu.getFormMostrarEmpleadosTienda(franquicia);
        if (idTienda!=0)
            franquicia.getEmpleados(idTienda);  
    }

    
    /**
     * 14. Mostrar las horas de un empleado de una tienda
     */
    void mostrarHorarioTienda() {
        Horario horario = Menu.getFormMostrarHorarioTienda(franquicia);        
        if (horario!=null)        
            franquicia.getHorario(horario);
    }    
         
    
    /**
     * 15. Actualizar el stock de un producto de una tienda
     */
    public void updateProductoTienda(){  
        Stock stock = Menu.getFormUpdateStock(franquicia);  
        if (stock!=null)
            franquicia.updateStock(stock);                                                                                        
    }    
 

    /**
     * 16. Actualizar las horas de un empleado de una tienda
     */
    public void updateEmpleadoTienda() {
        Horario horario = Menu.getFormUpdateHorario(franquicia);  
        if (horario!=null)
            franquicia.updateHorario(horario);         
    }


    /**
     * 17. Elimina una tienda
     */
    public void eliminarTiendaFranquicia(){
        long idTienda = Menu.getFormEliminarTienda(franquicia);
        if (idTienda!=0)
            franquicia.delTienda(idTienda);
    }
  
    
    /**
     * 18. Elimina un producto
     */
    public void eliminarProductoFranquicia(){
        long idProducto = Menu.getFormEliminarProducto(franquicia);
        if (idProducto!=0)
            franquicia.delProducto(idProducto);      
    }        
 

    /**
     * 19. Elimina un producto de una tienda
     */
    public void eliminarProductoTienda(){
        Stock stock = Menu.getFormEliminarProductoTienda(franquicia);        
        if (stock!=null)        
            franquicia.delProductoTienda(stock);        
    }        
 

    /**
     * 20. Elimina un cliente
     */
    public void eliminarClienteFranquicia(){
        long idCliente = Menu.getFormEliminarCliente(franquicia);
        if (idCliente!=0)
            franquicia.delCliente(idCliente);
    }   


    /**
     * 21. Elimina un empleado
     */
    public void eliminarEmpleadosFranquicia() {
        long idEmpleado = Menu.getFormEliminarEmpleado(franquicia);
        if (idEmpleado!=0)
            franquicia.delEmpleado(idEmpleado); 
    }       
    

    /**
     * 22. Elimina un empleado de una tienda
     */
    public void eliminarEmpleadosTienda() {
        Horario horario = Menu.getFormEliminarEmpleadoTienda(franquicia);        
        if (horario!=null)        
            franquicia.delEmpleadoTienda(horario); 
    }

    
    /**
     * 23. Leer los titulares del periódico El País
     */
    public void mostrarTitulares(){        
        int opcion=Menu.getFormMostrarTitulares();                                  
        new Titulares().mostrarTitulares(opcion);        
    }  
     
    
    /**
     * 24.Generar informe
     * @param nombreFichero     Nombre con el que se guardará el informe
     */
    public void generarInforme(String nombreFichero){          
        Informe informe = BD.getInforme(franquicia.session);     
        new Archivo().guardarArchivo(nombreFichero,informe);
    }
    
    
    /**
     * 25. Salir del programa
     */    
    public void salir(){
        //Cerrar base de datos
        BD.disconnectDatabase(franquicia.session);
        //Sale del programa
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Adios.");    
    }

    
}
