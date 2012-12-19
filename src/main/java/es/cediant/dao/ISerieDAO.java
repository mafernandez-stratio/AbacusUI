/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.dao;

import es.cediant.database.Serie;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author miguel
 */
public interface ISerieDAO {
    public HashMap<String, ArrayList<Serie>> getSeries();
}
