import java.util.Date;

public class Librarian extends User {

    public Librarian(int id, String name, String email, Date createdAt) {
        super(id, name, email, type.librarian, createdAt);
    }
}
