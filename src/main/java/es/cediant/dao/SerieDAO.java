/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.dao;

import es.cediant.database.Serie;
import es.cediant.database.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class SerieDAO implements ISerieDAO {
    
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
        
    @Override
    public List<Serie> getSeries() {
        Session session = getSessionFactory().getCurrentSession();
        //Query q = session.createQuery("from User as user where user.username LIKE '"+username+"'");
        Query q = session.createQuery("from webdb.serie");
        // SELECT * FROM webdb.serie;
        return q.list();
    }
    
}
