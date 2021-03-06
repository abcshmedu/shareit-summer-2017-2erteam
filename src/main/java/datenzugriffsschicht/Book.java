package datenzugriffsschicht;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Represents a book as a Medium.
 * @author Altvatter Robert, Gro�beck Thomas
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Book extends Medium {
    
    @Id
    private String isbn;
    private String author;
    
    /**
     * Constructs a book as a medium.
     */
    public Book() {
        super("");
        author = "";
        isbn = "";
    }
    
    /**
     * Constructs a book with title, author and isbn.
     * @param title of the book
     * @param author of the book
     * @param isbn of the book
     */
    public Book(String title, String author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Book other = (Book) obj;
        if (author == null) {
            if (other.author != null) {
                return false;
            }
        } else if (!author.equals(other.author)) {
            return false;
        }
        if (isbn == null) {
            if (other.isbn != null) {
                return false;
            }
        } else if (!isbn.equals(other.isbn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", isbn=" + isbn + "]";
    }
    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }
    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }
    
    /**
     * Sets the author.
     * @param author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    
}
