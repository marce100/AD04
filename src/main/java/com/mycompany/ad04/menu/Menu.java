
package com.mycompany.ad04.menu;

import com.mycompany.ad04.Franquicia;
import com.mycompany.ad04.bd.BD;
import com.mycompany.ad04.entidades.Cliente;
import com.mycompany.ad04.entidades.Empleado;
import com.mycompany.ad04.entidades.Horario;
import com.mycompany.ad04.entidades.Pais;
import com.mycompany.ad04.entidades.Producto;
import com.mycompany.ad04.entidades.Provincia;
import com.mycompany.ad04.entidades.Tienda;
import com.mycompany.ad04.entidades.Stock;
import java.util.Scanner;       
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.Session;

/**
 * Clase Menu
 * 
 * @author Marcelino Álvarez García
 */
public class Menu {
    
    /**
     * Muestra el menú por pantalla
     */
    public static void getMenu(){
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "MENU"+"\n"+
            "------------------------------------------------------------------------------"+"\n" +
            " 1. Añade una tienda"+"\n" +                    
            " 2. Añade un producto"+"\n" +                   
            " 3. Añade un producto a una tienda"+"\n" +                    
            " 4. Añade un cliente"+"\n" +                                                                           
            " 5. Añade un empleado"+"\n" +
            " 6. Añade un empleado a una tienda"+"\n" +                    
            " 7. Mostrar las tiendas"+"\n" +
            " 8. Mostrar los productos de la franquicia"+"\n" +
            " 9. Mostrar los productos de una tienda"+"\n" +                  
            "10. Mostrar el stock de un producto de una tienda"+"\n" + 
            "11. Mostrar los clientes"+"\n" +  
            "12. Mostrar los empleados"+"\n" +
            "13. Mostrar los empleados de una tienda"+"\n" +                                     
            "14. Mostrar las horas de un empleado de una tienda"+"\n" +                                          
            "15. Actualizar el stock de un producto de una tienda"+"\n" +    
            "16. Actualizar las horas de un empleado de una tienda"+"\n" +                      
            "17. Elimina una tienda"+"\n" +                  
            "18. Elimina un producto"+"\n" +     
            "19. Elimina un producto de una tienda"+"\n" +              
            "20. Elimina un cliente"+"\n" + 
            "21. Elimina un empleado"+"\n" +
            "22. Elimina un empleado de una tienda"+"\n" +                      
            "23. Leer los titulares del periódico El País"+"\n" +
            "24. Generar informe"+"\n" +
            "25. Salir del programa");
    }
    
    
    /**
     * Pide una opción del menú por teclado
     *
     * @return  La opción seleccionada
     */           
    public static long getOpcion(){
        long opcion;
        
        // Creamos un objeto Scanner para la entrada de datos
        Scanner entrada = new Scanner(System.in);
        
        try{
            // Pedimos datos por pantalla
            System.out.println("------------------------------------------------------------------------------");
            System.out.print("Selecciona una opción: ");
            opcion = entrada.nextLong();
        }catch(Exception e){
            opcion=-1;
        }
        
        return opcion;
    } 
    
    
    /**
     * Muestra un formulario crear una tienda nueva
     *
     * @param franquicia    Instancia de la clase Franquicia
     * @return      Objeto de la clase Tienda
     */
    public static Tienda getFormCrearTienda(Franquicia franquicia){        
        String nombre;
        String ciudad; 
        int idProvincia=0;
        
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "CREAR TIENDA"+"\n"+
            "------------------------------------------------------------------------------");    
        nombre = seleccionarString("Nombre: ");
        ciudad = seleccionarString("Ciudad: ");
        idProvincia = seleccionarProvincia(franquicia.session,"Provincia: \n");
           
        //Se crea la tienda
        Provincia provincia = BD.selectProvincia(franquicia.session,idProvincia);  
        Tienda tienda = new Tienda(nombre,ciudad);  
        tienda.setProvincia(provincia);
        return tienda;
    }  
    
            
    /**
     * Muestra un formulario para crear un empleado
     *
     * @return  Objeto de la clase Empleado
     */
    public static Empleado getFormCrearEmpleado(){        
        String nombre;
        String apellidos; 
        
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "CREAR EMPLEADO"+"\n"+
            "------------------------------------------------------------------------------");    
        nombre = seleccionarString("Nombre: ");
        apellidos = seleccionarString("Apellidos: ");
           
        //Se crea el empleado
        Empleado empleado = new Empleado(nombre,apellidos);   
        return empleado;
    }  
    
    
    /**
     * Muestra un formulario para añadir crear un cliente
     *
     * @return Objeto de la clase Cliente
     */    
    public static Cliente getFormCrearCliente(){        
        String nombre;
        String apellidos;
        String email;        
        
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "CREAR CLIENTE"+"\n"+
            "------------------------------------------------------------------------------");    
        nombre = seleccionarString("Nombre: ");
        apellidos = seleccionarString("Apellidos: ");
        email = seleccionarEmail("Email: ");
        
        //Se crea la tienda
        Cliente cliente = new Cliente(nombre,apellidos,email);        
        return cliente;
    }    
    
    
    /**
     * Muestra un formulario para crear un producto
     *
     * @return Objeto de la clase Producto
     */
    public static Producto getFormCrearProducto(){        
        String nombre;
        String descripcion;
        float precio;
        
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "CREAR PRODUCTO"+"\n"+
            "------------------------------------------------------------------------------");    
        nombre = seleccionarString("Nombre: ");        
        descripcion = seleccionarString("Descripción: ");        
        precio = seleccionarFloat("Precio: ");

        //Se crea el producto
        Producto producto = new Producto(nombre,descripcion,precio);        
        return producto;
    }    

    
    /**
     * Muestra un formulario para eliminar una tienda
     *
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Identificador de la tienda a eliminar
     */      
    public static long getFormEliminarTienda(Franquicia franquicia){        
        long idTienda;
                
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "ELIMINAR TIENDA"+"\n"+
            "------------------------------------------------------------------------------");    
        if (franquicia.getTotalTiendas()==0){
            System.out.println("No hay tiendas para eliminar.");
            return 0;
        }  
        
        idTienda = seleccionarTienda(franquicia, "Tienda: \n");
                  
        return idTienda;          
    }   
    
    
    /**
     * Muestra un formulario para seleccionar una tienda
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Identificador de la tienda seleccionada
     */
    public static long getFormMostrarProductosTienda(Franquicia franquicia){        
        long idTienda;
        
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "MOSTRAR PRODUCTOS DE TIENDA"+"\n"+
            "------------------------------------------------------------------------------");    
        if (franquicia.getTotalTiendas()==0){
            System.out.println("No hay ninguna tienda.");
            return 0;
        }  
        
        idTienda = seleccionarTienda(franquicia, "Tienda: \n");

        if (franquicia.getTotalProductos(idTienda)==0){
            System.out.println("La tienda seleccionada no tiene productos.");
            return 0;
        }   

        return idTienda;          
    }  
    
    
    /**
     * Muestra un formulario para eliminar una empleado de una tienda
     *
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Identificador del empleado e eliminar
     */
    public static Horario getFormEliminarEmpleadoTienda(Franquicia franquicia) {
        long idTienda;
        long idEmpleado;
                
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "ELIMINAR EMPLEADO DE TIENDA"+"\n"+
            "------------------------------------------------------------------------------");       
        if (franquicia.getTotalTiendas()==0){
            System.out.println("No hay ninguna tienda.");
            return null;
        }          
        
        idTienda = seleccionarTienda(franquicia, "Tienda: \n");

        if (franquicia.getTotalEmpleados(idTienda)==0){
            System.out.println("La tienda seleccionada no tiene empleados.");
            return null;
        }   

        idEmpleado = seleccionarEmpleado(franquicia, "Empleado: \n");
            
        //Se crea el horario
        Tienda tienda = BD.selectTienda(franquicia.session,idTienda);
        Empleado empleado = BD.selectEmpleado(franquicia.session, idEmpleado);  
        Horario horario = new Horario(tienda, empleado);     
        return horario;
    }        
    
    
    /**
     * Muestra un formulario para seleccionar una tienda
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Identificador de la tienda seleccionada
     */
    public static long getFormMostrarEmpleadosTienda(Franquicia franquicia) {
        long idTienda;
        
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "MOSTRAR EMPLEADOS DE TIENDA"+"\n"+
            "------------------------------------------------------------------------------");            
        idTienda = seleccionarTienda(franquicia, "Tienda: \n");

        if (franquicia.getTotalEmpleados(idTienda)==0){
            System.out.println("La tienda seleccionada no tiene empleados.");
            return 0;
        }   

        return idTienda;  
    }    
    
    
    /**
     * Muestra un formulario para eliminar un producto
     *
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Identificador del producto a eliminar
     */      
    public static long getFormEliminarProducto(Franquicia franquicia){        
        long idProducto;
        
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "ELIMINAR PRODUCTO"+"\n"+
            "------------------------------------------------------------------------------");      
        if (franquicia.getTotalProductos()==0){
            System.out.println("No hay productos para eliminar.");
            return 0;
        }                 
        
        idProducto = seleccionarProducto(franquicia, "Producto: \n");
                  
        return idProducto;          
    }      
    
    
    /**
     * Muestra un formulario para eliminar un cliente
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Identificador del cliente a eliminar
     */
    public static long getFormEliminarCliente(Franquicia franquicia){        
        long idCliente;
        
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "ELIMINAR CLIENTE"+"\n"+
            "------------------------------------------------------------------------------");      
        if (franquicia.getTotalClientes()==0){
            System.out.println("No hay clientes para eliminar.");
            return 0;
        }  
                        
        idCliente = seleccionarCliente(franquicia, "Cliente: \n");

        return idCliente;          
    }    
      
    
    /**
     * Muestra un formulario para eliminar un empleado
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Identificador del empleado a eliminar
     */
    public static long getFormEliminarEmpleado(Franquicia franquicia){        
        long idEmpleado;
        
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "ELIMINAR EMPLEADO"+"\n"+
            "------------------------------------------------------------------------------");          
        if (franquicia.getTotalEmpleados()==0){
            System.out.println("No hay empleados para eliminar.");
            return 0;
        }  
        
        idEmpleado = seleccionarEmpleado(franquicia, "Empleado: \n");

        return idEmpleado;          
    }   
    
    
    /**
     * Muestra un formulario para añadir stock a una tienda
     *
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Objeto de la clase Stock
     */      
    public static Stock getFormInsertarStock(Franquicia franquicia){        
        int cantidad;
        long idTienda;
        long idProducto;
                
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "AÑADIR PRODUCTO A TIENDA"+"\n"+
            "------------------------------------------------------------------------------");            
        if (franquicia.getTotalTiendas()==0){
            System.out.println("No existe ninguna tienda, prueba primero a crear una !!!");
            return null;
        }  
        if (franquicia.getTotalProductos()==0){
            System.out.println("No existe ninguna producto, prueba primero a crear uno !!!");
            return null;
        }         
                
        idTienda = seleccionarTienda(franquicia,"Tienda: \n");
        idProducto = seleccionarProducto(franquicia,"Producto: \n");
        
        if (franquicia.existeStock(idProducto,idTienda)){
            System.out.println("Ese producto ya existe en la tienda, prueba a actualizarlo o borrarlo !!!");
            return null;
        }        
                
        cantidad = seleccionarInt("Cantidad: ");   
        
        //Se crea el stock
        Tienda tienda = BD.selectTienda(franquicia.session,idTienda);
        Producto producto = BD.selectProducto(franquicia.session, idProducto);
        Stock stock = new Stock(tienda, producto);        
        stock.setCantidad(cantidad);
        return stock;
    }       
  
    
    /**
     * Muestra un formulario para añadir un empleado a una tienda
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Objeto de la clase Horario
     */
    public static Horario getFormInsertarHorario(Franquicia franquicia){        
        int horasSemanales;
        long idTienda;
        long idEmpleado;
                
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "AÑADIR EMPLEADO A TIENDA"+"\n"+
            "------------------------------------------------------------------------------");                  
        if (franquicia.getTotalTiendas()==0){
            System.out.println("No existe ninguna tienda, prueba primero a crear una !!!");
            return null;
        }  
        if (franquicia.getTotalEmpleados()==0){
            System.out.println("No existe ninguna empleado, prueba primero a crear uno !!!");
            return null;
        }                           
        
        idTienda = seleccionarTienda(franquicia,"Tienda: \n");
        idEmpleado = seleccionarEmpleado(franquicia, "Empleado: \n");
        
        if (franquicia.existeEmpleadoTienda(idTienda,idEmpleado)){
            System.out.println("Ese empleado ya existe en la tienda, prueba a actualizarlo o borrarlo !!!");
            return null;
        }        
                
        horasSemanales = seleccionarInt("Horas semanales: ");    
    
        //Se crea el horario
        Tienda tienda = BD.selectTienda(franquicia.session,idTienda);
        Empleado empleado = BD.selectEmpleado(franquicia.session, idEmpleado);  
        Horario horario = new Horario(tienda, empleado);   
        horario.setHorasSemanales(horasSemanales);
        return horario;
    }       

    
    /**
     * Muestra un formulario para eliminar un producto de una tienda
     *
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Objeto de la clase Stock
     */      
    public static Stock getFormEliminarProductoTienda(Franquicia franquicia){        
        long idTienda;
        long idProducto;
                
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "ELIMINAR STOCK DE TIENDA"+"\n"+
            "------------------------------------------------------------------------------");                  
        if (franquicia.getTotalTiendas()==0){
            System.out.println("No hay ninguna tienda.");
            return null;
        }          
        
        idTienda = seleccionarTienda(franquicia,"Tienda: \n");
        
        if (franquicia.getTotalProductos(idTienda)==0){
            System.out.println("La tienda seleccionada no tiene productos.");
            return null;
        }          
        
        idProducto = seleccionarProductoTienda(franquicia,idTienda,"Producto: \n");
                    
        //Se crea el stock
        Tienda tienda = BD.selectTienda(franquicia.session,idTienda);
        Producto producto = BD.selectProducto(franquicia.session, idProducto);
        Stock stock = new Stock(tienda, producto);        
        return stock;
    }   

    
    
    public static Stock getFormMostrarStockTienda(Franquicia franquicia){        
        long idTienda;
        long idProducto;
                
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "MOSTRAR STOCK DE TIENDA"+"\n"+
            "------------------------------------------------------------------------------");                  
        if (franquicia.getTotalTiendas()==0){
            System.out.println("No hay ninguna tienda.");
            return null;
        }          
        
        idTienda = seleccionarTienda(franquicia,"Tienda: \n");
        
        if (franquicia.getTotalProductos(idTienda)==0){
            System.out.println("La tienda seleccionada no tiene productos.");
            return null;
        }          

        idProducto = seleccionarProductoTienda(franquicia,idTienda,"Producto: \n");
                    
        //Se crea el stock
        Tienda tienda = BD.selectTienda(franquicia.session, idTienda);
        Producto producto = BD.selectProducto(franquicia.session, idProducto);
        Stock stock = new Stock(tienda,producto);        
        return stock;
    }   

    
    /**
     * Muestra un formulario para seleccionar un empleado de una tienda
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Objeto de la clase Horario
     */
    public static Horario getFormMostrarHorarioTienda(Franquicia franquicia) {
        long idTienda;
        long idEmpleado;
                
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "MOSTRAR HORARIO DE TIENDA"+"\n"+
            "------------------------------------------------------------------------------");          
        idTienda = seleccionarTienda(franquicia,"Tienda: \n");
        
        if (franquicia.getTotalEmpleados(idTienda)==0){
            System.out.println("La tienda seleccionada no tiene empleados.");
            return null;
        }          
        
        idEmpleado = seleccionarEmpleadoTienda(franquicia,idTienda,"Empleado: \n");
                  
        //Se crea el horario
        Tienda tienda = BD.selectTienda(franquicia.session, idTienda);
        Empleado empleado = BD.selectEmpleado(franquicia.session, idEmpleado);        
        Horario horario = new Horario(tienda, empleado);        
        return horario;
    }    
    
    
    /**
     * Muestra un formulario para editar un producto de una tienda
     *
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Objeto de la clase Stock
     */
    public static Stock getFormUpdateStock(Franquicia franquicia){        
        int cantidad;
        long idTienda;
        long idProducto;
        
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "ACTUALIZAR STOCK DE TIENDA"+"\n"+
            "------------------------------------------------------------------------------");            
        idTienda = seleccionarTienda(franquicia, "Tienda: \n");

        if (franquicia.getTotalProductos(idTienda)==0){
            System.out.println("La tienda seleccionada no tiene productos.");
            return null;
        }     

        idProducto = seleccionarProductoTienda(franquicia, idTienda, "Producto: \n");
        cantidad = seleccionarInt("Cantidad: ");

        //Se crea el stock
        Tienda tienda = BD.selectTienda(franquicia.session, idTienda);
        Producto producto = BD.selectProducto(franquicia.session, idProducto);        
        Stock stock = new Stock(tienda, producto);
        stock.setCantidad(cantidad);
        return stock;          
    }
    
    
    /**
     * Mustra un formulario para editar el horario de un empleado
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @return              Objeto de la clase Horario
     */
    public static Horario getFormUpdateHorario(Franquicia franquicia) {
        int horasSemanales;
        long idTienda;
        long idEmpleado;
        
        // Se piden los datos por pantalla
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "ACTUALIZAR HORARIO DE EMPLEADO"+"\n"+
            "------------------------------------------------------------------------------");            
        idTienda = seleccionarTienda(franquicia, "Tienda: \n");

        if (franquicia.getTotalEmpleados(idTienda)==0){
            System.out.println("La tienda seleccionada no tiene empleados.");
            return null;
        }     

        idEmpleado = seleccionarEmpleadoTienda(franquicia, idTienda, "Empleado: \n");
        horasSemanales = seleccionarInt("Horas semanales: ");

        //Se crea el horario
        Tienda tienda = BD.selectTienda(franquicia.session, idTienda);
        Empleado empleado = BD.selectEmpleado(franquicia.session, idEmpleado);        
        Horario horario = new Horario(tienda, empleado); 
        horario.setHorasSemanales(horasSemanales);
        return horario;  
    }    
  
    /*
     * Muestra un formulario para seleccionar el origen de las noticias RSS
     *
     * @return  Opcion seleccionada
     */
    public static int getFormMostrarTitulares(){
        int opcion;
        
        System.out.println(
            "------------------------------------------------------------------------------"+"\n"+
            "RSS"+"\n"+
            "------------------------------------------------------------------------------"+"\n" +
            " 1. Leer desde archivo"+"\n" +
            " 2. Leer desde la web"+"\n" +
            "------------------------------------------------------------------------------");        
        do{                      
            opcion = seleccionarInt("Selecciona una opcion: ");
            if (opcion!=1 && opcion!=2) System.out.print("Error. ");
        }while(opcion!=1 && opcion!=2);
        
        return opcion;
    }    
    
    
    /**
     * Pide un dato de tipo String 
     * 
     * @param mensaje       Mensaje de solicitud que se muestra al usuario
     * @return              Cadena de texto
     */
    public static String seleccionarString(String mensaje){
        Scanner entrada = new Scanner(System.in);        
        String res;
        System.out.print(mensaje);
        res = entrada.nextLine();
        return res;
    }  
    
    
    /**
     * Pide un dato de tipo Float 
     * 
     * @param mensaje       Mensaje de solicitud que se muestra al usuario
     * @return              Número decimal
     */
    public static float seleccionarFloat(String mensaje){
        Scanner entrada = new Scanner(System.in);        
        float res=0;
        System.out.print(mensaje);
        while (entrada.hasNext()) {
           if (entrada.hasNextFloat()) {
              res = entrada.nextFloat();
              break;
           }
           System.out.println("Error, introduce un decimal:");
           entrada.next();           
        } 
        return res;
    }
    
    
    /**
     * Pide un dato de tipo Int 
     * 
     * @param mensaje       Mensaje de solicitud que se muestra al usuario
     * @return              Número entero
     */
    public static int seleccionarInt(String mensaje){
        Scanner entrada = new Scanner(System.in);     
        int res=0;
        System.out.print(mensaje);               
        while (entrada.hasNext()) {
           if (entrada.hasNextInt()) {
              res = entrada.nextInt();
              break;
           }
           System.out.println("Error, introduce un entero:");
           entrada.next();
        }         
        return res;
    }
    
    
    /**
     * Pide el Email 
     * 
     * @param mensaje       Mensaje de solicitud que se muestra al usuario
     * @return              Email
     */
    public static String seleccionarEmail(String mensaje){
        Scanner entrada = new Scanner(System.in);               
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");        
        Matcher mather;        
        String email;        
        System.out.print(mensaje);        
        while(true){
            email = entrada.nextLine();        
            mather = pattern.matcher(email);
            if (mather.find() == false)
                System.out.println("Error, introduce el email:");
            else 
                break;
        }        
        return email;
    }  
    
    
    /**
     * Pide seleccionar una provincia 
     * 
     * @param session       Sesión con la base de datos
     * @param mensaje       Mensaje de solicitud que se muestra al usuario
     * @return              Identificador de la provincia seleccionada
     */
    public static int seleccionarProvincia(Session session,String mensaje){
        //Muestra por pantalla las provincias y permite seleccionar una
        int idProvincia;
        System.out.print(mensaje);       
        Pais pais=new Pais();
        do {                 
            pais.getProvinciasStr(session);
            idProvincia=(int)Menu.getOpcion();                                                                           
            if (!pais.existeProvincia(session,idProvincia)){
                System.out.println("Opción incorrecta !!!");
            }else{
                break;                                 
            }  
        }while (!pais.existeProvincia(session,idProvincia));      
        return idProvincia;
    }
    
    
    /**
     * Pide seleccionar una tienda
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @param mensaje       Mensaje de solicitud que se muestra al usuario
     * @return              Identificador de la tienda seleccionada
     */
    public static long seleccionarTienda(Franquicia franquicia, String mensaje){
        long idTienda;
        System.out.print(mensaje);       
        do {                 
            franquicia.getTiendas();
            idTienda=Menu.getOpcion();                                                                           
            if (!franquicia.existeTienda(idTienda)){
                System.out.println("Opción incorrecta !!!");
            }else{
                break;                                 
            }  
        }while (!franquicia.existeTienda(idTienda)); 
        return idTienda;
    }    
    
    
    /**
     * Pide seleccionar un cliente
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @param mensaje       Mensaje de solicitud que se muestra al usuario
     * @return              Identificador del cliente seleccionado
     */
    public static long seleccionarCliente(Franquicia franquicia, String mensaje){
        long idCliente;
        System.out.print(mensaje);       
        do {                 
            franquicia.getClientes();
            idCliente=Menu.getOpcion();                                                                           
            if (!franquicia.existeCliente(idCliente)){
                System.out.println("Opción incorrecta !!!");
            }else{
                break;                                 
            }  
        }while (!franquicia.existeTienda(idCliente)); 
        return idCliente;
    }
    
    
    /**
     * Pide seleccionar un empleado
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @param mensaje       Mensaje de solicitud que se muestra al usuario
     * @return              Identificador del empleado seleccionado
     */
    public static long seleccionarEmpleado(Franquicia franquicia, String mensaje){
        long idEmpleado;
        System.out.print(mensaje);       
        do {                 
            franquicia.getEmpleados();
            idEmpleado=Menu.getOpcion();                                                                           
            if (!franquicia.existeEmpleado(idEmpleado)){
                System.out.println("Opción incorrecta !!!");
            }else{
                break;                                 
            }  
        }while (!franquicia.existeEmpleado(idEmpleado)); 
        return idEmpleado;
    }
    
    
    /**
     * Pide seleccionar un producto
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @param mensaje       Mensaje de solicitud que se muestra al usuario
     * @return              Identificador del producto seleccionado
     */
    public static long seleccionarProducto(Franquicia franquicia, String mensaje){
        long idProducto;
        System.out.print(mensaje);       
        do {                 
            franquicia.getProductos();
            idProducto=Menu.getOpcion();                                                                           
            if (!franquicia.existeProducto(idProducto)){
                System.out.println("Opción incorrecta !!!");
            }else{
                break;                                 
            }  
        }while (!franquicia.existeProducto(idProducto));      
        return idProducto;
    }    
       
    
    /**
     * Pide seleccionar el producto de una tienda
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @param idTienda      Identificado de la tienda que contiene los productos
     * @param mensaje       Mensaje de solicitud que se muestra al usuario
     * @return              Identificador del producto seleccionado
     */
    public static long seleccionarProductoTienda(Franquicia franquicia,long idTienda, String mensaje){   
        long idProducto;
        System.out.print(mensaje);       
        do {     
            franquicia.getProductos(idTienda);
            idProducto=Menu.getOpcion();                                                                           
            if (!franquicia.existeProducto(idProducto)){
                System.out.println("Opción incorrecta !!!");
            }else{
                break;                                 
            }  
        }while (!franquicia.existeProducto(idProducto));  
        return idProducto;
    }
    
    
    /**
     * Pide seleccionar el empleado de una tienda
     * 
     * @param franquicia    Instancia de la clase Franquicia
     * @param idTienda      Identificador de la tienda que contiene los empleados
     * @param mensaje       Mensaje de solicitud que se muestra al usuario
     * @return              Identificador del empleado seleccionado
     */
    public static long seleccionarEmpleadoTienda(Franquicia franquicia,long idTienda, String mensaje){   
        //Muestra por pantalla los empleados y permite seleccionar uno
        long idEmpleado;
        System.out.print(mensaje);       
        do {     
            franquicia.getEmpleados(idTienda);
            idEmpleado=Menu.getOpcion();                                                                           
            if (!franquicia.existeEmpleado(idEmpleado)){
                System.out.println("Opción incorrecta !!!");
            }else{
                break;                                 
            }  
        }while (!franquicia.existeEmpleado(idEmpleado));  
        return idEmpleado;
    }

        
}
