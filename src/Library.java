import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Library {
    public static Map<String, CustomQueue<String>> booksToBeBorrowed = new HashMap<>();
    public static PriorityQueue<User> priorityBooks = new PriorityQueue<>(new UserComparator());
    public static Map<String, ArrayList<User>> booksToBeBorrowedWithPriority = new HashMap<>();

    public Library() {
        String name = "Decagon Library";
    }

    public static String borrowBook(String requestedBook, CustomQueue<String> user) {
        booksToBeBorrowed.put(requestedBook, user);
        return requestedBook;
    }

    public static String borrowBookWithPriority(String requestedBook, ArrayList<User> user) {
        booksToBeBorrowedWithPriority.put(requestedBook, user);
        return requestedBook;
    }
}
