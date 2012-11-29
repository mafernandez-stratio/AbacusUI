/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.cimon;

import java.util.Comparator;

/**
 *
 * @author miguel
 */
public class ServiceComparator implements Comparator<Service>{

    @Override
    public int compare(Service t1, Service t2) {
        return t1.getName().compareToIgnoreCase(t2.getName());        
    }
    
}
