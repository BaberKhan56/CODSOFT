import java.util.Scanner;

/*
 * Class representing the user's bank account
 * It stores and manages the account balance
 */
class BankAccount {
    private double balance;

    // Constructor to initialize account balance
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
        } else {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        }
    }

    // Method to check current balance
    public double getBalance() {
        return balance;
    }
}

/*
 * Class representing the ATM Machine
 * It provides menu options to the user
 */
class ATM {
    private BankAccount account;
    private Scanner sc = new Scanner(System.in);

    // Constructor to connect ATM with BankAccount
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display ATM menu
    public void showMenu() {
        int choice;

        do {
            System.out.println("\n------ ATM MENU ------");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 3:
                    System.out.println("Current Balance: ₹" + account.getBalance());
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);
    }
}

/*
 * Main class to run the ATM Machine program
 */
public class ATMMachine {
    public static void main(String[] args) {
        // Create a bank account with initial balance
        BankAccount userAccount = new BankAccount(5000);

        // Create ATM object and start menu
        ATM atm = new ATM(userAccount);
        atm.showMenu();
    }
}
