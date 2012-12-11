/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.database;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author miguel
 */
public class ConnectHibernate {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            Configuration configuration = new Configuration();
            configuration.configure(); 
            
            Properties properties = configuration.getProperties();
            properties.stringPropertyNames();
            for(String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                System.out.println(key + " => " + value);
            }           
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
