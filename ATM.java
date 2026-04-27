import java.util.*;

// Bank Account Class
class BankAccount {
    private double balance;
    private List<String> history;

    private static final double MAX_LIMIT = 100000; // 1 lakh limit

    public BankAccount(double balance) {
        this.balance = balance;
        history = new ArrayList<>();
    }

    // Deposit Method
    public String deposit(double amount) {
        if (amount <= 0) {
            return " Invalid amount!";
        } else if (amount > MAX_LIMIT) {
            return " Deposit limit exceeded! Max Rs. 1,00,000 allowed.";
        } else {
            balance += amount;

            if (history.size() == 5) {
                history.remove(0);
            }
            history.add("Deposited Rs. " + amount);

            return "Successfully deposited Rs. " + amount;
        }
    }

    // Withdraw Method
    public String withdraw(double amount) {
        if (amount <= 0) {
            return " Invalid amount!";
        } else if (amount > MAX_LIMIT) {
            return " Withdrawal limit exceeded! Max Rs. 1,00,000 allowed.";
        } else if (amount > 5000) {
            return " Limit exceeded! Max Rs. 5000 per transaction.";
        } else if (amount > balance) {
            return "Insufficient balance!";
        } else {
            balance -= amount;

            if (history.size() == 5) {
                history.remove(0);
            }
            history.add("Withdrawn Rs. " + amount);

            return "Successfully withdrawn Rs. " + amount;
        }
    }

    // Check Balance
    public String checkBalance() {
        return "Current Balance: Rs. " + String.format("%.2f", balance);
    }

    // Transaction History
    public void showHistory() {
        System.out.println("\n====== Transaction History ======");
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String h : history) {
                System.out.println(h);
            }
        }
    }
}

// ATM Class
public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount(1000);

        int correctPin = 1234;
        int attempts = 0;

        System.out.println("====== Welcome to ATM System ======");

        // PIN Authentication
        while (attempts < 3) {
            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();

            if (pin == correctPin) {
                System.out.println("Login successful!");
                break;
            } else {
                attempts++;
                System.out.println(" Wrong PIN!");
            }
        }

        if (attempts == 3) {
            System.out.println("Too many attempts. Card blocked.");
            return;
        }

        int choice;

        do {
            System.out.println("\n====== ATM MENU ======");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println(account.checkBalance());
                    break;

                case 2:
                    System.out.print("Enter amount: ");
                    double depositAmount = sc.nextDouble();
                    System.out.println(account.deposit(depositAmount));
                    break;

                case 3:
                    System.out.print("Enter amount: ");
                    double withdrawAmount = sc.nextDouble();
                    System.out.println(account.withdraw(withdrawAmount));
                    break;

                case 4:
                    account.showHistory();
                    break;

                case 5:
                    System.out.println("Thank you for using ATM!");
                    break;

                default:
                    System.out.println(" Invalid choice!");
            }

        } while (choice != 5);

        System.out.println("Session ended successfully.");
        sc.close();
    }
}