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
    public MediaServiceResult updateBook(String isbn, Book  b){
        return null;
    }
    public MediaServiceResult updateDisc(String barcode, Disc d){
        return null;
    }
    public Book getBook(String isbn) {
    	return null;
    }
    public Disc getDisc(String barcode) {
    	return null;
    }
}
