package persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * 
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
//CHECKSTYLE:OFF
public class HibernateUtils {

    private static SessionFactory sessionFactory;

    static {
       sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    
    /**
     * Getter.
     * @return factory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * shutdown.
     */
    public static void shutdown() {
        getSessionFactory().close();
    }
}
