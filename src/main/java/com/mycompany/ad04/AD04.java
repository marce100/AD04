
package com.mycompany.ad04;

import com.mycompany.ad04.entidades.Pais;
import com.mycompany.ad04.menu.Menu;
import com.mycompany.ad04.bd.BD;
import com.mycompany.ad04.disco.Archivo;
import com.google.gson.Gson;
import com.mycompany.ad04.bd.Config;
import org.hibernate.Session;


/**
 * Clase AD03
 * 
 * @author Marcelino Álvarez García
 */
public class AD04 {

    private static final String CONFIG = "config.json";
    private static final String PROVINCIAS = "provincias.json";
    private static final String INFORME = "informe.json";
    
        
    /**
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        
        //Conectar con la base de datos
        Config con = new Gson().fromJson(new Archivo().leerArchivo(CONFIG), Config.class);  
        Session session = BD.connectDatabase(con);
                
        //Cargar la tabla de provincias
        Pais pais = new Gson().fromJson(new Archivo().leerArchivo(PROVINCIAS), Pais.class);       
        BD.insertProvincias(session, pais);
 
        // Clase principal que interactúa con todas las entidades
        Franquicia franquicia = new Franquicia(session);               
         
        // Opciones del menú
        Acciones accion = new Acciones(franquicia);
        
        //Pide seleccionar una opción mientras no salgamos del programa
        int opcion;  
        do {
            // Muestra el menú
            Menu.getMenu();                
            opcion=(int)Menu.getOpcion();
            switch(opcion) {
                case 1:  accion.insertarTiendaFranquicia();   break;
                case 2:  accion.insertarProductoFranquicia(); break;                              
                case 3:  accion.insertarProductoTienda();     break; 
                case 4:  accion.insertarClienteFranquicia();  break; 
                case 5:  accion.insertarEmpleadoFranquicia(); break;                
                case 6:  accion.insertarEmpleadoTienda();     break;
                case 7:  accion.mostrarTiendasFranquicia();   break;  
                case 8:  accion.mostrarProductosFranquicia(); break;
                case 9:  accion.mostrarProductosTienda();     break; 
                case 10: accion.mostrarStockTienda();         break;                 
                case 11: accion.mostrarClientesFranquicia();  break;
                case 12: accion.mostrarEmpleadosFranquicia(); break;
                case 13: accion.mostrarEmpleadosTienda();     break;                 
                case 14: accion.mostrarHorarioTienda();       break;                  
                case 15: accion.updateProductoTienda();       break;                 
                case 16: accion.updateEmpleadoTienda();       break;  
                case 17: accion.eliminarTiendaFranquicia();   break; 
                case 18: accion.eliminarProductoFranquicia(); break;          
                case 19: accion.eliminarProductoTienda();     break;                  
                case 20: accion.eliminarClienteFranquicia();  break; 
                case 21: accion.eliminarEmpleadosFranquicia();break;     
                case 22: accion.eliminarEmpleadosTienda();    break;                              
                case 23: accion.mostrarTitulares();           break;
                case 24: accion.generarInforme(INFORME);      break;
                case 25: accion.salir();                      break;
                default:                                        
                    System.out.println("Opción incorrecta !!!");                                        
              }          
        }while (opcion!=25); 
                
    } 

}
