package geschaeftslogik;
import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;
import datenzugriffsschicht.Medium;

public interface MediaService {
    MediaServiceResult addBook(Book b);
    MediaServiceResult addDisc(Disc d);
    Medium[] getBooks();
    Medium[] getDiscs();
    MediaServiceResult updateBook(Book b);
    MediaServiceResult updateDisc(Disc d);
}
