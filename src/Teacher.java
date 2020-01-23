import java.util.Date;

public class Teacher extends User {

    public Teacher(int id, String name, String email, Date createdAt) {
        super(id, name, email, type.teacher, createdAt);
    }
}
