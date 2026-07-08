import java.util.HashMap;
import java.util.Map;

public class LibraryManager {
    private Map<Integer, Book> library;

    public LibraryManager() {
        this.library = new HashMap<>();
    }

    public void addBook(int id, String title, String author, String isbn) {
        library.put(id, new Book(id, title, author, isbn));
    }

    public boolean borrowBook(int id, String memberName) {
        if (library.containsKey(id)) {
            Book book = library.get(id);
            if (book.isAvailable) {
                book.isAvailable = false;
                book.borrowedBy = memberName;
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(int id) {
        if (library.containsKey(id)) {
            Book book = library.get(id);
            book.isAvailable = true;
            book.borrowedBy = null;
            return true;
        }
        return false;
    }

    public int countAvailable() {
        int count = 0;
        for (Book book : library.values()) {
            if (book.isAvailable) {
                count++;
            }
        }
        return count;
    }

    public void displayAll() {
        for (Book book : library.values()) {
            String status = book.isAvailable ? "Available" : "Borrowed by " + book.borrowedBy;
            System.out.println("ID: " + book.bookId + ", Title: " + book.title + 
                             ", Author: " + book.author + ", Status: " + status);
        }
    }
}
