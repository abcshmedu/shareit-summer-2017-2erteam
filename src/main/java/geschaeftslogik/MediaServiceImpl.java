package geschaeftslogik;

import datenzugriffsschicht.Book;
import datenzugriffsschicht.Disc;
import datenzugriffsschicht.Medium;

public class MediaServiceImpl implements MediaService {
    public MediaServiceImpl(){}
    public MediaServiceResult addBook(Book b){
        return MediaServiceResult.FEHLER;
    }
    public MediaServiceResult addDisc(Disc d){
        return MediaServiceResult.FEHLER;
    }
    public Medium[] getBooks(){
        return null;
    }
    public Medium[] getDiscs(){
        return null;
    }
    public MediaServiceResult updateBook(Book  b){
        return null;
    }
    public MediaServiceResult updateDisc(Disc d){
        return null;
    }
}
