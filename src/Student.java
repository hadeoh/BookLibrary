import java.util.Date;
import java.util.Scanner;

public class Student extends User{

    private static final Scanner scanner = new Scanner(System.in);

    public Student(int id, String name, String email, type userType, Date createdAt) {
        super(id, name, email, userType, createdAt);
    }
}
