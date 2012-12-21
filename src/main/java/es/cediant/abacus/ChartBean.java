/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;  
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author miguel
 */
@ManagedBean
@RequestScoped
public class ChartBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    final Logger logger = LoggerFactory.getLogger(ChartBean.class);
    private CartesianChartModel categoryModel;    
    private CartesianChartModel linearModel;
    private CartesianChartModel liveLinearModel;

    /**
     * Creates a new instance of ChartBean
     */
    public ChartBean() {
        createCategoryModel();  
        createLinearModel();
    }
    
    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CartesianChartModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public CartesianChartModel getLinearModel() {
        return linearModel;
    }

    public void setLinearModel(CartesianChartModel linearModel) {
        this.linearModel = linearModel;
    }
    
    public CartesianChartModel getLiveLinearModel() {
        try {
            CartesianChartModel tmpLinearModel = new CartesianChartModel();  
            // Series 1
            LineChartSeries series1 = new LineChartSeries();  
            series1.setLabel("Series 1");    
            series1.set(1, Math.round(Math.random()*10));  
            series1.set(2, Math.round(Math.random()*10));  
            series1.set(3, Math.round(Math.random()*10));  
            series1.set(4, Math.round(Math.random()*10));  
            series1.set(5, Math.round(Math.random()*10));  
            // Series 2
            LineChartSeries series2 = new LineChartSeries();  
            series2.setLabel("Series 2");  
            series2.setMarkerStyle("diamond");  
            series2.set(1, Math.round(Math.random()*10));  
            series2.set(2, Math.round(Math.random()*10));  
            series2.set(3, Math.round(Math.random()*10));  
            series2.set(4, Math.round(Math.random()*10));  
            series2.set(5, Math.round(Math.random()*10));  
            // ADDING DATA
            tmpLinearModel.addSeries(series1);  
            tmpLinearModel.addSeries(series2);
            this.liveLinearModel = tmpLinearModel;
            return this.liveLinearModel;
        } catch (Exception ex) {
            logger.error("Cartesian Chart Model creation failed.");
            logger.error(ex.getMessage());
            return null;
        }
            
    }

    public void setLiveLinearModel(CartesianChartModel liveLinearModel) {
        this.liveLinearModel = liveLinearModel;
    }
    
    public void itemSelect(ItemSelectEvent event) {          
        logger.info("Selected item: "+event.getSeriesIndex());        
    }  
    
    private void createCategoryModel() {
        try {
            categoryModel = new CartesianChartModel();
            // GRID DATA
            ChartSeries grid = new ChartSeries();  
            grid.setLabel("Grid");
            grid.set("0", 3);
            grid.set("1", 4);
            grid.set("2", 2);
            grid.set("3", 2);
            grid.set("4", 3);
            // MASTER DATA
            ChartSeries master = new ChartSeries();  
            master.setLabel("Master");
            master.set("0", 0);
            master.set("1", 2);
            master.set("2", 1);
            master.set("3", 4);
            master.set("4", 3);
            // ADDING DATA        
            categoryModel.addSeries(grid);  
            categoryModel.addSeries(master);  
        } catch (Exception ex) {
            logger.error("Category Model creation failed.");
            logger.error(ex.getMessage());
        }
            
    }

    private void createLinearModel() {
        try {
            linearModel = new CartesianChartModel();  
            // Series 1
            LineChartSeries series1 = new LineChartSeries();  
            series1.setLabel("Series 1");    
            series1.set(1, 2);  
            series1.set(2, 1);  
            series1.set(3, 3);  
            series1.set(4, 6);  
            series1.set(5, 8);  
            // Series 2
            LineChartSeries series2 = new LineChartSeries();  
            series2.setLabel("Series 2");  
            series2.setMarkerStyle("diamond");  
            series2.set(1, 6);  
            series2.set(2, 3);  
            series2.set(3, 2);  
            series2.set(4, 7);  
            series2.set(5, 9);  
            // ADDING DATA
            linearModel.addSeries(series1);  
            linearModel.addSeries(series2);
        } catch (Exception ex) {
            logger.error("Linear Model creation failed.");
            logger.error(ex.getMessage());
        }
    }
    
    
}
