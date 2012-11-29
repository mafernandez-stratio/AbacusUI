/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.cimon.Service; 
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel; 

/**
 *
 * @author miguel
 */
public class ServiceDataModel extends ListDataModel<Service> implements SelectableDataModel<Service> {

    public ServiceDataModel() {        
    }
    
    public ServiceDataModel(List<Service> data){
        super(data);
    }
    
    @Override
    public Object getRowKey(Service service) {
        return service.getName();
    }

    @Override
    public Service getRowData(String rowKey) {
        List<Service> services = (List<Service>) getWrappedData();
        for(Service service : services) {
            if(service.getName().equalsIgnoreCase(rowKey)) {
                return service;
            }
        }
        return null;
    }
    
}
