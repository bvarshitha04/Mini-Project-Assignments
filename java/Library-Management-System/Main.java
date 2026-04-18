import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Library myLibrary = new Library();
        int userChoice = 0;

        System.out.println("=========================================");
        System.out.println("Welcome to the Library Management System!");
        System.out.println("=========================================");

        while (userChoice != 9) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Register a New User");
            System.out.println("2. Add a New Book");
            System.out.println("3. Remove a Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Borrow a Book");
            System.out.println("6. Return a Book");
            System.out.println("7. Search Books by Title");
            System.out.println("8. Search Books by Author");
            System.out.println("9. Exit System");
            System.out.print("Please select an option (1-9): ");

            if (inputScanner.hasNextInt()) {
                userChoice = inputScanner.nextInt();
                inputScanner.nextLine(); // Clear the newline character
            } else {
                System.out.println("\n[ERROR] Invalid input. Please enter a number.");
                inputScanner.nextLine();
                continue;
            }

            switch (userChoice) {
                case 1:
                    System.out.print("Enter new User ID: ");
                    int newUserId = inputScanner.nextInt();
                    inputScanner.nextLine();
                    System.out.print("Enter User's Full Name: ");
                    String fullName = inputScanner.nextLine();
                    System.out.print("Enter User's Contact Number: ");
                    String contactNumber = inputScanner.nextLine();
                    myLibrary.registerNewUser(new User(newUserId, fullName, contactNumber));
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    int newBookId = inputScanner.nextInt();
                    inputScanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String bookTitle = inputScanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String bookAuthor = inputScanner.nextLine();
                    myLibrary.addNewBook(new Book(newBookId, bookTitle, bookAuthor));
                    break;

                case 3:
                    System.out.print("Enter the ID of the Book to remove: ");
                    int removeBookId = inputScanner.nextInt();
                    myLibrary.removeBook(removeBookId);
                    break;

                case 4:
                    myLibrary.displayAllBooks();
                    break;

                case 5:
                    System.out.print("Enter User ID borrowing the book: ");
                    int borrowingUserId = inputScanner.nextInt();
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowBookId = inputScanner.nextInt();
                    myLibrary.borrowBook(borrowBookId, borrowingUserId);
                    break;

                case 6:
                    System.out.print("Enter User ID returning the book: ");
                    int returningUserId = inputScanner.nextInt();
                    System.out.print("Enter Book ID to return: ");
                    int returnBookId = inputScanner.nextInt();
                    myLibrary.returnBook(returnBookId, returningUserId);
                    break;

                case 7:
                    System.out.print("Enter keyword for Title search: ");
                    String searchTitle = inputScanner.nextLine();
                    myLibrary.searchBooksByTitle(searchTitle);
                    break;

                case 8:
                    System.out.print("Enter keyword for Author search: ");
                    String searchAuthor = inputScanner.nextLine();
                    myLibrary.searchBooksByAuthor(searchAuthor);
                    break;

                case 9:
                    System.out.println("\nExiting the Library Management System. Goodbye!");
                    break;

                default:
                    System.out.println("\n[ERROR] Invalid option. Please try again.");
            }
        }

        inputScanner.close();
    }
}