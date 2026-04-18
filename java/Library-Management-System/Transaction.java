import java.time.LocalDate;

public class Transaction {
    private int transactionId;
    private int bookId;
    private int userId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Transaction(int transactionId, int bookId, int userId) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.userId = userId;
        this.issueDate = LocalDate.now(); // Issued today
        this.dueDate = this.issueDate.plusDays(14); // 14 days borrow period
        this.returnDate = null; // Has not been returned yet
    }

    public int getTransactionId() { return transactionId; }
    public int getBookId() { return bookId; }
    public int getUserId() { return userId; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    
    // Call this method when the user brings the book back
    public void markAsReturned() {
        this.returnDate = LocalDate.now();
    }
}
