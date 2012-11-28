/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.cimon.OpenLMIproviders;
import es.cediant.cimon.Service;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class ServicesBean {

    private Logger logger;
    
    private OpenLMIproviders providers;
    private ArrayList<Service> services;
    private Service selectedService;
        
    /**
     * Creates a new instance of ServicesBean
     */
    public ServicesBean() {
        this.logger = LoggerFactory.getLogger(ServicesBean.class);
        this.providers = new OpenLMIproviders();
        this.services = new ArrayList<>();
    }
    
    public ArrayList<Service> getServices() {
        try {
            fetchServices();
            return services;
        } catch (Throwable ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }
    
    public void fetchServices() throws Throwable {
        providers.fetchServices();
        this.services = providers.getServices();
    }
    
    public Service getSelectedService(){
        return this.selectedService;
    }
    
    public void setSelectedService(Service selectedService){
        this.selectedService = selectedService;
    }
    
    public void onRowSelect(SelectEvent event) {  
        event.getObject();
    }
}
