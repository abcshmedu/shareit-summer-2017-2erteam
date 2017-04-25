package geschaeftslogik;

import java.util.ArrayList;
import java.util.List;

import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;
import datenzugriffsschicht.Medium;

/**
 * Implementation of the service.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public class MediaServiceImpl implements MediaService {

    private List<Book> books;
    private List<Disc> discs;
    
    /**
     * Constructs a MediaService instance.
     */
    public MediaServiceImpl() {
        books = new ArrayList<Book>();
        books.add(new Book("a","a","1"));
        books.add(new Book("b","b","2"));
        discs = new ArrayList<Disc>();
    }
    
    @Override
    public MediaServiceResult addBook(Book b) {
        try{
            books.add(b);
            System.out.println(b.toString());
            System.out.println("new books size is: " + books.size());
            return MediaServiceResult.OK;
        }catch(Exception e){
            e.printStackTrace();
            return MediaServiceResult.FEHLER;
        }
        
        
    }
    
    @Override
    public MediaServiceResult addDisc(Disc d) {
        return MediaServiceResult.FEHLER;
    }
    
    @Override
    public Medium[] getBooks() {
        System.out.println("Books size is: "+books.size());
        Book[] array = new Book[books.size()];
        books.toArray(array);
        for(Book b : array)
            System.out.println(b.toString());
        return array;
    }
    
    @Override
    public Medium[] getDiscs() {
        return null;
    }
    
    @Override
    public MediaServiceResult updateBook(String isbn, Book  b) {
        return null;
    }
    
    @Override
    public MediaServiceResult updateDisc(String barcode, Disc d) {
        return null;
    }
    
    @Override
    public Book getBook(String isbn) {
        return null;
    }
    
    @Override
    public Disc getDisc(String barcode) {
        return null;
    }
}
