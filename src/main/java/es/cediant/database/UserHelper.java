/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.database;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author miguel
 */
public class UserHelper {
    Session session = null;
    private final Logger logger;

    public UserHelper() {
        this.logger = LoggerFactory.getLogger(UserHelper.class);
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public Users getUserByUsername (String username){
        Users user = new Users();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Users as users where users.id.username LIKE '"+username+"'");
            user = (Users) q.list().get(0);
            logger.info("User '"+user.getId().getUsername()+"' recovered");
        } catch (Exception e){
            logger.error("Database error");
            logger.error(e.getMessage());
        }
        return user;        
    }
    
    public Users getUserById (int id){
        Users user = new Users();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Users as users where users.id.username LIKE "+id);
            user = (Users) q.list().get(0);
            logger.info("User '"+user.getId().getIdusers()+"' recovered");
        } catch (Exception e){
            logger.error("Database error");
            logger.error(e.getMessage());
        }
        return user;        
    }
}
