
package com.mycompany.ad04.bd;

import com.mycompany.ad04.entidades.Cliente;
import com.mycompany.ad04.entidades.Empleado;
import com.mycompany.ad04.entidades.Horario;
import com.mycompany.ad04.entidades.Pais;
import com.mycompany.ad04.entidades.Producto;
import com.mycompany.ad04.entidades.Provincia;
import com.mycompany.ad04.entidades.Stock;
import com.mycompany.ad04.entidades.Tienda;
import com.mycompany.ad04.entidades.informes.Informe;
import com.mycompany.ad04.entidades.informes.Inf_Tienda;
import com.mycompany.ad04.entidades.informes.Inf_Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Clase BD
 * 
 * @author Marcelino Álvarez García
 */
public class BD {


    /**
     * Conecta con la base de datos
     * 
     * @param config        Parámetros de configuración
     * @return              Sesión con la base de datos
     */
    public static Session connectDatabase(Config config){
        Session session = null;
        try{
            
            //Deshabilitamos los logs
            Logger.getLogger("org.hibernate").setLevel(Level.OFF);
            
            //Creamos la sesión
            session = HibernateUtil.getSessionFactory(config).openSession();
            
        }catch(HibernateException e){
            e.printStackTrace();
        }  
        return session;
    } 
    
    
    /**
     * Desconecta la base de datos
     *
     * @param session    Sesión con la base de datos
     */
    public static void disconnectDatabase(Session session){
        try{
            
            //Cerramos la sessión
            session.close();
            
        }catch(HibernateException e){
            e.printStackTrace();
        }  
    }     

    
    /**
     * Añade todas las provincias
     * 
     * @param session       Sesión con la base de datos
     * @param pais          Objeto de la clase Pais
     */
    public static void insertProvincias(Session session, Pais pais){  
        try{
            Transaction tran = session.beginTransaction();
            for (Provincia p : pais.getProvincias()){
                //Solo las añade si no existen
                //El parámetro HBM2DDL_AUTO tiene que estar a "update"
                session.saveOrUpdate(p);
            } 
            tran.commit();
        }catch(HibernateException e){
            e.printStackTrace();
        }                
    }      
    
    
    /**
     * Añade una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param tienda        Objeto de la clase Tienda
     */
    public static void insertTienda(Session session, Tienda tienda){  
        try{
            Transaction tran = session.beginTransaction();
            session.save(tienda);
            tran.commit(); 
            System.out.println("Se ha añadido una tienda."); 
        }catch(HibernateException e){
            System.out.println("No se ha podido añadir la tienda.");
            e.printStackTrace();
        } 
    }       
    
    
    /**
     * Añade el número de horas que trabaja un empleado en una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param horario       Objeto de la clase Horario
     */
    public static void insertHorario(Session session, Horario horario){  
        try{
            Transaction tran = session.beginTransaction();
            session.save(horario);
            tran.commit(); 
            System.out.println("Se ha añadido un empleado a la tienda.");  
        }catch(HibernateException e){
            System.out.println("No se ha podido añadir el empleado a la tienda."); 
            e.printStackTrace();
        } 
    }   
    
    
    /**
     * Añade un cliente
     * 
     * @param session       Sesión con la base de datos
     * @param cliente       Objeto de la clase Cliente
     */
    public static void insertCliente(Session session, Cliente cliente){  
        try{
            Transaction tran = session.beginTransaction();
            session.save(cliente);
            tran.commit(); 
            System.out.println("Se ha añadido un cliente.");
        }catch(HibernateException e){
            System.out.println("No se ha podido añadir el cliente."); 
            e.printStackTrace();
        }         
    }     
    
    
    /**
     * Añade un empleado
     * 
     * @param session       Sesión con la base de datos
     * @param empleado      Objeto de la clase Empleado
     */
    public static void insertEmpleado(Session session, Empleado empleado){  
        try{
            Transaction tran = session.beginTransaction();
            session.save(empleado);
            tran.commit(); 
            System.out.println("Se ha añadido un empleado.");  
        }catch(HibernateException e){
            System.out.println("No se ha podido añadir el empleado.");  
            e.printStackTrace();
        }   
    }  
    
    
    /**
     * Añade un producto a una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param stock         Objeto de la clase Stock
     */
    public static void insertStock(Session session, Stock stock){  
        try{
            Transaction tran = session.beginTransaction();
            session.save(stock);
            tran.commit(); 
            System.out.println("Se ha añadido el stock."); 
        }catch(HibernateException e){
            System.out.println("No se ha podido añadir el stock.");
            e.printStackTrace();
        } 
    }  
    
    
    /**
     * Actualiza la cantidad de productos de una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param stock         Objeto de la clase Stock
     */
    public static void updateStock(Session session, Stock stock){  
        try{
            Transaction tran = session.beginTransaction();
            session.merge(stock);
            tran.commit(); 
            System.out.println("Se ha actualizado el stock.");  
        }catch(HibernateException e){
            System.out.println("No se ha podido actualizar el stock.");  
            e.printStackTrace();
        } 
    }   
    
    
    /**
     * Actualiza el número de horas que trabaja un empleado en una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param horario       Objeto de la clase Horario
     */
    public static void updateHorario(Session session, Horario horario) {
        try{
            Transaction tran = session.beginTransaction();
            session.merge(horario);
            tran.commit(); 
            System.out.println("Se ha actualizado el horario.");  
        }catch(HibernateException e){
            System.out.println("No se ha podido actualizar el horario.");  
            e.printStackTrace();
        } 
    }    
    
    
    /**
     * Añade un producto
     * 
     * @param session       Sesión con la base de datos
     * @param producto      Objeto de la clase Producto
     */ 
    public static void insertProducto(Session session, Producto producto){  
        try{
            Transaction tran = session.beginTransaction();
            session.save(producto);
            tran.commit();  
            System.out.println("Se ha añadido un producto.");  
        }catch(HibernateException e){
            System.out.println("No se ha podido añadir el producto."); 
            e.printStackTrace();
        } 
    }         
    
    
    /**
     * Selecciona una provincia
     * 
     * @param session       Sesión con la base de datos
     * @param idProvincia   Identificador de la provincia a seleccionar
     * @return              Provincia
     */  
    public static Provincia selectProvincia(Session session, int idProvincia){
        Provincia provincia = null;
        try{  
            provincia = (Provincia) session.get(Provincia.class, new Integer(idProvincia));
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return provincia;
    }  

    
    /**
     * Selecciona una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param idTienda      Identificador de la tienda a seleccionar
     * @return              Tienda
     */
    public static Tienda selectTienda(Session session, long idTienda){
        Tienda tienda = null;
        try{ 
            tienda = (Tienda) session.get(Tienda.class, new Long(idTienda));
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return tienda;

    } 

    
    /**
     * Selecciona un producto
     * 
     * @param session       Sesión con la base de datos
     * @param idProducto    Identificador del producto a seleccionar
     * @return              Producto
     */
    public static Producto selectProducto(Session session, long idProducto){
        Producto producto = null;
        try{            
            producto = (Producto) session.get(Producto.class, new Long(idProducto));
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return producto;
    } 

    
    /**
     * Selecciona un empleado
     * 
     * @param session       Sesión con la base de datos
     * @param idEmpleado    Identificador del empleado a seleccionar
     * @return              Empleado
     */
    public static Empleado selectEmpleado(Session session, long idEmpleado){
        Empleado empleado = null;
        try{          
            empleado = (Empleado) session.get(Empleado.class, new Long(idEmpleado));
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return empleado;
    } 

    
    /**
     * Selecciona un cliente
     * 
     * @param session       Sesión con la base de datos
     * @param idCliente     Identificador del cliente a seleccionar
     * @return              Cliente
     */
    public static Cliente selectCliente(Session session, long idCliente){
        Cliente cliente = null;
        try{          
            cliente = (Cliente) session.get(Cliente.class, new Long(idCliente));
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return cliente;
    } 
  
    
    /**
     * Busca todas las tiendas
     * 
     * @param session       Sesión con la base de datos
     * @return              Listado de tiendas
     */
    public static List<Tienda> selectTiendas(Session session){
        List<Tienda> tiendas = null;
        try{
            Query q = session.createQuery("SELECT t FROM Tienda t");
            tiendas = q.list();            
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return tiendas;
    } 
    
    
    /**
     * Busca todos los clientes
     * 
     * @param session       Sesión con la base de datos
     * @return              Listado de clientes
     */
    public static List<Cliente> selectClientes(Session session){
        List<Cliente> clientes=new ArrayList();
        try{
            Query q = session.createQuery("SELECT c FROM Cliente c");
            clientes = q.list();            
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return clientes;
    } 
    
    
    /**
     * Busca todos los empleados
     * 
     * @param session       Sesión con la base de datos
     * @return              Listado de empleados
     */
    public static List<Empleado> selectEmpleados(Session session){
        List<Empleado> empleados=new ArrayList();
        try{
            Query q = session.createQuery("SELECT e FROM Empleado e");
            empleados = q.list();            
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return empleados;
    } 

    
    /**
     * Busca todos los empleados de una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param idTienda      Identificador de la tienda en la que buscar
     * @return              Listado de empleados
     */
    public static List<Empleado> selectEmpleados(Session session, long idTienda) {
        List<Empleado> empleados=new ArrayList();
        try{
            Query q = session.createQuery(""
                    + "SELECT e FROM Empleado e "
                    + "JOIN e.tiendas s "
                    + "WHERE idTienda=:idTienda"
            );
            q.setParameter("idTienda", idTienda);                            
            empleados = q.list();  
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return empleados;
    }    

    
    /**
     * Busca todos los productos
     * 
     * @param session       Sesión con la base de datos
     * @return              Listado de productos
     */
    public static List<Producto> selectProductos(Session session){
        List<Producto> productos = null;
        try{
            Query q = session.createQuery("SELECT p FROM Producto p");
            productos = q.list();            
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return productos;                        
    }  
    
    
    /**
     * Busca todos los productos de una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param idTienda      Identificador de la tienda en la que buscar
     * @return              Listado de productos
     */
    public static List<Producto> selectProductos(Session session,long idTienda){
        List<Producto> productos = null;
        try{
            Query q = session.createQuery(""
                    + "SELECT p FROM Producto p "
                    + "JOIN p.tiendas s "
                    + "WHERE idTienda=:idTienda"
            );
            q.setParameter("idTienda", idTienda);                            
            productos = q.list();  
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return productos;   
    }  
    
    
    /**
     * Muestra por pantalla el stock de una tienda para un producto determinado
     * 
     * @param session       Sesión con la base de datos
     * @param stock         Objeto de la clase Stock que contiene una Tienda y un Producto
     * @return              Objeto de la clase Stock actualizado con la cantidad
     */
    public static Stock selectStock(Session session,Stock stock){
        try{
            stock = (Stock) session.get(Stock.class, stock.getId());
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return stock;
    }  
    
    
    /**
     * Muestra por pantalla el número de horas que trabaja el empleado de una 
     * tienda
     * 
     * @param session       Sesión con la base de datos
     * @param horario       Objeto de la clase Horario que contiene una Tienda y un Empleado
     * @return              Objeto de la clase Horario actualizado con las horas
     */
    public static Horario selectHorario(Session session, Horario horario) {
        try{
            horario = (Horario) session.get(Horario.class, horario.getId());
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return horario;

    }

    
    /**
     * Busca todas las provincias
     * 
     * @param session       Sesión con la base de datos
     * @return              Listado de provincias
     */   
    public static List<Provincia> selectProvincias(Session session){
        List<Provincia> provincias = null;
        try{
            Query q = session.createQuery("SELECT p FROM Provincia p ORDER BY 1");
            provincias = q.list();               
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return provincias;
    }       
    
    /**
     * Elimina una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param idTienda      Identificador de la tienda a eliminar
     */
    public static void deleteTienda(Session session, long idTienda){                       
      try{
            Transaction tran = session.beginTransaction();               
            Tienda t = selectTienda(session, idTienda);
            session.remove(t);
            session.flush();            
            tran.commit();           
            System.out.println("Se ha borrado la tienda !!!");
        }catch(HibernateException e){
            System.out.println("No se ha podido borrar la tienda !!!");
            e.printStackTrace();
        } 
    }  
    
    
    /**
     * Elimina un producto
     * 
     * @param session       Sesión con la base de datos
     * @param idProducto    Identificador del producto a eliminar
     */
    public static void deleteProducto(Session session, long idProducto){
      try{
            Transaction tran = session.beginTransaction();               
            Producto p = selectProducto(session, idProducto);
            session.remove(p);
            session.flush();            
            tran.commit();           
            System.out.println("Se ha borrado el producto !!!");
        }catch(HibernateException e){
            System.out.println("No se ha podido borrar el producto !!!");
            e.printStackTrace();
        } 
    }    
    
    
    /**
     * Elimina un producto de una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param stock         Objeto de la clase Stock que contiene una Tienda y un Producto
     */
    public static void deleteProductoTienda(Session session, Stock stock){  
      try{
            Transaction tran = session.beginTransaction();               
            stock = selectStock(session, stock);
            session.remove(stock);
            session.flush();            
            tran.commit();           
            System.out.println("Se ha borrado el stock del producto !!!");
        }catch(HibernateException e){
            System.out.println("No se ha podido borrar el stock del producto !!!");
            e.printStackTrace();
        } 
    }  
    
    
    /**
     * Elimina un empleado de una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param horario       Objeto de la clase Horario que contiene una Tienda y un Empleado
     */
    public static void deleteEmpleadoTienda(Session session, Horario horario) {
      try{
            Transaction tran = session.beginTransaction();               
            horario = selectHorario(session, horario);
            session.remove(horario);
            session.flush();            
            tran.commit();           
            System.out.println("Se ha borrado el empleado de la tienda !!!"); 
        }catch(HibernateException e){
            System.out.println("No se ha podido borrar el empleado de la tienda !!!");;
            e.printStackTrace();
        }         
        
    }    
    
    
    /**
     * Elimina un cliente
     * 
     * @param session       Sesión con la base de datos
     * @param idCliente     Identificador del cliente a eliminar
     */
    public static void deleteCliente(Session session, long idCliente){
        try{
            Transaction tran = session.beginTransaction();               
            Cliente c = selectCliente(session, idCliente);
            session.remove(c);
            session.flush();            
            tran.commit();           
            System.out.println("Se ha borrado el cliente !!!");
        }catch(HibernateException e){
            System.out.println("No se ha podido borrar el cliente !!!");
            e.printStackTrace();
        }                 
    }   
    
    
    /**
     * Elimina un empleado
     * 
     * @param session       Sesión con la base de datos
     * @param idEmpleado    Identificador del empleado a eliminar
     */
    public static void deleteEmpleado(Session session, long idEmpleado){
        try{
            Transaction tran = session.beginTransaction();               
            Empleado e = selectEmpleado(session, idEmpleado);
            session.remove(e);
            session.flush();            
            tran.commit();           
            System.out.println("Se ha borrado el empleado !!!");
        }catch(HibernateException e){
            System.out.println("No se ha podido borrar el empleado !!!");
            e.printStackTrace();
        }    
    }    
        
    
    /**
     * Comprueba si existe una provincia
     * 
     * @param session       Sesión con la base de datos
     * @param idProvincia   Identificador de la provincia a comprobar
     * @return              Si existe o no la provincia
     */ 
    public static boolean existeProvincia(Session session, int idProvincia){
        boolean existe=false;
        try{
            Query q = session.createQuery(""
                    + "SELECT COUNT(*) "
                    + "FROM Provincia p "
                    + "WHERE id=:idProvincia");
            q.setParameter("idProvincia", idProvincia);            
            existe = (Long) q.uniqueResult() > 0;             
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return existe;
    }       
    
    /**
     * Comprueba si existe un producto en una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param idProducto    Identificador del producto a comprobar
     * @param idTienda      Identificador de la tienda que contiene el producto
     * @return              Si existe o no el producto
     */
    public static boolean existeStock(Session session, long idProducto, long idTienda){
        boolean existe=false;
        try{
            Query q = session.createQuery(""
                    + "SELECT COUNT(*) "
                    + "FROM Stock s "
                    + "WHERE idProducto=:idProducto AND idTienda=:idTienda ");
            q.setParameter("idProducto", idProducto);            
            q.setParameter("idTienda", idTienda);            
            existe = (Long) q.uniqueResult() > 0;             
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return existe;    
    }   
    
    
    /**
     * Comprueba si existe una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param idTienda      Identificador de la tienda a comprobar
     * @return              Si existe o no la tienda
     */
    public static boolean existeTienda(Session session, long idTienda){
        boolean existe=false;
        try{
            Query q = session.createQuery(""
                    + "SELECT COUNT(*) "
                    + "FROM Tienda t "
                    + "WHERE idTienda=:idTienda");
            q.setParameter("idTienda", idTienda);            
            existe = (Long) q.uniqueResult() > 0;             
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return existe;
    } 
    
    
    /**
     * Comprueba si existe un cliente
     * 
     * @param session       Sesión con la base de datos
     * @param idCliente     Identificador del cliente a comprobar
     * @return              Si existe o no el cliente
     */
    public static boolean existeCliente(Session session, long idCliente){
        boolean existe=false;
        try{
            Query q = session.createQuery(""
                    + "SELECT COUNT(*) "
                    + "FROM Cliente c "
                    + "WHERE idCliente=:idCliente");
            q.setParameter("idCliente", idCliente);            
            existe = (Long) q.uniqueResult() > 0;             
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return existe;
    }  
    
    
    /**
     * Comprueba si existe un empleado
     * 
     * @param session       Sesión con la base de datos
     * @param idEmpleado    Identificador del empleado a comprobar
     * @return              Si existe o no el empleado
     */
    public static boolean existeEmpleado(Session session, long idEmpleado){
        boolean existe=false;
        try{
            Query q = session.createQuery(""
                    + "SELECT COUNT(*) "
                    + "FROM Empleado e "
                    + "WHERE idEmpleado=:idEmpleado");
            q.setParameter("idEmpleado", idEmpleado);            
            existe = (Long) q.uniqueResult() > 0;             
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return existe;
    } 
    
    
    /**
     * Comprueba si existe un empleado en una tienda
     * 
     * @param session       Sesión con la base de datos
     * @param idTienda      Identificador de la tienda que contiene al empleado
     * @param idEmpleado    Identificador del empleado a comprobar
     * @return              Si existe o no el empleado
     */
    public static boolean existeEmpleadoTienda(Session session, long idTienda, long idEmpleado){
        boolean existe=false;
        try{
            Query q = session.createQuery(""
                    + "SELECT COUNT(*) "
                    + "FROM Horario h "
                    + "WHERE idTienda=:idTienda AND idEmpleado=:idEmpleado ");
            q.setParameter("idEmpleado", idEmpleado);            
            q.setParameter("idTienda", idTienda);            
            existe = (Long) q.uniqueResult() > 0;             
        }catch(HibernateException e){
            e.printStackTrace();
        }         
        return existe;
    }  
    
    
    /**
     * Comprueba si existe un producto
     * 
     * @param session       Sesión con la base de datos
     * @param idProducto    Identificador del producto a comprobar
     * @return              Si existe o no el producto
     */
    public static boolean existeProducto(Session session, long idProducto){
        boolean existe=false;
        try{
            Query q = session.createQuery(""
                    + "SELECT COUNT(*) "
                    + "FROM Producto p "
                    + "WHERE idProducto=:idProducto");
            q.setParameter("idProducto", idProducto);            
            existe = (Long) q.uniqueResult() > 0;             
        }catch(HibernateException e){
            e.printStackTrace();
        } 
        return existe;
    }       

    /**
     * Consulta para generar un informe. Para cada tienda se buscan todos sus 
     * productos y el stock.
     * 
     * @param session       Sesión con la base de datos
     * @return              Objeto de la clase Informe
     */
    public static Informe getInforme(Session session){        
        Informe informe = new Informe();
        Inf_Tienda tienda;
        Inf_Producto producto;
        try{
            List<Stock> stocks = null;
            Query q = session.createQuery("SELECT s FROM Stock s");
            stocks = q.list(); 
            //Se procesa el resultado de la consulta
            for (Stock s : stocks){   
                tienda = new Inf_Tienda(s.getTienda().getIdTienda(),s.getTienda().getNombre());
                //Si no existe la tienda se crea
                if (!informe.existeTienda(tienda))
                    informe.addTienda(tienda);
                //Se añade el producto a la última tienda creada                
                producto = new Inf_Producto( s.getProducto().getIdProducto(), s.getProducto().getNombre(), s.getCantidad() );
                informe.getTienda().addProducto(producto);                
            }                           
        }catch(HibernateException e){
            e.printStackTrace();
        }         
        return informe;    
    }
    
}
