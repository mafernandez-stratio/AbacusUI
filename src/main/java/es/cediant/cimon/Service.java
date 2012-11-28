/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.cimon;

import java.io.Serializable;

/**
 *
 * @author miguel
 */
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;    
    
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
