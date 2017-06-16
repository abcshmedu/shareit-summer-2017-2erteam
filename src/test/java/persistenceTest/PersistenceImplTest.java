package persistenceTest;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;
import persistence.HibernateUtils;
import persistence.Persistence;
import persistence.PersistenceImpl;
//CHECKSTYLE:OFF
public class PersistenceImplTest {

    private static final Injector injector = Guice.createInjector(new GuiceTestModule());
    @Inject
    private SessionFactory sessionFactory;
    private Session entityManager;
    private Transaction tx;
    private Persistence pers;
    
    public PersistenceImplTest() {
        injector.injectMembers(this);
        pers = new PersistenceImpl();
    }
    
    @BeforeClass
    public static void initialize() {
        //Session entityManager = injector.getInstance(SessionFactory.class).getCurrentSession();
        Session entityManager = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = entityManager.beginTransaction();
        
        Book book = new Book("TestTitel", "TestAuthor", "978-1556199094");
        entityManager.persist(book);
        
        Disc disc = new Disc("TestTitel", "TestAuthor", 0, "9783862017782");
        entityManager.persist(disc);
        tx.commit();
    }
    
    @AfterClass
    public static void shutDown() {
        injector.getInstance(SessionFactory.class).close();
    }
    
    @Before
    public void setUp() {
        //entityManager = sessionFactory.getCurrentSession();
        //entityManager = HibernateUtils.getSessionFactory().getCurrentSession();
        //tx = entityManager.beginTransaction();
    }
    
    @After
    public void tearDown() {
        /*if (entityManager.isOpen()) {
            tx.commit();
        }*/
    }
    
    @Test
    public void testExist() {
        assertEquals(true, pers.exist(Book.class, "978-1556199094"));
    }
    
    @Test
    public void testGet() {
        Book b = new Book("TestTitel", "TestAuthor", "978-1556199094");
        assertEquals(b, pers.get(Book.class, "978-1556199094"));
    }
    
    @Test
    public void testAdd() {
        assertEquals(false, pers.exist(Book.class, "978-3551714435"));
        Book b = new Book("Test2", "Author2", "978-3551714435");
        pers.add(b);
        assertEquals(true, pers.exist(Book.class, "978-3551714435"));
    }
    
    @Test
    public void testGetAll() {
        List<Book> list = pers.getAll(Book.class);
        assertEquals(2, list.size());
    }
    
    @Test
    public void testUpdate() {
        Book toUpdate = new Book("TestTitelNeu", "TestAuthorNeu", "978-1556199094");
        pers.update(toUpdate);
        assertEquals(toUpdate, pers.get(Book.class, "978-1556199094"));
    }

}
