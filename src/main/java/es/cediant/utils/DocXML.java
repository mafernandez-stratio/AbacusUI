/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.utils;

import es.cediant.database.DatabaseConnection;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author miguel
 */
public class DocXML {

    private File file;    
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document doc;
    private final Logger logger = LoggerFactory.getLogger(DocXML.class); 
    
    public DocXML (String fileName) {
        try {
            this.factory = DocumentBuilderFactory.newInstance();
            this.factory.setValidating(true);
            this.factory.setIgnoringElementContentWhitespace(true);
            this.builder = factory.newDocumentBuilder();
            this.file = new File(fileName);
            this.doc = builder.parse(this.file);
            this.doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException ex) {
            logger.error("Document Builder failed.");
            logger.error(ex.getMessage());
        } catch (SAXException | IOException ex) {
            logger.error("Parser failed.");
            logger.error(ex.getMessage());
        } 
    }
    
    public Node getProperty(String id, String propertyName){
        Element element = doc.getElementById(id);
        return element.getElementsByTagName(propertyName).item(0);
    }
    
    public String getPropertyValue(String id, String propertyName){
        return getProperty(id, propertyName).getNodeValue();
    }
    
}
