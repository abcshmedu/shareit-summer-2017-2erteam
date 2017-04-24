package geschaeftslogik;
import datenzugriffsschicht.*;
import api_schicht.*;
public interface MediaService {
    MediaServiceResult addBook(Book b);
    MediaServiceResult addDisc(Disc d);
    Medium[] getBooks();
    Medium[] getDiscs();
    MediaServiceResult updateBook(Book b);
    MediaServiceResult updateDisc(Disc d);
}
