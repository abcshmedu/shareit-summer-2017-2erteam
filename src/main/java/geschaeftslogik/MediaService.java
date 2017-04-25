package geschaeftslogik;
import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;
import datenzugriffsschicht.Medium;

public interface MediaService {
    MediaServiceResult addBook(Book b);
    MediaServiceResult addDisc(Disc d);
    Medium[] getBooks();
    Medium[] getDiscs();
    MediaServiceResult updateBook(String isbn, Book b);
    MediaServiceResult updateDisc(String barcode, Disc d);
    Book getBook(String isbn);
    Disc getDisc(String barcode);
}
