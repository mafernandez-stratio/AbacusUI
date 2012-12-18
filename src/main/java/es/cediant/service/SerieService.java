/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.service;

import es.cediant.dao.ISerieDAO;
import es.cediant.database.Serie;
import java.util.List;


public class SerieService implements ISerieService {   

    // SerieDAO is injected...
    ISerieDAO serieDAO;
    
    public ISerieDAO getSerieDAO() {
        return serieDAO;
    }

    public void setSerieDAO(ISerieDAO serieDAO) {
        this.serieDAO = serieDAO;
    }
    
    
    @Override
    public List<Serie> getSeries() {
        return getSerieDAO().getSeries();
    }
    
}
