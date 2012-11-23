/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.dao;

import es.cediant.database.User;

/**
 *
 * @author miguel
 */
public interface IUserDAO {
    public User getUserByUsername(String username);
}
