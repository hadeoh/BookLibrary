import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryTest {

    @BeforeEach
    public void setUp() {
        User teacher = new Teacher(1, "usman", "adio@gmail.com", new Date());
        User.allUsers.put("adio@gmail.com", teacher);
        Book newBook = new Book(1, "Hamlet", "Adio", "Usman", 2);
        Book.allBooks.put("Hamlet", newBook);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void borrowBook() {
        CustomQueue<String> newBorrower = new CustomQueue<>();
        newBorrower.add("adio@gmail.com");
        String requestedBook = "Hamlet";
        Library.booksToBeBorrowed.put(requestedBook, newBorrower);
        assertEquals("Hamlet", requestedBook);
    }

    @Test
    public void borrowBookWithPriority() {
        ArrayList<User> user = new ArrayList<>();
        String requestedBook = "Hamlet";
        Library.booksToBeBorrowedWithPriority.put("Hamlet", user);
        assertEquals("Hamlet", requestedBook);
    }
}