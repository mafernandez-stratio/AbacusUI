/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.cimon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.cim.CIMArgument;
import javax.cim.CIMDataType;
import javax.cim.UnsignedInteger16;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author miguel
 */
public class OpenLMIproviders implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Logger logger = LoggerFactory.getLogger(OpenLMIproviders.class);
    
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
    
    public void fetchServices(){
        try {
            services.clear();
            OpenLMIConnection olc = OpenLMIConnection.getInstance();
            olc.setCOP("https", "127.0.0.1", 5989, "root/cimv2", "LMI_Service");
            olc.initializeCLI("root", "futbol23");
            services.addAll(olc.getInstanceNames());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
    
    public void tmp() {
        services.clear();
        services.add(new Service("take"));
        for (int i=0; i<12; i++){
            services.add(new Service("service"+i));
        }           
        services.add(new Service("contact"));
        /***/
        /* wbemcli ein -nl -noverify https://root:futbol23@localhost:5989/root/cimv2:LMI_Service
         * ein = Enumerate instance names
         * -nl = Start a new line for every property returned
         * -noverify = Do not verify the server certificate for https URLs (useful for testing)
         */ 
        String protocolo = "https";
        String host = "127.0.0.1";
        int port = 5989;
        String namespace = "root/cimv2/";
        String classname = "LMI_Service";
        String username = "root";
        String password = "futbol23";
        String method = "RequestPowerStateChange";
        CIMArgument<?>[] input = new CIMArgument[0];
        //CIMArgument<?>[] input = new CIMArgument[1];
        //input[0] = new CIMArgument("PowerState", CIMDataType.UINT16_T, new UnsignedInteger16(12));
        int outputSize = 1;
        /***/ 
        OpenLMIConnection olc = OpenLMIConnection.getInstance();
        olc.setCOP(protocolo, host, port, namespace, classname);
        olc.initializeCLI(username, password);
        Object result = olc.invokeCIMMethod(method, input, outputSize);
        logger.info(" - Object result: ");
        logger.info(result.toString());
    }

    public void sortServices() {
        Collections.sort(services, new ServiceComparator());
    }
    
}
