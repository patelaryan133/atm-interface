import java.util.Scanner;

class BankAccount {
    private double balance;

    BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    double getBalance() {
        return balance;
    }

    void credit(double amount) {
        balance += amount;
    }

    void debit(double amount) {
        balance -= amount;
    }
}

class ATM {
    private final BankAccount account;

    ATM(BankAccount account) {
        this.account = account;
    }

    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Must be greater than 0.");
        } else if (amount > account.getBalance()) {
            System.out.println("Transaction failed: insufficient balance.");
        } else {
            account.debit(amount);
            System.out.printf("Withdrawal successful. Please collect %.2f%n", amount);
        }
    }

    void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Must be greater than 0.");
        } else {
            account.credit(amount);
            System.out.printf("Deposit successful. %.2f added to your account.%n", amount);
        }
    }

    void checkBalance() {
        System.out.printf("Current balance: %.2f%n", account.getBalance());
    }

    void start() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== ATM =====");
            System.out.println("1. Withdraw\n2. Deposit\n3. Check Balance\n4. Exit");
            System.out.print("Choose an option: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid. Choose 1-4: ");
                sc.next();
            }
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    withdraw(readAmount(sc));
                    break;
                case 2:
                    deposit(readAmount(sc));
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid option. Choose 1-4.");
            }
        } while (choice != 4);
        sc.close();
    }

    private double readAmount(Scanner sc) {
        System.out.print("Enter amount: ");
        while (!sc.hasNextDouble()) {
            System.out.print("Invalid. Enter amount: ");
            sc.next();
        }
        return sc.nextDouble();
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        new ATM(new BankAccount(1000.00)).start();
    }
}
