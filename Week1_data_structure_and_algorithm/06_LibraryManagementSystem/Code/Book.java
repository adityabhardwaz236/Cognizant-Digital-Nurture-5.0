public class Book {
    public int bookId;
    public String title;
    public String author;
    public String isbn;
    public boolean isAvailable;
    public String borrowedBy;

    public Book(int id, String title, String author, String isbn) {
        this.bookId = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
        this.borrowedBy = null;
    }
}
