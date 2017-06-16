package persistenceTest;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.inject.AbstractModule;

/**
 * 
 * @author Altvatter Robert, Gro�beck Thomas
 *
 */
public class GuiceTestModule extends AbstractModule {
    
/**
 * configure.
 */
    protected void configure() {
        bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());
    }

}
