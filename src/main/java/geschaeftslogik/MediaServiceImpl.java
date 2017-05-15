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
    
    public static final int ISBN_LENGTH = 13;
    
    /**
     * Constructs a MediaService instance.
     */
    public MediaServiceImpl() {
        if (books == null) {
            books = new ArrayList<Book>();
            books.add(new Book("a", "a", "978-1-4028-9462-6"));
            books.add(new Book("b", "b", "978-3-16-148410-0"));
        }
        if (discs == null) {
            discs = new ArrayList<Disc>();
            discs.add(new Disc("TestTitel", "9781402894626", 0, "TestDirector"));
            discs.add(new Disc("TestTitel2", "9783161484100", 0, "TestDirector"));
        }
    }
    
    @Override
    public MediaServiceResult addBook(Book b) {
        if (!testCode(b.getIsbn()) || "".equals(b.getAuthor()) || "".equals(b.getTitle())) {
            return MediaServiceResult.BAD_REQUEST;
        }  
        else {
            for (Book book : books) {
                if (book.getIsbn().equals(b.getIsbn())) {
                    return MediaServiceResult.CONFLICT;
                }
            }
            b.setIsbn(b.getIsbn().replaceAll("-", ""));
            books.add(b);
            System.out.println(b.toString());
            System.out.println("new books size is: " + books.size());
            return MediaServiceResult.OK;
        }
    }
    
    @Override
    public MediaServiceResult addDisc(Disc d) {
        if (!testCode(d.getBarcode()) || "".equals(d.getDirector()) || "".equals(d.getTitle())) {
            return MediaServiceResult.BAD_REQUEST;
        }
        else {
            for (Disc disc : discs) {
                if (disc.getBarcode().equals(d.getBarcode())) {
                    return MediaServiceResult.CONFLICT;
                }
            }
            d.setBarcode(d.getBarcode().replaceAll("-",""));
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
        if (!b.getAuthor().isEmpty()) {
            toUpdate.setAuthor(b.getAuthor());
        }
        if (!b.getTitle().isEmpty()) {
            toUpdate.setTitle(b.getTitle());
        }
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
        if (!(barcode.equals(d.getBarcode())) || ("".equals(d.getDirector()) && "".equals(d.getTitle()) && (d.getFsk() == -1))) {
            return MediaServiceResult.BAD_REQUEST;
        }
        if (!d.getDirector().isEmpty()) {
            toUpdate.setDirector(d.getDirector());
        }
        if (!d.getTitle().isEmpty()) {
            toUpdate.setTitle(d.getTitle());
        }
        if (d.getFsk() != -1) {
            toUpdate.setFsk(d.getFsk());
        }
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
    
    /**
     * Checks if there is a valid isbn number.
     * @param toTest isbn or barcode to test
     * @return true if the code is valid
     */
    public boolean testCode(String toTest) {
        toTest = toTest.replace("-", "");
        if (toTest == null || toTest.length() != ISBN_LENGTH) {
            return false;
        }
        ArrayList<Integer> ints = convString(toTest);
        //CHECKSTYLE:OFF
        int result = (ints.get(0) + ints.get(2) + ints.get(4) + ints.get(6) + ints.get(8) + ints.get(10) + ints.get(12) + 3 * (ints.get(1) + ints.get(3) + ints.get(5) + ints.get(7) + ints.get(9) + ints.get(11))) % 10;
      //CHECKSTYLE:ON
        if (result == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Converts String.
     * @param s string
     * @return list
     */
    public ArrayList<Integer> convString(String s) {
        ArrayList<Integer> help = new ArrayList<>();
        for (char c: s.toCharArray()) {
            help.add(Character.getNumericValue(c));
        }
        return help;
    }
}
