public class Main {
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();

        library.addBook(1, "Java Programming", "Herbert Schildt", "ISBN001");
        library.addBook(2, "Data Structures", "Mark Allen Weiss", "ISBN002");
        library.addBook(3, "Algorithms", "Sanjoy Dasgupta", "ISBN003");
        library.addBook(4, "Clean Code", "Robert C. Martin", "ISBN004");

        System.out.println("Library Status:");
        library.displayAll();

        System.out.println("\nAvailable Books: " + library.countAvailable());

        library.borrowBook(1, "John");
        library.borrowBook(3, "Sarah");

        System.out.println("\nAfter borrowing:");
        library.displayAll();

        System.out.println("\nAvailable Books: " + library.countAvailable());

        library.returnBook(1);

        System.out.println("\nAfter return:");
        System.out.println("Available Books: " + library.countAvailable());
    }
}
