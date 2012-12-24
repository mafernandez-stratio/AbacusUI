/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.dao;

import es.cediant.database.User;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author miguel
 */
public class UserDAO implements IUserDAO {
    private SessionFactory sessionFactory;
 
    /**
     * Get Hibernate Session Factory
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    /**
     * Set Hibernate Session Factory
     *
     * @param SessionFactory - Hibernate Session Factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = getSessionFactory().getCurrentSession();
        Query q = session.createQuery("from User as user where user.username LIKE '"+username+"'");
        return (User) q.list().get(0);
    }

    @Override
    public StreamedContent getPicDB(String username) {
        Session session = getSessionFactory().getCurrentSession();
        Query q = session.createQuery("from User as user where user.username LIKE '"+username+"'");
        User user = (User) q.list().get(0);
        byte[] bytes = user.getPhoto();
        return new DefaultStreamedContent (new ByteArrayInputStream(bytes));
    }
}
