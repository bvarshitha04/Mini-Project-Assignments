import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        int choice;

        do {
            System.out.println("\n1 Add Book");
            System.out.println("2 Show Books");
            System.out.println("3 Issue Book");
            System.out.println("4 Return Book");
            System.out.println("5 Search Book");
            System.out.println("6 Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    System.out.println("Enter id:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter title:");
                    String title = sc.nextLine();

                    System.out.println("Enter author:");
                    String author = sc.nextLine();

                    lib.addBook(new Book(id, title, author));
                    break;

                case 2:
                    lib.showBooks();
                    break;

                case 3:
                    System.out.println("Enter book id:");
                    int issueId = sc.nextInt();
                    lib.issueBook(issueId);
                    break;

                case 4:
                    System.out.println("Enter book id:");
                    int returnId = sc.nextInt();

                    System.out.println("Enter days late:");
                    int late = sc.nextInt();

                    lib.returnBook(returnId, late);
                    break;

                case 5:
                    sc.nextLine();
                    System.out.println("Enter title:");
                    String search = sc.nextLine();
                    lib.searchBook(search);
                    break;

                case 6:
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while(choice != 6);

        sc.close();
    }
}