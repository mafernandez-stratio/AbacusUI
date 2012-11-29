/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.cimon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author miguel
 */
public class OpenLMIproviders implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<Service> services;

    public OpenLMIproviders() {
        this.services = new ArrayList<>();
    }
    
    public List<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }
    
    public void fetchServices() throws Throwable {
        services.clear();
        services.add(new Service("take"));
        for (int i=0; i<40; i++){
            services.add(new Service("service"+i));
        }           
        services.add(new Service("contact"));
    }

    public void sortServices() {
        Collections.sort(services, new ServiceComparator());
    }
    
}