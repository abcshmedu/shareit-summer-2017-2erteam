package datenzugriffsschicht;

public class Book extends Medium{
    String author;
    String isbn;
    public Book() {
        super("");
    }
    public Book(String title, String author, String isbn){
        super(title);
        this.author = author;
        this.isbn = isbn;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
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
    
}
