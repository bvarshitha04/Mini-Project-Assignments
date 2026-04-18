public class Book {
    private int bookId;
    private String title;
    private String authorName;
    private boolean isCurrentlyIssued;

    public Book(int bookId, String title, String authorName) {
        this.bookId = bookId;
        this.title = title;
        this.authorName = authorName;
        this.isCurrentlyIssued = false;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthorName() { return authorName; }
    public boolean isIssued() { return isCurrentlyIssued; }
    
    public void setIssuedStatus(boolean status) {
        this.isCurrentlyIssued = status;
    }
}