import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum type {
    juniorStudent,
    seniorStudent,
    teacher,
    librarian
}

public abstract class User {
    private int id;
    private String name;
    private String email;
    private type userType;
    private Date createdAt;
    public static Map<String, User> allUsers = new HashMap<>();

    public User(int id, String name, String email, type userType, Date createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public type getUserType() {
        return userType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public static User createUser(String email, User user) {
        allUsers.put(email, user);
        return null;
    }

    public static User deleteUser(String email) {
        if (!allUsers.containsKey(email)) {
            System.out.println("User does not exist");
        } else {
            allUsers.remove(email);
            System.out.println("User successfully deleted");
        }
        return null;
    }

    public static boolean loginUser(String email) {
        if (!allUsers.containsKey(email)) {
            System.out.println("User not found");
            return false;
        } else {
            System.out.println("Welcome " + allUsers.get(email).getName());
            LoginUser.setUserEmail(email);
            return true;
        }
    }

    public static void listOfUsers() {
        System.out.println("There are " + allUsers.size() + " users who use the Library");

        Iterator<Map.Entry<String, User>> iterator = allUsers.entrySet().iterator();
        System.out.println("\tS/N \t\tName \t\t\tUser Type");
        while (iterator.hasNext()) {
            Map.Entry<String, User> entry = iterator.next();
            System.out.println("\t" + entry.getValue().getId() + " \t\t" + entry.getValue().getName().toUpperCase() + "\t\t\t " + entry.getValue().getUserType().toString().toUpperCase());
        }
    }
}
