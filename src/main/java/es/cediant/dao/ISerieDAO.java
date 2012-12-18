/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.dao;

import es.cediant.database.Serie;
import java.util.List;

/**
 *
 * @author miguel
 */
public interface ISerieDAO {
    public List<Serie> getSeries();
}
