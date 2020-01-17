
package com.mycompany.ad03.bd;

import com.mycompany.ad03.entidades.Cliente;
import com.mycompany.ad03.entidades.Empleado;
import com.mycompany.ad03.entidades.Horario;
import com.mycompany.ad03.entidades.Pais;
import com.mycompany.ad03.entidades.Producto;
import com.mycompany.ad03.entidades.Provincia;
import com.mycompany.ad03.entidades.Stock;
import com.mycompany.ad03.entidades.Tienda;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase BD
 * 
 * @author Marcelino Álvarez García
 */
public class BD {

    /**
     *  Crea una base de datos
     *
     * @param filename  Nombre de la base de datos
     */
    public static void createDatabase(String filename){
        String databaseFile = "jdbc:sqlite:" + filename;        
        try{
            Connection connection = DriverManager.getConnection(databaseFile);
            if(connection != null){
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("Se ha creado la base de datos.");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }

    
    /**
     *  Conecta con la base de datos
     *
     * @param filename      Nombre de la base de datos
     * @return              Sesión con la base de datos
     */
    public static Connection connectDatabase(String filename){
        Connection connection = null;
        try{
            //Creamos la conexión a la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
            System.out.println("Conexión realizada con éxito.");
            return connection;             
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    
    /**
     *  Desconecta la base de datos
     *
     * @param connection    Sesión con la base de datos
     */
    public static void desconnetDatabase(Connection connection){
        try{
            if(connection != null){
                connection.close();
                System.out.println("Desconexión realizada con éxito.");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    /**
     * Crear tablas 
     *
     * @param con           Sesión con la base de datos
     */
    public static void createTables(Connection con){
        try{
            Statement stmt;
            String sql;
            sql=    "CREATE TABLE IF NOT EXISTS provincias (\n" +
                    "   idProvincia integer PRIMARY KEY,\n"+
                    "   nombre text NOT NULL\n"+
                    ");";                                                             
            stmt = con.createStatement();
            stmt.execute(sql);
            sql=    "CREATE TABLE IF NOT EXISTS tiendas (\n" +
                    "   idTienda integer PRIMARY KEY,\n"+
                    "   nombre text NOT NULL,\n"+
                    "   ciudad text NOT NULL,\n"+
                    "   idProvincia integer NOT NULL\n"+
                    ");";                                                               
            stmt = con.createStatement();
            stmt.execute(sql);
            sql=    "CREATE TABLE IF NOT EXISTS productos (\n" +
                    "   idProducto integer PRIMARY KEY,\n"+
                    "   nombre text NOT NULL,\n"+
                    "   descripcion text NOT NULL,\n"+
                    "   precio real NOT NULL"+
                    ");";                                                               
            stmt = con.createStatement();
            stmt.execute(sql);
            sql=    "CREATE TABLE IF NOT EXISTS stock (\n" +
                    "   idStock integer PRIMARY KEY,\n"+
                    "   cantidad integer NOT NULL,\n"+
                    "   idTienda integer NOT NULL,\n"+
                    "   idProducto integer NOT NULL"+
                    ");";                                                               
            stmt = con.createStatement();
            stmt.execute(sql);
            sql=    "CREATE TABLE IF NOT EXISTS clientes (\n" +
                    "   idCliente integer PRIMARY KEY,\n"+
                    "   nombre text NOT NULL,\n"+
                    "   apellidos text NOT NULL,\n"+
                    "   email text NOT NULL"+
                    ");";                                                               
            stmt = con.createStatement();
            stmt.execute(sql);
            sql=    "CREATE TABLE IF NOT EXISTS empleados (\n" +
                    "   idEmpleado integer PRIMARY KEY,\n"+
                    "   nombre text NOT NULL,\n"+
                    "   apellidos text NOT NULL"+
                    ");";                                                               
            stmt = con.createStatement();
            stmt.execute(sql);
            sql=    "CREATE TABLE IF NOT EXISTS horario (\n" +
                    "   idHorario integer PRIMARY KEY,\n"+
                    "   idEmpleado integer NOT NULL,\n"+
                    "   idTienda integer NOT NULL,\n"+
                    "   horasSemanales integer NOT NULL"+
                    ");";                                                               
            stmt = con.createStatement();
            stmt.execute(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }    
    
    
    /**
     * Añade todas las provincias
     * 
     * @param con           Sesión con la base de datos
     * @param pais          Objeto de la clase Pais
     */
    public static void insertProvincias(Connection con, Pais pais){  
        String sql;
        for (Provincia p : pais.getProvincias()){    
            try{       
                sql= "INSERT OR REPLACE INTO provincias(idProvincia,nombre) VALUES(?,?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, p.getId());
                pstmt.setString(2, p.getNome()); 
                pstmt.executeUpdate();                                                                
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }    
    }  
    
    
    /**
     * Añade una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param tienda        Objeto de la clase Tienda
     */
    public static void insertTienda(Connection con, Tienda tienda){  
        String sql;
        try{       
            sql= "INSERT INTO tiendas(nombre,ciudad,idProvincia) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, tienda.getNombre());
            pstmt.setString(2, tienda.getCiudad());                
            pstmt.setInt(3, tienda.getProvincia().getId());            
            pstmt.executeUpdate();            
            System.out.println("Se ha añadido una tienda.");                                     
        }catch(SQLException e){
            System.out.println("No se ha podido añadir la tienda.");                                     
            System.out.println(e.getMessage());
        }
    }   
    
    
    /**
     * Añade el número de horas que trabaja un empleado en una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param horario       Objeto de la clase Horario
     */
    public static void insertHorario(Connection con, Horario horario){  
        String sql;
        try{       
            sql= "INSERT INTO horario(idEmpleado,idTienda,horasSemanales) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, horario.getIdEmpleado());
            pstmt.setInt(2, horario.getIdTienda());                
            pstmt.setInt(3, horario.getHorasSemanales());            
            pstmt.executeUpdate();            
            System.out.println("Se ha añadido un empleado a la tienda.");                                     
        }catch(SQLException e){
            System.out.println("No se ha podido añadir el empleado a la tienda.");                                     
            System.out.println(e.getMessage());
        }
    }   
    
    
    /**
     * Añade un cliente
     * 
     * @param con           Sesión con la base de datos
     * @param cliente       Objeto de la clase Cliente
     */
    public static void insertCliente(Connection con, Cliente cliente){  
        String sql;
        try{       
            sql= "INSERT INTO clientes(nombre,apellidos,email) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellidos());                
            pstmt.setString(3, cliente.getEmail());            
            pstmt.executeUpdate();            
            System.out.println("Se ha añadido un cliente.");                                     
        }catch(SQLException e){
            System.out.println("No se ha podido añadir el cliente.");                                     
            System.out.println(e.getMessage());
        }
    }     
    
    
    /**
     * Añade un empleado
     * 
     * @param con           Sesión con la base de datos
     * @param empleado      Objeto de la clase Empleado
     */
    public static void insertEmpleado(Connection con, Empleado empleado){  
        String sql;
        try{       
            sql= "INSERT INTO empleados(nombre,apellidos) VALUES(?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getApellidos());                
            pstmt.executeUpdate();            
            System.out.println("Se ha añadido un empleado.");                                     
        }catch(SQLException e){
            System.out.println("No se ha podido añadir el empleado.");                                     
            System.out.println(e.getMessage());
        }
    }  
    
    
    /**
     * Añade un producto a una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param stock         Objeto de la clase Stock
     */
    public static void insertStock(Connection con, Stock stock){  
        String sql;
        try{       
            sql= "INSERT INTO stock(cantidad,idTienda,idProducto) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, stock.getCantidad());
            pstmt.setInt(2, stock.getIdTienda());                
            pstmt.setInt(3, stock.getIdProducto());            
            pstmt.executeUpdate();            
            System.out.println("Se ha añadido el stock.");                                     
        }catch(SQLException e){
            System.out.println("No se ha podido añadir el stock.");                                     
            System.out.println(e.getMessage());
        }
    }  
    
    
    /**
     * Actualiza la cantidad de productos de una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param stock         Objeto de la clase Stock
     */
    public static void updateStock(Connection con, Stock stock){  
        String sql;
        try{       
            sql= "UPDATE stock SET cantidad=? WHERE idTienda=? AND idProducto=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, stock.getCantidad());
            pstmt.setInt(2, stock.getIdTienda());                
            pstmt.setInt(3, stock.getIdProducto());            
            pstmt.executeUpdate();            
            System.out.println("Se ha actualizado el stock.");                                     
        }catch(SQLException e){
            System.out.println("No se ha podido actualizar el stock.");                                     
            System.out.println(e.getMessage());
        }
    }   
    
    
    /**
     * Actualiza el número de horas que trabaja un empleado en una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param horario       Objeto de la clase Horario
     */
    public static void updateHorario(Connection con, Horario horario) {
        String sql;
        try{       
            sql= "UPDATE horario SET horasSemanales=? WHERE idEmpleado=? AND idTienda=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, horario.getHorasSemanales());
            pstmt.setInt(2, horario.getIdEmpleado());                
            pstmt.setInt(3, horario.getIdTienda());            
            pstmt.executeUpdate();            
            System.out.println("Se ha actualizado el horario.");                                     
        }catch(SQLException e){
            System.out.println("No se ha podido actualizar el horario.");                                     
            System.out.println(e.getMessage());
        }
    }    
    
    
    /**
     * Añade un producto
     * 
     * @param con           Sesión con la base de datos
     * @param producto      Objeto de la clase Producto
     */
    public static void insertProducto(Connection con, Producto producto){  
        String sql;
        try{       
            sql= "INSERT INTO productos(nombre,descripcion,precio) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());                
            pstmt.setFloat(3, producto.getPrecio());            
            pstmt.executeUpdate();            
            System.out.println("Se ha añadido un producto.");                                     
        }catch(SQLException e){
            System.out.println("No se ha podido añadir el producto.");                                     
            System.out.println(e.getMessage());
        }
    }    
    
    
    /**
     * Selecciona una provincia
     * 
     * @param con           Sesión con la base de datos
     * @param idProvincia   Identificador de la provincia a seleccionar
     * @return              Provincia
     */
    public static Provincia selectProvincia(Connection con, int idProvincia){
        Provincia provincia=null;
        try{
            String sql= "SELECT * FROM provincias WHERE idProvincia=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idProvincia);
            ResultSet rs = pstmt.executeQuery();                        
            while(rs.next()){
                provincia = new Provincia(rs.getInt("idProvincia"),rs.getString("nombre"));
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return provincia;
    }   
    
    
    /**
     * Selecciona todas las tiendas
     * 
     * @param con           Sesión con la base de datos
     * @return              Listado de tiendas
     */
    public static List<Tienda> selectTiendas(Connection con){
        List<Tienda> tiendas=new ArrayList();
        Provincia provincia;
        Tienda tienda;
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tiendas");            
            while(rs.next()){        
                provincia = selectProvincia(con,rs.getInt("idProvincia"));
                tienda = new Tienda(rs.getInt("idTienda"),rs.getString("nombre"),rs.getString("ciudad"),provincia);
                tiendas.add(tienda);
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return tiendas;
    } 
    
    
    /**
     * Selecciona todos los clientes
     * 
     * @param con           Sesión con la base de datos
     * @return              Listado de clientes
     */
    public static List<Cliente> selectClientes(Connection con){
        List<Cliente> clientes=new ArrayList();
        Cliente cliente;
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM clientes");            
            while(rs.next()){        
                cliente = new Cliente(rs.getInt("idCliente"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("email"));
                clientes.add(cliente);
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return clientes;
    } 
    
    
    /**
     * Selecciona todos los empleados
     * 
     * @param con           Sesión con la base de datos
     * @return              Listado de empleados
     */
    public static List<Empleado> selectEmpleados(Connection con){
        List<Empleado> empleados=new ArrayList();
        Empleado empleado;
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM empleados");            
            while(rs.next()){        
                empleado = new Empleado(rs.getInt("idEmpleado"),rs.getString("nombre"),rs.getString("apellidos"));
                empleados.add(empleado);
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return empleados;
    } 

    
    /**
     * Selecciona todos los empleados de una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param idTienda      Identificador de la tienda en la que buscar
     * @return              Listado de empleados
     */
    public static List<Empleado> selectEmpleados(Connection con, int idTienda) {
        List<Empleado> empleados=new ArrayList();
        Empleado empleado;
        try{
            String sql="SELECT empleados.idEmpleado,nombre, apellidos \n"+
                       "FROM horario, empleados \n"+
                       "WHERE horario.idTienda=? AND \n"+
                       "horario.idEmpleado=empleados.idEmpleado";   
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTienda);
            ResultSet rs = pstmt.executeQuery();  
            while(rs.next()){        
                empleado = new Empleado(rs.getInt("idEmpleado"),rs.getString("nombre"),rs.getString("apellidos"));
                empleados.add(empleado);
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return empleados;
    }    

    
    /**
     * Selecciona todos los productos
     * 
     * @param con           Sesión con la base de datos
     * @return              Listado de productos
     */
    public static List<Producto> selectProductos(Connection con){
        List<Producto> productos=new ArrayList();
        Producto producto;
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM productos");            
            while(rs.next()){        
                producto = new Producto(rs.getInt("idProducto"),rs.getString("nombre"),rs.getString("descripcion"),rs.getFloat("precio"));
                productos.add(producto);
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return productos;
    }  
    
    
    /**
     * Selecciona todos los productos de una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param idTienda      Identificador de la tienda en la que buscar
     * @return              Listado de productos
     */
    public static List<Producto> selectProductos(Connection con,int idTienda){
        List<Producto> productos=new ArrayList();
        Producto producto;
        try{
            String sql= "SELECT * FROM productos WHERE idProducto IN (SELECT idProducto FROM stock WHERE idTienda=?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTienda);
            ResultSet rs = pstmt.executeQuery();               
            while(rs.next()){        
                producto = new Producto(rs.getInt("idProducto"),rs.getString("nombre"),rs.getString("descripcion"),rs.getFloat("precio"));
                productos.add(producto);
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return productos;
    }  
    
    
    /**
     * Muestra por pantalla el stock de una tienda para un producto determinado
     * 
     * @param con           Sesión con la base de datos
     * @param stock         Objeto de la clase Stock
     */
    public static void selectStock(Connection con,Stock stock){
        try{
            String sql= "SELECT cantidad,nombre \n"+
                        "FROM stock,productos \n"+
                        "WHERE stock.idTienda = ? AND \n"+
                        "stock.idProducto = ? AND \n"+
                        "stock.idProducto = productos.idProducto";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, stock.getIdTienda());
            pstmt.setInt(2, stock.getIdProducto());
            ResultSet rs = pstmt.executeQuery();               
            while(rs.next()){  
                System.out.printf("%-55s  STOCK: %-25s \n",rs.getString("nombre"),rs.getInt("cantidad")); 
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }  
    
    
    /**
     * Muestra por pantalla el número de horas que trabaja el empleado de una 
     * tienda
     * 
     * @param con           Sesión con la base de datos
     * @param horario       Objeto de la clase Horario
     */
    public static void selectHorario(Connection con, Horario horario) {
        try{
            String sql= "SELECT nombre, apellidos, horasSemanales \n"+
                        "FROM horario,empleados \n"+
                        "WHERE horario.idTienda = ? AND \n"+
                        "horario.idEmpleado = ? AND \n"+
                        "horario.idEmpleado = empleados.idEmpleado";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, horario.getIdTienda());
            pstmt.setInt(2, horario.getIdEmpleado());
            ResultSet rs = pstmt.executeQuery();               
            while(rs.next()){  
                System.out.printf("%-55s  HORAS SEMANALES: %-25s \n",rs.getString("nombre"),rs.getInt("horasSemanales")); 
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    
    /**
     * Selecciona todas las provincias
     * 
     * @param con           Sesión con la base de datos
     * @return              Listado de provincias
     */
    public static List<Provincia> selectProvincias(Connection con){
        List<Provincia> provincias=new ArrayList();
        Provincia provincia;
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM provincias");            
            while(rs.next()){        
                provincia = new Provincia(rs.getInt("idProvincia"),rs.getString("nombre"));
                provincias.add(provincia);
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return provincias;
    }      
    
    
    /**
     * Elimina una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param idTienda      Identificador de la tienda a eliminar
     */
    public static void deleteTienda(Connection con, int idTienda){
        try{
            String sql = "DELETE FROM tiendas WHERE idTienda = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTienda);
            pstmt.executeUpdate();
            System.out.println("Se ha borrado la tienda "+idTienda+" !!!");
            
            sql = "DELETE FROM stock WHERE idTienda = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTienda);
            pstmt.executeUpdate();
            System.out.println("Se ha borrado el stock de la tienda "+idTienda+" !!!");   
            
            sql = "DELETE FROM horario WHERE idTienda = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTienda);
            pstmt.executeUpdate();
            System.out.println("Se han borrado los trabajadores de la tienda "+idTienda+" !!!");                        
        }
        catch(SQLException e){
            System.out.println("No se ha podido borrar los trabajadores de la tienda "+idTienda+" !!!");
            System.err.println(e.getMessage());
        }
    }  
    
    
    /**
     * Elimina un producto
     * 
     * @param con           Sesión con la base de datos
     * @param idProducto    Identificador del producto a eliminar
     */
    public static void deleteProducto(Connection con, int idProducto){
        try{
            String sql = "DELETE FROM productos WHERE idProducto = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idProducto);
            pstmt.executeUpdate();
            System.out.println("Se ha borrado el producto "+idProducto+" !!!");
            

            sql = "DELETE FROM stock WHERE idProducto = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idProducto);
            pstmt.executeUpdate();
            System.out.println("Se ha borrado el stock del producto "+idProducto+" !!!");               
        }
        catch(SQLException e){
            System.out.println("No se ha podido borrar el producto "+idProducto+" !!!");
            System.err.println(e.getMessage());
        }
    }    
    
    
    /**
     * Elimina un producto de una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param idProducto    Identificador del producto a eliminar
     * @param idTienda      Identificador de la tienda que contiene el prouducto
     */
    public static void deleteProductoTienda(Connection con, int idProducto, int idTienda){
        try{
            String sql = "DELETE FROM stock WHERE idProducto = ? AND idTienda = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idProducto);
            pstmt.setInt(2, idTienda);
            pstmt.executeUpdate();
            System.out.println("Se ha borrado el stock del producto "+idProducto+" !!!");               
        }
        catch(SQLException e){
            System.out.println("No se ha podido borrar el producto "+idProducto+" !!!");
            System.err.println(e.getMessage());
        }
    }  
    
    
    /**
     * Elimina un empleado de una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param idEmpleado    Identificador del empleado a eliminar
     * @param idTienda      Identificador de la tienda que contiene al empleado
     */
    public static void deleteEmpleadoTienda(Connection con, int idEmpleado, int idTienda) {
        try{
            String sql = "DELETE FROM horario WHERE idEmpleado = ? AND idTienda = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idEmpleado);
            pstmt.setInt(2, idTienda);
            pstmt.executeUpdate();
            System.out.println("Se ha borrado el empleado de la tienda !!!");               
        }
        catch(SQLException e){
            System.out.println("No se ha podido borrar el empleado de la tienda !!!");
            System.err.println(e.getMessage());
        }
    }    
    
    
    /**
     * Elimina un cliente
     * 
     * @param con           Sesión con la base de datos
     * @param idCliente     Identificador del cliente a eliminar
     */
    public static void deleteCliente(Connection con, int idCliente){
        try{
            String sql = "DELETE FROM clientes WHERE idCliente = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idCliente);
            pstmt.executeUpdate();
            System.out.println("Se ha borrado el cliente "+idCliente+" !!!");               
        }
        catch(SQLException e){
            System.out.println("No se ha podido borrar el cliente "+idCliente+" !!!");
            System.err.println(e.getMessage());
        }
    }   
    
    
    /**
     * Elimina un empleado
     * 
     * @param con           Sesión con la base de datos
     * @param idEmpleado    Identificador del empleado a eliminar
     */
    public static void deleteEmpleado(Connection con, int idEmpleado){
        try{
            String sql = "DELETE FROM empleados WHERE idEmpleado = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idEmpleado);
            pstmt.executeUpdate();
            System.out.println("Se ha borrado el empleado "+idEmpleado+" !!!");    
            
            
            sql = "DELETE FROM horario WHERE idEmpleado = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idEmpleado);
            pstmt.executeUpdate();
            System.out.println("Se ha borrado el empleado de las tiendas !!!");               
        }
        catch(SQLException e){
            System.out.println("No se ha podido borrar el empleado de las tiendas !!!");
            System.err.println(e.getMessage());
        }
    }    
        
    
    /**
     * Comprueba si existe una provincia
     * 
     * @param con           Sesión con la base de datos
     * @param idProvincia   Identificador de la provincia a comprobar
     * @return              Si existe o no la provincia
     */
    public static boolean existeProvincia(Connection con, int idProvincia){
        boolean existe=false;
        try{
            String sql= "SELECT * FROM provincias WHERE idProvincia=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idProvincia);
            ResultSet rs = pstmt.executeQuery();                        
            while(rs.next()){
                existe=true;
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return existe;
    }   
    
    
    /**
     * Comprueba si existe un producto en una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param idProducto    Identificador del producto a comprobar
     * @param idTienda      Identificador de la tienda que contiene el producto
     * @return              Si existe o no el producto
     */
    public static boolean existeStock(Connection con, int idProducto, int idTienda){
        boolean existe=false;
        try{
            String sql= "SELECT * FROM stock WHERE idProducto=? AND idTienda=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idProducto);
            pstmt.setInt(2, idTienda);
            ResultSet rs = pstmt.executeQuery();                        
            while(rs.next()){
                existe=true;
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return existe;
    }   
    
    
    /**
     * Comprueba si existe una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param idTienda      Identificador de la tienda a comprobar
     * @return              Si existe o no la tienda
     */
    public static boolean existeTienda(Connection con, int idTienda){
        boolean existe=false;
        try{
            String sql= "SELECT * FROM tiendas WHERE idTienda=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTienda);
            ResultSet rs = pstmt.executeQuery();                        
            while(rs.next()){
                existe=true;
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return existe;
    } 
    
    
    /**
     * Comprueba si existe un cliente
     * 
     * @param con           Sesión con la base de datos
     * @param idCliente     Identificador del cliente a comprobar
     * @return              Si existe o no el cliente
     */
    public static boolean existeCliente(Connection con, int idCliente){
        boolean existe=false;
        try{
            String sql= "SELECT * FROM clientes WHERE idCliente=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();                        
            while(rs.next()){
                existe=true;
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return existe;
    }  
    
    
    /**
     * Comprueba si existe un empleado
     * 
     * @param con           Sesión con la base de datos
     * @param idEmpleado    Identificador del empleado a comprobar
     * @return              Si existe o no el empleado
     */
    public static boolean existeEmpleado(Connection con, int idEmpleado){
        boolean existe=false;
        try{
            String sql= "SELECT * FROM empleados WHERE idEmpleado=?";
            PreparedStatement pstmt = con.prepareStatement(sql);            
            pstmt.setInt(1, idEmpleado);
            ResultSet rs = pstmt.executeQuery();                        
            while(rs.next()){
                existe=true;
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return existe;
    } 
    
    
    /**
     * Comprueba si existe un empleado en una tienda
     * 
     * @param con           Sesión con la base de datos
     * @param idTienda      Identificador de la tienda que contiene al empleado
     * @param idEmpleado    Identificador del empleado a comprobar
     * @return              Si existe o no el empleado
     */
    public static boolean existeEmpleadoTienda(Connection con, int idTienda,int idEmpleado){
        boolean existe=false;
        try{
            String sql= "SELECT * FROM horario WHERE idTienda=? AND idEmpleado=?";
            PreparedStatement pstmt = con.prepareStatement(sql);            
            pstmt.setInt(1, idTienda);
            pstmt.setInt(2, idEmpleado);
            ResultSet rs = pstmt.executeQuery();                        
            while(rs.next()){
                existe=true;
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return existe;
    }  
    
    
    /**
     * Comprueba si existe un producto
     * 
     * @param con           Sesión con la base de datos
     * @param idProducto    Identificador del producto a comprobar
     * @return              Si existe o no el producto
     */
    public static boolean existeProducto(Connection con, int idProducto){
        boolean existe=false;
        try{
            String sql= "SELECT * FROM productos WHERE idProducto=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();                        
            while(rs.next()){
                existe=true;
            }            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return existe;
    }       

    
}
