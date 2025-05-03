package ATM;

import java.util.ArrayList;
import java.util.Scanner;
class UserAccount {
    private String username;
    private String password;
    private double balance;
    private ArrayList<String> transactionHistory;
    public UserAccount(String username, String password, double initialBalance) {
        this.username = username;
        this.password = password;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: " + initialBalance);
    }
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
    }
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient Balance!");
        }
    }
    public void viewTransactions() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
    public void calculateInterest(double rate, int years) {
        double interest = (balance * rate * years) / 100;
        System.out.println("Interest after " + years + " years at " + rate + "% rate: " + interest);
    }
    public double getBalance() {
        return balance;
    }
}
public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Step 1: Account Creation
        System.out.println("Welcome to ATM Machine!");
        System.out.print("Create username: ");
        String username = scanner.nextLine();
        System.out.print("Create password: ");
        String password = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double initialBalance = scanner.nextDouble();       
        UserAccount user = new UserAccount(username, password, initialBalance);
        // Step 2: Login
        System.out.println("\nLogin to your account:");
        scanner.nextLine(); // consume newline
        System.out.print("Username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Password: ");
        String loginPassword = scanner.nextLine();
        if (user.authenticate(loginUsername, loginPassword)) {
            System.out.println("Login Successful!");
            // Step 3: Operations
            int choice;
            do {
                System.out.println("\nATM Menu:");
                System.out.println("1. Deposit Money");
                System.out.println("2. Withdraw Money");
                System.out.println("3. View Balance");
                System.out.println("4. View Transaction History");
                System.out.println("5. Calculate Interest");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        user.deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        user.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.println("Current Balance: " + user.getBalance());
                        break;
                    case 4:
                        user.viewTransactions();
                        break;
                    case 5:
                        System.out.print("Enter interest rate: ");
                        double rate = scanner.nextDouble();
                        System.out.print("Enter number of years: ");
                        int years = scanner.nextInt();
                        user.calculateInterest(rate, years);
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 6);
        } else {
            System.out.println("Invalid username or password. Access Denied!");
        }
        scanner.close();
    }
}