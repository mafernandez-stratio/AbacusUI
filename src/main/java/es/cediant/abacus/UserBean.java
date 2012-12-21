/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.database.User;
import es.cediant.service.IUserService;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mafernandez
 */
@ManagedBean(name="userBean")
@SessionScoped
public final class UserBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String SUCCESS = "success";
    private String ERROR   = "error";    

    private boolean loggedin = false;
    private String username;
    private String password;
    //private String pic;
    private StreamedContent pic;
    final Logger logger = LoggerFactory.getLogger(UserBean.class);
    
    //Spring User Service is injected...
    @ManagedProperty(value="#{UserService}")
    IUserService userService;
    
    public UserBean() {            
        this.setPic("defaultPic.png");
        this.setLoggedin(false);
        logger.info("loggedin="+this.loggedin);
    }
    
    /**
     * Get User Service
     *
     * @return IUserService - User Service
     */
    public IUserService getUserService() {
        return userService;
    }
    
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    
    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        logger.info("Loggedin = "+loggedin);
        this.loggedin = loggedin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        logger.info("Username = "+username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        logger.info("Password = "+password);
        this.password = password;
    }
    
    public StreamedContent getPic() {
        return pic;
    }

    public void setPic(String pic) {
        logger.info("New Pic");   
        try {            
            // UPDATE `webdb.user`.`user` SET `photo`=? WHERE `id`=`1`; 
            ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
            logger.info(extContext.getContextName());
            InputStream stream = extContext.getResourceAsStream("resources/images/"+pic);  
            logger.info(Integer.toString(stream.available()));
            byte[] photo = IOUtils.toByteArray(stream);
            logger.info(Integer.toString(photo.length));
            this.pic = new DefaultStreamedContent(stream, "image/png");
            logger.info(Integer.toString(pic.length()));
            //this.pic = new DefaultStreamedContent(new ByteArrayInputStream(photo), "image/png");
        } catch (Throwable ex) {
            logger.error("Profile picture could not be updated.");
            logger.error(ex.getMessage());
        }
    }        
    
    private User getUserByUsername(String username) {
        return getUserService().getUserByUsername(username);
    }
    
    public void login(ActionEvent actionEvent){
        //RequestContext context = RequestContext.getCurrentInstance();  
        if(username != null && password != null) {  
            User user = getUserByUsername(username);
            if(user != null && user.getPassword().equals(password)){
                this.setLoggedin(true);  
                this.setPic("admin.png");
                logger.info("Loggedin");
            } else {                      
                this.setLoggedin(false);  
                logger.info("Incorrect login");
                //context.addCallbackParam("errorLogin", "The username or password you have entered is incorrect.");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,"The username or password you have entered is incorrect.", "Try again!"));  
            }  
        }
        logger.info("Username: " + username);
        logger.info("Password: " + password);          
    }
    
    public void logout() {
        try {
            this.setLoggedin(false);
            this.setPic("defaultPic.png");
            logger.info("Loggedout");
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            logger.error("Error while redirecting to index.xhtml");
            logger.error(ex.getMessage());
        }
    }
    
}
