import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Library {
    private ArrayList<Book> availableBooks = new ArrayList<>();
    private ArrayList<User> registeredUsers = new ArrayList<>();
    private ArrayList<Transaction> transactionHistory = new ArrayList<>();
    private int nextTransactionId = 1;

    // ==========================================
    // Book Management Methods
    // ==========================================
    
    public void addNewBook(Book newBook) {
        availableBooks.add(newBook);
        System.out.println("\n[SUCCESS] Book '" + newBook.getTitle() + "' has been added to the library.");
    }

    public void removeBook(int bookId) {
        for (int i = 0; i < availableBooks.size(); i++) {
            if (availableBooks.get(i).getBookId() == bookId) {
                System.out.println("\n[SUCCESS] Book '" + availableBooks.get(i).getTitle() + "' removed.");
                availableBooks.remove(i);
                return;
            }
        }
        System.out.println("\n[ERROR] Could not find a book with ID " + bookId);
    }

    public void displayAllBooks() {
        if (availableBooks.isEmpty()) {
            System.out.println("\n[INFO] The library currently has no books.");
            return;
        }
        System.out.println("\n--- Library Book List ---");
        for (Book book : availableBooks) {
            String status = book.isIssued() ? "[Borrowed]" : "[Available]";
            System.out.println("ID: " + book.getBookId() + " | Title: " + book.getTitle() + " | Author: " + book.getAuthorName() + " | Status: " + status);
        }
    }

    // ==========================================
    // User Management Methods
    // ==========================================
    
    public void registerNewUser(User newUser) {
        registeredUsers.add(newUser);
        System.out.println("\n[SUCCESS] User '" + newUser.getFullName() + "' has been registered.");
    }

    // ==========================================
    // Transaction Methods (Issue / Return)
    // ==========================================
    
    public void borrowBook(int bookId, int userId) {
        // 1. Check if the book exists and is available
        Book foundBook = null;
        for (Book book : availableBooks) {
            if (book.getBookId() == bookId) {
                foundBook = book;
                break;
            }
        }

        if (foundBook == null) {
            System.out.println("\n[ERROR] Book with ID " + bookId + " does not exist.");
            return;
        }
        
        if (foundBook.isIssued()) {
            System.out.println("\n[ERROR] Sorry, '" + foundBook.getTitle() + "' is already borrowed by someone else.");
            return;
        }

        // 2. Check if the user is registered
        boolean userExists = false;
        for (User user : registeredUsers) {
            if (user.getUserId() == userId) {
                userExists = true;
                break;
            }
        }

        if (!userExists) {
            System.out.println("\n[ERROR] User with ID " + userId + " is not registered.");
            return;
        }

        // 3. Process the borrowing
        foundBook.setIssuedStatus(true);
        Transaction newTransaction = new Transaction(nextTransactionId++, bookId, userId);
        transactionHistory.add(newTransaction);
        
        System.out.println("\n[SUCCESS] '" + foundBook.getTitle() + "' has been issued to User ID " + userId);
        System.out.println("[INFO] Please return it by: " + newTransaction.getDueDate());
    }

    public void returnBook(int bookId, int userId) {
        // 1. Find the active transaction for this book and user
        Transaction activeTransaction = null;
        for (Transaction transaction : transactionHistory) {
            if (transaction.getBookId() == bookId && transaction.getUserId() == userId && transaction.getReturnDate() == null) {
                activeTransaction = transaction;
                break;
            }
        }

        if (activeTransaction == null) {
            System.out.println("\n[ERROR] No active borrow record found for Book ID " + bookId + " and User ID " + userId);
            return;
        }

        // 2. Update the book's status back to available
        for (Book book : availableBooks) {
            if (book.getBookId() == bookId) {
                book.setIssuedStatus(false);
                break;
            }
        }

        // 3. Finalize transaction and calculate any fine
        activeTransaction.markAsReturned();
        System.out.println("\n[SUCCESS] Book returned safely.");

        long daysLate = ChronoUnit.DAYS.between(activeTransaction.getDueDate(), activeTransaction.getReturnDate());
        if (daysLate > 0) {
            long totalFine = daysLate * 5; // $5 fine per day late
            System.out.println("[NOTICE] Book is " + daysLate + " days late. Please collect a fine of $" + totalFine);
        } else {
            System.out.println("[INFO] Thank you for returning the book on time!");
        }
    }

    // ==========================================
    // Search Features
    // ==========================================
    
    public void searchBooksByTitle(String searchTitle) {
        boolean found = false;
        System.out.println("\n--- Search Results for Title: '" + searchTitle + "' ---");
        for (Book book : availableBooks) {
            if (book.getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
                System.out.println("ID: " + book.getBookId() + " | Title: " + book.getTitle() + " | Author: " + book.getAuthorName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching title: " + searchTitle);
        }
    }

    public void searchBooksByAuthor(String searchAuthor) {
        boolean found = false;
        System.out.println("\n--- Search Results for Author: '" + searchAuthor + "' ---");
        for (Book book : availableBooks) {
            if (book.getAuthorName().toLowerCase().contains(searchAuthor.toLowerCase())) {
                System.out.println("ID: " + book.getBookId() + " | Title: " + book.getTitle() + " | Author: " + book.getAuthorName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found by author: " + searchAuthor);
        }
    }
}