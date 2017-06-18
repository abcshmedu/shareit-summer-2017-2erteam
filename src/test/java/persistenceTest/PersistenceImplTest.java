package persistenceTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;
import persistence.HibernateUtils;
import persistence.Persistence;
import persistence.PersistenceImpl;
import org.hibernate.SessionFactory;
//CHECKSTYLE:OFF
public class PersistenceImplTest{


    private Persistence pers;
    private ArrayList<Book> books = new ArrayList<>(Arrays.asList(new Book("a", "a", "9781402894626"),new Book("b", "b", "9783161484100")));
    private ArrayList<Disc> discs = new ArrayList<>(Arrays.asList(new Disc("TestTitel", "9781402894626", 10, "TestDirector"), new Disc("TestTitel2", "9783161484100", 10, "TestDirector")));

    public PersistenceImplTest() {
        pers = new PersistenceImpl();
        if(!pers.exist(Book.class, books.get(0).getIsbn()))
            pers.add(books.get(0));
        if(!pers.exist(Disc.class, discs.get(0).getBarcode()))
            pers.add(discs.get(0));
    }
    
    
    
    
    @After
    public void tearDown() {
        /*if (entityManager.isOpen()) {
            tx.commit();
        }*/
    }
    
    @Test
    public void testExist() {
        assertEquals(true, pers.exist(Book.class, books.get(0).getIsbn()));
    }
    
    @Test
    public void testGet() {
        Book b = books.get(0);
        assertEquals(b, pers.get(Book.class, b.getIsbn()));
    }
    
    @Test
    public void testAdd() {
        assertEquals(false, pers.exist(Book.class, "9783551714435"));
        Book b = new Book("Test2", "Author2", "9783551714435");
        pers.add(b);
        assertEquals(true, pers.exist(Book.class, "9783551714435"));
    }
    
    @Test
    public void testGetAll() {
        List<Book> list = pers.getAll(Book.class);
        assertEquals(2, list.size());
    }
    
    @Test
    public void testUpdate() {
        Book toUpdate = books.get(0);
        toUpdate.setAuthor("newAuthor");
        pers.update(toUpdate);
        assertEquals(toUpdate, pers.get(Book.class,books.get(0).getIsbn()));
    }

}
