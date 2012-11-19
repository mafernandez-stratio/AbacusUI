/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mafernandez
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    private String username;
    private String password;
    private Logger logger;
    
    /**
     * Creates a new instance of UserBean
     */
    public LoginBean() {
        logger = LoggerFactory.getLogger(LoginBean.class);
        logger.info("UserBean created");
        // assume SLF4J is bound to logback in the current environment
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        // print logback's internal status
        StatusPrinter.print(lc);
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
    
    public String login(){
        String nextWeb = "login.xhtml";
        if(username != null  && username.equals("admin") && password != null  && password.equals("admin")){
            logger.info("Login: ");
            nextWeb = "welcomePrimefaces.xhtml";
        } else {
            logger.info("Login error: ");            
        }
        logger.info("username = {}.", username);
        logger.info("password = {}.", password);
        return nextWeb;
    }
}
