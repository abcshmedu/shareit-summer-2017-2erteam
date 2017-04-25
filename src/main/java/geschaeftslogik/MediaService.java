package geschaeftslogik;
import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;
import datenzugriffsschicht.Medium;

/**
 * This interface represents the functionalities of the service.
 * @author Altvatter Robert, Groﬂbeck Thomas
 *
 */
public interface MediaService {

    /**
     * Adds a book to the service.
     * @param b book
     * @return result status
     */
    MediaServiceResult addBook(Book b);
    
    /**
     * Adds a disc to the service.
     * @param d disc
     * @return result status
     */
    MediaServiceResult addDisc(Disc d);
    
    /**
     * Lists all books.
     * @return list with all books
     */
    Medium[] getBooks();
    
    /**
     * Lists all discs.
     * @return list with all discs
     */
    Medium[] getDiscs();
    
    /**
     * Updates the attributes of a book.
     * @param isbn of the book
     * @param b book
     * @return result status
     */
    MediaServiceResult updateBook(String isbn, Book b);
    
    /**
     * Updates the attributes of a disc.
     * @param barcode of the disc
     * @param d disc
     * @return result status
     */
    MediaServiceResult updateDisc(String barcode, Disc d);
    
    /**
     * Lists a specific book.
     * @param isbn of the book
     * @return the book
     */
    Book getBook(String isbn);
    
    /**
     * Lists a specific disc.
     * @param barcode of the disc
     * @return the disc
     */
    Disc getDisc(String barcode);
}
