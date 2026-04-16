import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter account holder name:");
        String name = sc.nextLine();

        System.out.println("Enter initial balance:");
        double balance = sc.nextDouble();

        Account acc = new Account(name, balance);

        int choice;

        do {
            System.out.println("\n1 Deposit");
            System.out.println("2 Withdraw");
            System.out.println("3 Check Balance");
            System.out.println("4 Mini Statement");
            System.out.println("5 Exit");

            choice = sc.nextInt();

            try {
                switch(choice) {

                    case 1:
                        System.out.println("Enter amount:");
                        double d = sc.nextDouble();
                        acc.deposit(d);
                        break;

                    case 2:
                        System.out.println("Enter amount:");
                        double w = sc.nextDouble();
                        acc.withdraw(w);
                        break;

                    case 3:
                        acc.checkBalance();
                        break;

                    case 4:
                        acc.printMiniStatement();
                        break;

                    case 5:
                        System.out.println("Thank you");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while(choice != 5);

        sc.close();
    }
}