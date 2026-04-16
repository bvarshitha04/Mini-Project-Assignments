import java.util.ArrayList;

public class Account {

    private String accountHolder;
    private double balance;
    private ArrayList<Double> history = new ArrayList<>();

    Account(String name, double balance) {
        this.accountHolder = name;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid deposit amount");
        }
        balance += amount;
        addToHistory(amount);
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Not enough balance");
        }

        balance -= amount;
        addToHistory(-amount);
        System.out.println("Withdrawn: " + amount);
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    private void addToHistory(double amount) {
        if (history.size() == 5) {
            history.remove(0);
        }
        history.add(amount);
    }

    public void printMiniStatement() {
        System.out.println("Last Transactions:");
        for (double t : history) {
            System.out.println(t);
        }
    }
}