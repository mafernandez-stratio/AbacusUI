/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.database;

import es.cediant.abacus.UserBean;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author miguel
 */
public class ConnectHibernate {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    
    private static Logger logger = LoggerFactory.getLogger(ConnectHibernate.class);
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); 
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry(); 
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Log the exception. 
            // System.err.println("Initial SessionFactory creation failed." + ex);
            // throw new ExceptionInInitializerError(ex);
            logger.error(ex.getMessage());
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
