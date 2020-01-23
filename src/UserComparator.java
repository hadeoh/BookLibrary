import java.util.Comparator;

public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        type user1 = o1.getUserType();
        type user2 = o2.getUserType();

        if (user1.compareTo(user2) > 0) {
            return -1;
        } else if (user1.compareTo(user2) < 0) {
            return 1;
        }
        return 0;
    }
}
