/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
    
    public DocXML (String fileName) throws ParserConfigurationException, SAXException, IOException{
        this.factory = DocumentBuilderFactory.newInstance();
        this.factory.setValidating(true);
        this.factory.setIgnoringElementContentWhitespace(true);
        this.builder = factory.newDocumentBuilder();
        this.file = new File(fileName);
        this.doc = builder.parse(this.file);
        this.doc.getDocumentElement().normalize();
    }
    
    public Node getProperty(String id, String propertyName){
        Element element = doc.getElementById(id);
        return element.getElementsByTagName(propertyName).item(0);
    }
    
    public String getPropertyValue(String id, String propertyName){
        return getProperty(id, propertyName).getNodeValue();
    }
    
}
