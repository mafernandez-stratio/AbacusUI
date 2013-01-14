/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.cimon;

import com.google.common.collect.Iterators;
import java.util.ArrayList;
import java.util.Collection;
import javax.cim.CIMArgument;
import javax.cim.CIMObjectPath;
import javax.security.auth.Subject;
import javax.wbem.CloseableIterator;
import javax.wbem.client.PasswordCredential;
import javax.wbem.client.UserPrincipal;
import javax.wbem.client.WBEMClient;
import javax.wbem.client.WBEMClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author miguel
 */
public class OpenLMIConnection {
    //VARIABLES
    private static OpenLMIConnection instance;
    private final Logger logger = LoggerFactory.getLogger(OpenLMIConnection.class);
    private WBEMClient cli;
    private CIMObjectPath cop;
    CIMObjectPath copInstance;

    //CONSTRUCTORS

    //METHODS
    public static OpenLMIConnection getInstance(){
        if (instance==null){
            instance = new OpenLMIConnection();
            instance.setCLI("CIM-XML");
        }
        return instance;
    }
        
    private void setCLI(String cli){
        try {
            /*
             * public static WBEMClient getClient(java.lang.String protocol) throws java.lang.Exception
             * Get a WBEMClient for a protocol.
             * Parameters:
             * protocol - The protocol name (e.g. "CIM-XML")
             * Returns:
             * the WBEMClient implementation for the protocol specified.
             * Throws:
             * java.lang.IllegalArgumentException -
             *      If the protocol is null or empty
             *      If the protocol is not supported 
             *      If the protocol implementation could not be loaded
             */
            this.cli = WBEMClientFactory.getClient(cli);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
    
    public void setCOP(String protocol, String host, int port, String namespace, String className){
        /*
         * public CIMObjectPath(java.lang.String scheme, java.lang.String host, java.lang.String port,
         *                      java.lang.String namespace, java.lang.String objectName, CIMProperty[] keys)
         * Constructs a CIM Object Path referencing an CIMObjectPathInstance of the specified CIM element as defined in the specified namespace on the specified host and identified by the given key properties and their corresponding values. Note that the connection mechanism and the port number to which a client connection is established are also specified.
         * Parameters:
         * scheme - The connection scheme to the host (e.g. http, https, ...)
         * host - The host name or IP Address.
         * port - The port on the host to which the connection was established.
         * namespace - The namepace in which the CIM element is defined.
         * objectName - The name of the CIM element referenced.
         * keys - CIMProperty[] The keys and their corresponding values that identify an CIMObjectPathInstance of the CIM element.
         */
        try {
            cop = new CIMObjectPath(protocol, host, Integer.toString(port), namespace, null, null);
            copInstance = new CIMObjectPath(namespace + "/:" + className);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
    
    public void initializeCLI(String username, String password){
        try {
            Subject subject = new Subject();
            subject.getPrincipals().add(new UserPrincipal(username));
            subject.getPrivateCredentials().add(new PasswordCredential(password));
            /*
             * public void initialize(CIMObjectPath name, javax.security.auth.Subject subject, java.util.Locale[] locales) throws WBEMException
             * Initialize the client connection. This must be called before any operations.
             * Parameters:
             * name - The protocol and host to use (e.g. http://192.168.1.128/). Any other fields will be ignored.
             * subject - The principal/credential pairs for this connection.
             * locales - An array of locales in order of priority of preference.
             * Throws:
             * java.lang.IllegalArgumentException - If the host or scheme portion of the object path is null. If the protocol is not supported.
             * WBEMException - If the protocol adapter or security can not be initialized
             */            
            cli.initialize(cop, new Subject(), null);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
    
    public Object invokeCIMMethod(String method, CIMArgument<?>[] input, int outputSize){
        try {
            // CIMArgument<?>[] input = new CIMArgument[1];
            CIMArgument<?>[] output = new CIMArgument[outputSize];  
            /*
             * public CIMDataType(java.lang.String pClassName, int pSize)
             * Creates a new CIM REFERENCE_ARRAY data type object with the specified class reference.
             * Parameters:
             * pClassName - The CIM class reference name.
             * pSize - The size of the array. 0 indicates the array is unbounded.
             */
            //CIMDataType d = new CIMDataType(CIMDataType.UINT16, 1);
            /*
             * public CIMArgument(java.lang.String name, CIMDataType type, java.lang.Object value)
             * Constructs a CIMArgument to be used for method invocations. A CIMArgument corresponds to a CIMParameter. For each CIMParameter being populated during a method invocation a CIMArgument object must be created.
             * Parameters:
             * name - Name of the CIM argument
             * type - CIMDataType of the argument
             * value - value of the argument
             * Throws:
             * java.lang.IllegalArgumentException - If the value does not match the type.
             */
            //input[0] = new CIMArgument(nameArgument, d, new UnsignedInteger16(inputValue));
            return cli.invokeMethod(copInstance, method, input, output);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }
    
    public Collection getInstanceNames(){
        try {
            Collection names = new ArrayList<>();
            logger.info("Collection names initialized.");
            CloseableIterator<CIMObjectPath> iter = cli.enumerateInstanceNames(cop);
            logger.info("CloseableIterator created.");
            Iterators.addAll(names, iter);
            logger.info("'Iterator --> Collection' conversion done.");
            return names;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }
    
}
