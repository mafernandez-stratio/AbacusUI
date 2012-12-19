/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cediant.dao;

import es.cediant.database.Serie;
import java.util.ArrayList;
import java.util.HashMap;
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
    public HashMap<String, ArrayList<Serie>> getSeries() {
        Session session = getSessionFactory().getCurrentSession();
        //Query q = session.createQuery("from User as user where user.username LIKE '"+username+"'");
        Query q = session.createQuery("from webdb.serie");
        // SELECT * FROM webdb.serie;
        ArrayList<Serie> seriesList = (ArrayList<Serie>) q.list();
        HashMap<String, ArrayList<Serie>> map = new HashMap<>();
        for(int i=0; i<seriesList.size(); i++){
            Serie currentSerie = seriesList.get(i);
            if(map.containsKey(currentSerie.getName())){
                map.get(currentSerie.getName()).add(currentSerie);
            } else {                
                ArrayList<Serie> currentArray = new ArrayList<>();
                currentArray.add(currentSerie);
                map.put(currentSerie.getName(), currentArray);
            }           
        }
        return map;
    }
    
}
