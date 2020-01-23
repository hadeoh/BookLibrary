import java.util.HashMap;
import java.util.Map;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int quantity;
    public static Map<String, Book> allBooks = new HashMap<>();

    public Book(int id, String title, String author, String publisher, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static Book addBook(String title, Book book) {
        allBooks.put(title, book);
        return book;
    }
}
