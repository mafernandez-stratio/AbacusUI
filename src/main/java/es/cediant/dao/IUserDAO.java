/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.dao;

import es.cediant.database.User;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author miguel
 */
public interface IUserDAO {
    
    public User getUserByUsername(String username);

    public StreamedContent getPicDB(String username);
    
}
