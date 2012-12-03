/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class ReportBean {
    
    final Logger logger = LoggerFactory.getLogger(ReportBean.class);

    /**
     * Creates a new instance of ReportBean
     */
    public ReportBean() {
    }
    
    /**
     *
     * @param actionEvent
     */
    public void createReport() throws JRException, FileNotFoundException, ClassNotFoundException, SQLException{
        logger.info("Creating report...");     
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection(url, user, passwd);
        Statement stmt = (Statement) conn.createStatement();

        JasperDesign jasperDesign = JRXmlLoader.load("report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);     

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

        JasperViewer.viewReport(jasperPrint,false);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
    }
}
