
package com.mycompany.ad04.bd;

import com.mycompany.ad04.entidades.Cliente;
import com.mycompany.ad04.entidades.Empleado;
import com.mycompany.ad04.entidades.Horario;
import com.mycompany.ad04.entidades.Stock;
import com.mycompany.ad04.entidades.Tienda;
import com.mycompany.ad04.entidades.Producto;
import com.mycompany.ad04.entidades.Provincia;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 * Clase HibernateUtil
 * 
 * @author Marcelino Álvarez García
 */
public class HibernateUtil {
    
    private static SessionFactory sessionFactory;

    
    /**
     *
     * @param con       Objeto de la clase Config con los parámetros de conexión
     * @return          Devuelve la sesión para poder hacer operaciones con la BD
     */
    public static SessionFactory getSessionFactory(Config con){
        
        //Se a sesion non se configurou, creamolo
        if(sessionFactory == null){
            try{
                Configuration configuration = new Configuration();
                
                //Propiedades de la conexión
                Properties settings = new Properties();
                settings.put(Environment.DRIVER,con.getDriver());
                settings.put(Environment.URL,"jdbc:mysql://"+con.getAddress()+"/"+con.getName());
                settings.put(Environment.USER, con.getUser());
                settings.put(Environment.PASS, con.getPassword());
                settings.put(Environment.DIALECT, con.getDialect());
                
                /*
                 * Comentamos la siguiente línea para que no muestre las
                 * consultas por pantalla
                 */
                //settings.put(Environment.SHOW_SQL, con.getSHOW_SQL());
                
                /*
                 * En el archivo config.json el parámetro HBM2DDL_AUTO está
                 * fijado a create. Lo cambiamos a update para que cree las 
                 * provincias, si y solo si, no existen.
                 */
                //settings.put(Environment.HBM2DDL_AUTO, con.getHBM2DDL_AUTO());
                settings.put(Environment.HBM2DDL_AUTO, "update"); 
                               
                configuration.setProperties(settings);
                
                //Entidades mapeadas                               
                configuration.addAnnotatedClass(Provincia.class);
                configuration.addAnnotatedClass(Producto.class);
                configuration.addAnnotatedClass(Tienda.class);
                configuration.addAnnotatedClass(Stock.class);
                configuration.addAnnotatedClass(Cliente.class);                
                configuration.addAnnotatedClass(Horario.class);
                configuration.addAnnotatedClass(Empleado.class);
                                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }catch(HibernateException e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
        
}
