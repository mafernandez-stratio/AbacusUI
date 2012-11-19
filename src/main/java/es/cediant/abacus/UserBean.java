/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.database.UserHelper;
import es.cediant.database.Users;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mafernandez
 */
@ManagedBean
@SessionScoped
public final class UserBean {
    private boolean loggedin;
    private String username;
    private String password;
    private String pic;
    private final Logger logger;
    
    public UserBean() {      
        this.logger = LoggerFactory.getLogger(LoginBean.class);
        this.setPic("defaultPic.png");
        this.setLoggedin(false);
    }
    
    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedIn) {
        System.out.println("Loggedin = "+loggedIn);
        this.loggedin = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("Username = "+username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println("Password = "+password);
        this.password = password;
    }
    
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        System.out.println("New Pic");   
        this.pic = pic;
    }
    
    public void login(ActionEvent actionEvent){
        RequestContext context = RequestContext.getCurrentInstance();  
        if(username != null && password != null) {  
            UserHelper userQueries = new UserHelper();
            Users user = userQueries.getUserByUsername(username);
            if(user != null && user.getPassword().equals(password)){
                this.setLoggedin(true);  
                logger.info("Loggedin");
            } else {                      
                this.setLoggedin(false);  
                logger.info("Incorrect login");
            }  
        }
        logger.info("Username: {}", username);
        logger.info("Password: {}", password);
        context.addCallbackParam("loggedIn", this.isLoggedin());  
    }
    
    public void logout(){
        logger.info("Loggedout");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();        
    }
}
