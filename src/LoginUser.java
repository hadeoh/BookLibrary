import java.util.Scanner;

public class LoginUser {
    public static String userEmail;

    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        LoginUser.userEmail = userEmail;
    }

    public static void loginUser() throws Exception {
        System.out.println("Enter your email address to login\r");
        String email = scanner.nextLine();
        boolean exit = false;
        boolean existingUser = User.loginUser(email);

        while (!exit) {
            if (!existingUser) {
                System.out.println("Incorrect email address");
                return;
            } else {
                exit = true;

            }
        }
        printInstructionToUser();
    }

    public static void printInstructionToUser() throws Exception {
        int choice = 0;
        boolean exit = false;

        while(!exit) {
            System.out.println("Enter your choice: (1 to show all available options)");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    LoginUser.setUserEmail(null);
                    exit = true;
                    break;
                case 1:
                    Main.printInstructions();
                    break;
                case 2:
                    User.listOfUsers();
                    break;
                case 3:
                    Main.listBooks();
                    break;
                case 4:
                    Main.borrowBookWithPriority();
                    break;
                case 5:
                    Main.deleteUser();
                    break;
                case 6:
                    Main.addBook();
                    break;
                case 7:
                    Main.deleteBook();
                    break;
                case 8:
                    Main.approveBookBasedOnPriority();
                    break;
                default:
                    System.out.println("You have not chosen any option");
                    Main.printInstructions();
                    break;
            }
        }
    }
}
