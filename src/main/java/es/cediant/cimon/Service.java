/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.cimon;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author miguel
 */
public class Service implements Serializable {
    
    private static final long serialVersionUID = 1L;    
    
    private Logger logger = LoggerFactory.getLogger(Service.class);
    
    private String name;    
    
    public Service(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
    
}
