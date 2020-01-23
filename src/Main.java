import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        boolean quit = false;
        int answer = 0;
        openLibrary();
        while(!quit) {
            promptInstructions();
            answer = scanner.nextInt();
            scanner.nextLine();
            switch (answer) {
                case 1:
                    LoginUser.loginUser();
                    break;
                case 2:
                    createUser();
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }
    }

    public static void promptInstructions() {
        System.out.println("Press 1 to login" +
                "\nPress 2 to sign up if you don't have an account" +
                "\nPress 3 to exit the application\n");
    }

    public static void openLibrary() {
        System.out.println("Welcome to Decagon Book Library");
    }

    public static void printInstructions() {
        System.out.println("\nPress ");
        System.out.println("\t 0 - Logout");
        System.out.println("\t 1 - See all available options");
        System.out.println("\t 2 - View all users");
        System.out.println("\t 3 - View all books");
        System.out.println("\t 4 - Borrow a book");
        System.out.println("\t 5 - Delete a user (specific to Librarians");
        System.out.println("\t 6 - Add a book (specific to Librarians)");
        System.out.println("\t 7 - Remove a book (specific to Librarians");
        System.out.println("\t 8 - Approve a book request (specific to Librarians");
    }

    public static void createUser() throws Exception {
        int count = User.allUsers.size() +1;
        System.out.println("Are you a Librarian, Teacher or Student?\r");
        String userType = scanner.nextLine().toLowerCase();
        System.out.println("Enter your email address\r");
        String email = scanner.nextLine();
        boolean isExist = User.allUsers.containsKey(email);
        if (isExist) {
            System.out.println("Email already exists");
            return;
        } else {
            System.out.println("Enter your name\r");
            String name = scanner.nextLine();
            if (userType.equals("student")) {
                System.out.println("Are you a junior or senior student?\r");
                String studentType = scanner.nextLine().toLowerCase();
                if (studentType.equals("junior")) {
                    Student student = new Student(count, name, email, type.juniorStudent, new Date());
                    User.createUser(email, student);
                } else {
                    Student student = new Student(count, email, email, type.seniorStudent, new Date());
                    User.createUser(email, student);
                }
            } else if (userType.equals("teacher")) {
                Teacher teacher = new Teacher(count, name, email, new Date());
                User.createUser(email, teacher);
            } else {
                Librarian librarian = new Librarian(count, name, email, new Date());
                User.createUser(email, librarian);
            }
        }
        LoginUser.loginUser();
    }

    public static void addBook() {
        int bookCount = Book.allBooks.size();
        String loggedInUser = LoginUser.getUserEmail();
        if (User.allUsers.get(loggedInUser).getUserType().equals(type.librarian)) {
            System.out.println("What's the title of the book to added?\r");
            String bookTitle = scanner.nextLine().toLowerCase();
            Book existingBookName = Book.allBooks.get(bookTitle);
            if (existingBookName != null) {
                int qty = existingBookName.getQuantity();
                existingBookName.setQuantity(qty+1);
            } else {
                System.out.println("Who's the author of the book?\r");
                String bookAuthor = scanner.nextLine();
                System.out.println("Who's the publisher of the book?\r");
                String bookPublisher = scanner.nextLine();
                Book newBook = new Book(bookCount+1, bookTitle, bookAuthor, bookPublisher, 1);
                Book.addBook(bookTitle, newBook);
            }
            listBooks();
        } else {
            System.out.println("Only a Librarian can perform this action");
        }
    }

    public static boolean borrowBook() {
        String loggedInUser = LoginUser.getUserEmail();
        System.out.println("Enter the title of the book you want to borrow\r");
        String requestedBook = scanner.nextLine().toLowerCase();
        if (Book.allBooks.containsKey(requestedBook)) {
            if (Library.booksToBeBorrowed.size() == 0) {
                return addMemberToQueue(loggedInUser, requestedBook);
            }
            if (Library.booksToBeBorrowed.containsKey(requestedBook)) {
                Object[] allQueueMembers = Library.booksToBeBorrowed.get(requestedBook).getAllQueueMembers();
                for (Object allQueueMember : allQueueMembers) {
                    if (allQueueMember == loggedInUser) {
                        System.out.println("You are already on the waiting list");
                        return false;
                    }
                }
                if (Book.allBooks.get(requestedBook).getQuantity() != 0) {
                    Library.booksToBeBorrowed.get(requestedBook).add(loggedInUser);
                    int oldQty = Book.allBooks.get(requestedBook).getQuantity();
                    Book.allBooks.get(requestedBook).setQuantity(oldQty - 1);
                    System.out.println("You have been added to the waiting list");
                    System.out.println(requestedBook +": " + Library.booksToBeBorrowed.get(requestedBook).printQueue());
                    return true;
                } else {
                    System.out.println("Book taken");
                    return false;
                }
            } else {
                return addMemberToQueue(loggedInUser, requestedBook);
            }
        } else {
            System.out.println("This book is currently not available in our Library");
            return false;
        }
    }

    public static boolean borrowBookWithPriority() {
        String loggedInUser = LoginUser.getUserEmail();
        User newBorrower = User.allUsers.get(loggedInUser);
        System.out.println("Enter the title of the book you want to borrow\r");
        String requestedBook = scanner.nextLine().toLowerCase();
        if (Book.allBooks.containsKey(requestedBook)) {
            if (Library.booksToBeBorrowedWithPriority.size() == 0) {
                return addMemberToPriorityQueue(newBorrower, requestedBook);
            }
            if (Library.booksToBeBorrowedWithPriority.containsKey(requestedBook)) {
                for (User user : Library.booksToBeBorrowedWithPriority.get(requestedBook)) {
                    if(user.getEmail().equals(loggedInUser)) {
                        System.out.println("You are already on the waiting list");
                        return false;
                    }
                }
                if (Book.allBooks.get(requestedBook).getQuantity() != 0) {
                    Library.booksToBeBorrowedWithPriority.get(requestedBook).add(newBorrower);
                    int oldQty = Book.allBooks.get(requestedBook).getQuantity();
                    Book.allBooks.get(requestedBook).setQuantity(oldQty - 1);
                    System.out.println("You have been added to the waiting list");
                    System.out.println(requestedBook +": " + Library.booksToBeBorrowedWithPriority.get(requestedBook).toString());
                    return true;
                } else {
                    System.out.println("Book taken");
                    return false;
                }
            } else {
                return addMemberToPriorityQueue(newBorrower, requestedBook);
            }
        } else {
            System.out.println("This book is currently not available in our Library");
            return false;
        }
    }

    private static boolean addMemberToPriorityQueue(User newBorrower, String requestedBook) {
        int oldQty = Book.allBooks.get(requestedBook).getQuantity();
        Book.allBooks.get(requestedBook).setQuantity(oldQty - 1);
        ArrayList<User> listArray = new ArrayList<>();
        listArray.add(newBorrower);
        Library.borrowBookWithPriority(requestedBook, listArray);
        System.out.println("You have been added to the waiting list");
        System.out.println(requestedBook +": " + Library.booksToBeBorrowedWithPriority.get(requestedBook).toString());
        return true;
    }

    public static boolean approveBook() throws Exception {
        String loggedInUser = LoginUser.getUserEmail();
        if (User.allUsers.get(loggedInUser).getUserType().equals(type.librarian)) {
            Iterator<Map.Entry<String, CustomQueue<String>>> bookRequestIterator = Library.booksToBeBorrowed.entrySet().iterator();
            System.out.println("\tS/N \t\tBook Title \t\t\tRequester");
            while (bookRequestIterator.hasNext()) {
                int count = 1;
                Map.Entry<String, CustomQueue<String>> entry = bookRequestIterator.next();
                System.out.println("\t" + count + "\t\t" + entry.getKey() + "\t\t\t\t" + entry.getValue().toString());
            }
            System.out.println("Do you want to approve based on custom or priority queue?(C/P)\r");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("c")) {
                System.out.println("Which of the books do you want to approve request for?\r");
                String ans = scanner.nextLine();
                if(Library.booksToBeBorrowed.get(ans) != null) {
                    String bookCollector = Library.booksToBeBorrowed.get(ans).remove();
                    System.out.println("The book was given out to " + bookCollector);
                    return true;
                } else {
                    System.out.println("There is no such book in the queue");
                    return false;
                }
            } else {
                System.out.println("I am a priority queue");
            }
            return true;
        } else {
            System.out.println("Only a Librarian can perform this action");
            return false;
        }
    }

    public static boolean approveBookBasedOnPriority(){
        String loggedInUser = LoginUser.getUserEmail();
        if (User.allUsers.get(loggedInUser).getUserType().equals(type.librarian)) {
            Iterator<Map.Entry<String, ArrayList<User>>> bookRequestIterator = Library.booksToBeBorrowedWithPriority.entrySet().iterator();
            System.out.println("\tS/N \t\tBook Title \t\t\tRequests");
            while (bookRequestIterator.hasNext()) {
                int count = 1;
                Map.Entry<String, ArrayList<User>> entry = bookRequestIterator.next();
                System.out.println("\t" + count + "\t\t" + entry.getKey() + "\t\t\t\t" + entry.getValue().toString());
            }
            System.out.println("Do you want to approve based on custom or priority queue?(C/P)\r");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("c")) {
                System.out.println("I am a custom queue");
            } else if (answer.equalsIgnoreCase("p")){
                System.out.println("Which of the books do you want to approve request for?\r");
                String ans = scanner.nextLine();
                if(Library.booksToBeBorrowedWithPriority.get(ans) != null) {
                    Library.priorityBooks.addAll(Library.booksToBeBorrowedWithPriority.get(ans));
                    User bookCollector = Objects.requireNonNull(Library.priorityBooks.poll());
                    System.out.println("The book was given out to " + bookCollector.getUserType() + " " + bookCollector.getEmail());
                    return true;
                } else {
                    System.out.println("There is no such book in the queue");
                    return false;
                }
            } else {
                System.out.println("You have not chosen an invalid key");
                return false;
            }
            return true;
        } else {
            System.out.println("Only a Librarian can perform this action");
            return false;
        }
    }

    public static boolean deleteBook() {
        String loggedInUser = LoginUser.getUserEmail();
        if (User.allUsers.get(loggedInUser).getUserType().equals(type.librarian)) {
            System.out.println("What's the title of the book you want to delete?\r");
            String bookTitle = scanner.nextLine().toLowerCase();
            Book existingBookName = Book.allBooks.get(bookTitle);
            if (existingBookName != null) {
                if (existingBookName.getQuantity() == 0) {
                    System.out.println("There are no " + existingBookName.getTitle() + " books in the Library presently");
                    return false;
                }
                System.out.println("There are " + existingBookName.getQuantity() + " books in the library");
                System.out.println("Do you want to delete all the " + existingBookName.getTitle() + "books");
                System.out.println("\nIf YES press Y, else press the number of books you want to delete");
                String answer = scanner.nextLine();
                int qty = existingBookName.getQuantity();
                if (answer.equalsIgnoreCase("y")) {
                    existingBookName.setQuantity(0);
                    System.out.println("You have deleted all the " + existingBookName.getTitle() + " books from the Library");
                } else {
                    existingBookName.setQuantity(qty - Integer.parseInt(answer));
                    System.out.println("You have deleted " + Integer.parseInt(answer) + existingBookName.getTitle() + " books from the Library\n" +
                            "There are now " +existingBookName.getQuantity() + " books in the Library");
                    return true;
                }
            } else {
                System.out.println("There's no such book in the Library");
                return true;
            }
        } else {
            System.out.println("Only a Librarian can perform this action");
            return false;
        }
        return false;
    }

    private static boolean addMemberToQueue(String loggedInUser, String requestedBook) {
        int oldQty = Book.allBooks.get(requestedBook).getQuantity();
        Book.allBooks.get(requestedBook).setQuantity(oldQty - 1);
        CustomQueue<String> newBorrower = new CustomQueue<>();
        newBorrower.add(loggedInUser);
        Library.booksToBeBorrowed.put(requestedBook, newBorrower);
        System.out.println("You have been added to the waiting list");
        System.out.println(requestedBook +": " + Library.booksToBeBorrowed.get(requestedBook).printQueue());
        return true;
    }

    public static int listBooks() {

        System.out.println("There are " + Book.allBooks.size() + " book(s) in the library");
        Iterator<Map.Entry<String, Book>> bookIterator = Book.allBooks.entrySet().iterator();
        System.out.println("\tS/N \t\tBook Title \t\t\tQuantity of books");
        while (bookIterator.hasNext()) {
            Map.Entry<String, Book> entry = bookIterator.next();
            System.out.println("\t" + entry.getValue().getId() + "\t\t" + entry.getValue().getTitle().toUpperCase() + "\t\t\t\t" + entry.getValue().getQuantity());
        }
        return Book.allBooks.size();
    }

    public static void deleteUser() {
        String loggedInUser = LoginUser.getUserEmail();
        if (User.allUsers.get(loggedInUser).getUserType().equals(type.librarian)) {
            System.out.println("Enter the email of the user to be deleted\r");
            String email = scanner.nextLine();
            User.deleteUser(email);
        } else {
            System.out.println("Only a Librarian can perform this action");
        }
    }
}
