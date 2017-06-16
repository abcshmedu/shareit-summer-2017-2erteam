package persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.inject.Inject;

/**
 * 
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class PersistenceImpl implements Persistence {

    @Inject
    private SessionFactory sessionFactory;
    private Session entityManager;
    private Transaction tx;
    
    @Override
    public <T, K extends Serializable> boolean exist(Class<T> t, K key) {
        //entityManager = sessionFactory.getCurrentSession();
        entityManager = HibernateUtils.getSessionFactory().getCurrentSession();
        tx = entityManager.beginTransaction();        
        T elem = entityManager.get(t, key);        
        tx.commit();
        return elem != null;
    }
    
    @Override
    public <T, K extends Serializable> T get(Class<T> t, K key) {
        entityManager = HibernateUtils.getSessionFactory().getCurrentSession();
        tx = entityManager.beginTransaction();        
        T elem = entityManager.get(t, key);        
        tx.commit();
        return elem;
    }
    
    @Override
    public <T> void add(T elem) {
        entityManager = HibernateUtils.getSessionFactory().getCurrentSession();
        tx = entityManager.beginTransaction();        
        entityManager.save(elem);   
        tx.commit();
    }
    
    @Override
    public <T> List<T> getAll(Class<T> t) {
        entityManager = HibernateUtils.getSessionFactory().getCurrentSession();
        tx = entityManager.beginTransaction();        
        String queryString = "from " + t.getSimpleName();
        Query<T> query = entityManager.createQuery(queryString);
        List<T> list = query.list();
        tx.commit();
        return list;
    }
    
    @Override
    public <T> void update(T elem) {
        entityManager = HibernateUtils.getSessionFactory().getCurrentSession();
        tx = entityManager.beginTransaction();
        entityManager.update(elem);
        tx.commit();
    }

}
