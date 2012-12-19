/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.abacus;

import es.cediant.database.Serie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author miguel
 */
class CustomDataSource implements TableModel {

    private HashMap<String, ArrayList<Serie>> series;
    
    public CustomDataSource(HashMap<String, ArrayList<Serie>> series) {
        this.series = series;
    }

    @Override
    public int getRowCount() {
        return series.get(series.keySet().iterator().next()).size();
    }

    @Override
    public int getColumnCount() {
        return series.size();
    }

    @Override
    public String getColumnName(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Class<?> getColumnClass(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isCellEditable(int i1, int i2) {
        return true;
    }

    @Override
    public Object getValueAt(int i1, int i2) {
        Iterator iter = series.keySet().iterator();
        ArrayList<Serie> currentSerie = new ArrayList<>();
        for(int i=0; i<i2; i++){
            currentSerie = (ArrayList<Serie>) iter.next();
        }
        return currentSerie.get(i1);
    }

    @Override
    public void setValueAt(Object obj, int i1, int i2) {
        Iterator iter = series.keySet().iterator();
        ArrayList<Serie> currentSerie = new ArrayList<>();
        for(int i=0; i<i2; i++){
            currentSerie = (ArrayList<Serie>) iter.next();
        }        
        currentSerie.set(i1, (Serie) obj);
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {        
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {       
    }
    
}
