/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.service;

import es.cediant.dao.IUserDAO;
import es.cediant.database.User;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author miguel
 */
@Transactional(readOnly = true)
public class UserService implements IUserService {

    // UserDAO is injected...
    IUserDAO userDAO;
    
    @Override
    public User getUserByUsername(String username) {
        return getUserDAO().getUserByUsername(username);
    }
    
    /**
     * Get User DAO
     *
     * @return IUserDAO - User DAO
     */
    public IUserDAO getUserDAO() {
        return userDAO;
    }
 
    /**
     * Set User DAO
     *
     * @param IUserDAO - User DAO
     */
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
