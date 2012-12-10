/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.database;

import es.cediant.abacus.DocXML;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author miguel
 */
public class DatabaseConnection {
    
    private static DatabaseConnection instance = new DatabaseConnection();
    private static Connection connection;
    private static String driverClass;
    private final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class); 
    
    public static synchronized DatabaseConnection getInstance(){
        return instance;
    }
    
    private DatabaseConnection(){
        try {
            DocXML applicationContext = new DocXML("applicationContext.xml");
            driverClass = applicationContext.getPropertyValue("DataSource", "driverClass");
            String jdbcUrl = applicationContext.getPropertyValue("DataSource", "jdbcUrl");
            String user = applicationContext.getPropertyValue("DataSource", "user");
            String password = applicationContext.getPropertyValue("DataSource", "password");
            connection = DriverManager.getConnection(jdbcUrl, user, password);
        } catch (ParserConfigurationException | SAXException | SQLException | IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return DatabaseConnection.connection;
    }
    
    public String getDriverName(){
        return DatabaseConnection.driverClass;
    }
    
}
