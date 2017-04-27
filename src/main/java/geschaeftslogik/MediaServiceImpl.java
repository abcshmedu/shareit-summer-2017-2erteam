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

    private static List<Book> books;
    private static List<Disc> discs;
    
    /**
     * Constructs a MediaService instance.
     */
    public MediaServiceImpl() {
        if (books == null) {
            books = new ArrayList<Book>();
            books.add(new Book("a", "a", "1"));
            books.add(new Book("b", "b", "2"));
        }
        if (discs == null) {
            discs = new ArrayList<Disc>();
            discs.add(new Disc("TestTitel", "1234", 0, "TestDirector"));
            discs.add(new Disc("TestTitel2", "5678", 0, "TestDirector"));
        }
    }
    
    @Override
    public MediaServiceResult addBook(Book b) {
        if (b.getIsbn().length() > 13 || "".equals(b.getAuthor()) || "".equals(b.getTitle())) {
            return MediaServiceResult.BAD_REQUEST;
        }  
        else {
            for (Book book : books) {
                if (book.getIsbn().equals(b.getIsbn())) {
                    return MediaServiceResult.CONFLICT;
                }
            }
            books.add(b);
            System.out.println(b.toString());
            System.out.println("new books size is: " + books.size());
            return MediaServiceResult.OK;
        }
    }
    
    @Override
    public MediaServiceResult addDisc(Disc d) {
        if (d.getBarcode().length() > 14 || "".equals(d.getDirector()) || "".equals(d.getTitle())) {
            return MediaServiceResult.BAD_REQUEST;
        }
        else {
            for (Disc disc : discs) {
                if (disc.getBarcode().equals(d.getBarcode())) {
                    return MediaServiceResult.CONFLICT;
                }
            }
            discs.add(d);
            System.out.println(d.toString());
            System.out.println("new disc size is: " + discs.size());
            return MediaServiceResult.OK;
        }
    }
    
    @Override
    public Medium[] getBooks() {
        System.out.println("Books size is: " + books.size());
        Book[] array = new Book[books.size()];
        books.toArray(array);
        for (Book b : array) {
            System.out.println(b.toString());
        }
        return array;
    }
    
    @Override
    public Medium[] getDiscs() {
        System.out.println("Discs size is: " + discs.size());
        Disc[] array = new Disc[discs.size()];
        discs.toArray(array);
        for (Disc d : array) {
            System.out.println(d.toString());
        }
        return array;
    }
    
    @Override
    public MediaServiceResult updateBook(String isbn, Book  b) {
        Book toUpdate = getBook(isbn);
        if (!searchBook(isbn)) {
            System.out.println(isbn);
            return MediaServiceResult.NOT_FOUND;
        }
        if (!(isbn.equals(b.getIsbn())) || ("".equals(b.getAuthor()) && "".equals(b.getTitle()))) {
            return MediaServiceResult.BAD_REQUEST;
        }
        toUpdate.setAuthor(b.getAuthor());
        toUpdate.setTitle(b.getTitle());
        return MediaServiceResult.OK;
    }
    
    /**
     * Returns true if the book is in the system.
     * @param isbn of the book
     * @return true if the book is in the system.
     */
    public boolean searchBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if the disc is in the system.
     * @param barcode of the disc
     * @return true if the disc is in the system.
     */
    public boolean searchDisc(String barcode) {
        for (Disc d : discs) {
            if (d.getBarcode().equals(barcode)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public MediaServiceResult updateDisc(String barcode, Disc d) {
        Disc toUpdate = getDisc(barcode);
        if (!searchDisc(barcode)) {
            return MediaServiceResult.NOT_FOUND;
        }
        if (!(barcode.equals(d.getBarcode())) || ("".equals(d.getDirector()) && "".equals(d.getTitle()))) {
            return MediaServiceResult.BAD_REQUEST;
        }
        toUpdate.setDirector(d.getDirector());
        toUpdate.setTitle(d.getTitle());
        toUpdate.setFsk(d.getFsk());
        return MediaServiceResult.OK;
    }
    
    @Override
    public Book getBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }
        return null;
    }
    
    @Override
    public Disc getDisc(String barcode) {
        for (Disc d : discs) {
            if (d.getBarcode().equals(barcode)) {
                return d;
            }
        }
        return null;
    }
}
