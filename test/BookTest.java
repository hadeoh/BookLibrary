import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;

public class BookTest {
    @BeforeEach
    public void setUp() {
        User librarian = new Librarian(1, "usman", "usman@gmail.com", new Date());
        User.allUsers.put("adio@gmail.com", librarian);
        Book newBook = new Book(1, "Hamlet", "Adio", "Usman", 2);
        Book.allBooks.put("Hamlet", newBook);
    }

    @Test
    public void addBookTest() {

    }
}