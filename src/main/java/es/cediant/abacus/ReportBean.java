/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.database.Serie;
import es.cediant.service.ISerieService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class ReportBean {
    
    final Logger logger = LoggerFactory.getLogger(ReportBean.class);   
    
    //Spring User Service is injected...
    @ManagedProperty(value="#{SerieService}")
    ISerieService serieService;

    public ISerieService getSerieService() {
        return serieService;
    }

    public void setSerieService(ISerieService serieService) {
        this.serieService = serieService;
    }
    
    private List<Serie> getSeries() {
        return getSerieService().getSeries();
    }
    
    /**
     *
     * @param actionEvent
     */
    public void createReport() throws JRException, FileNotFoundException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException, IOException{
        logger.info("Creating report...");    
        
        List<Serie> series = getSeries();        
        
        //Connection conn = DatabaseConnection.getInstance().getConnection();              
        
        // Session session = ConnectHibernate.getSessionFactory().getCurrentSession();
        // Session session = SessionFactory.getCurrentSession();
        // Connection conn = session.disconnect();
        
        // UserDAO userDAO = new UserDAO();
        // Session session = userDAO.getSessionFactory().getCurrentSession();   
        
        // Class.forName(DatabaseConnection.getInstance().getDriverName());       
        
        JasperDesign jasperDesign = JRXmlLoader.load("reports/report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);        
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null);
        JasperViewer.viewReport(jasperPrint, false);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "reports/report.pdf");
                
        /*ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = classLoader.getResource("/resources.properties").getPath();
        logger.info("Path = "+path);
        for (URL url : Collections.list(Thread.currentThread().getContextClassLoader().getResources(""))) {
            logger.info(url.getPath());
        }                      
        
        
        DocXML doc = new DocXML("/WEB-INF/applicationContext.xml");
        String driver = doc.getPropertyValue("DataSource", "driverClass");
        String url = doc.getPropertyValue("DataSource", "jdbcUrl");
        String user = doc.getPropertyValue("DataSource", "user");
        String passwd = doc.getPropertyValue("DataSource", "password");*/
        
        //Session session = this.sessionFactory.getCurrentSession();       
        /*session.doWork(new Work() {
            @Override
            public void execute(Connection conn) throws SQLException {
                try {
                    JasperDesign jasperDesign = JRXmlLoader.load("report.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);  
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
                    JasperViewer.viewReport(jasperPrint,false);
                    JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
                } catch (JRException ex) {
                    logger.info(ex.getMessage());
                }
            }            
        });*/
        
        // Class.forName(driver);
        // conn = DriverManager.getConnection(url, user, passwd);
        // Statement stmt = (Statement) conn.createStatement();
        logger.info("Report created.");
    }
    
    
}
