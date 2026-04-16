import java.util.ArrayList;

public class Library {
    ArrayList<Book> books = new ArrayList<>();

    void addBook(Book b) {
        books.add(b);
        System.out.println("Book added");
    }

    void showBooks() {
        for (Book b : books) {
            System.out.println(b.id + " " + b.title + " " + b.author + " Issued: " + b.isIssued);
        }
    }

    void issueBook(int id) {
        for (Book b : books) {
            if (b.id == id && !b.isIssued) {
                b.isIssued = true;
                System.out.println("Book issued");
                return;
            }
        }
        System.out.println("Book not available");
    }

    void returnBook(int id, int lateDays) {
        for (Book b : books) {
            if (b.id == id && b.isIssued) {
                b.isIssued = false;

                if (lateDays > 0) {
                    int fine = lateDays * 5;
                    System.out.println("Fine: " + fine);
                }

                System.out.println("Book returned");
                return;
            }
        }
        System.out.println("Invalid return");
    }

    void searchBook(String title) {
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                System.out.println("Found: " + b.title);
                return;
            }
        }
        System.out.println("Not found");
    }
}