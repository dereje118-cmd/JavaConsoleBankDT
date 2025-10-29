package banking02단계;

import java.util.Scanner;

public class AccountManager implements ICustomDefine {
    private Account[] accArray = new Account[50];
    private int numOfAccounts = 0;
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show All Accounts");
            System.out.println("5. Exit");
            System.out.print("Select: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case MAKE: makeAccount(); break;
                case DEPOSIT: depositMoney(); break;
                case WITHDRAW: withdrawMoney(); break;
                case INQUIRE: showAccInfo(); break;
                case EXIT:
                    System.out.println("Program terminated.");
                    return;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    public void makeAccount() {
        System.out.println("\n[Select Account Type]");
        System.out.println("1. Normal Account");
        System.out.println("2. High Credit Account");
        System.out.print("Select: ");
        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("Account Number: ");
        String accNumber = sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Initial Deposit: ");
        int balance = sc.nextInt();

        System.out.print("Base Interest Rate (%): ");
        int interestRate = sc.nextInt();

        if (type == 1) {
            accArray[numOfAccounts++] = new NormalAccount(accNumber, name, balance, interestRate);
            System.out.println("Normal Account Created!");
        } else if (type == 2) {
            System.out.print("Credit Grade (A/B/C): ");
            char grade = sc.next().toUpperCase().charAt(0);

            int creditRate = 0;
            switch (grade) {
                case 'A': creditRate = A; break;
                case 'B': creditRate = B; break;
                case 'C': creditRate = C; break;
                default:
                    System.out.println("Invalid grade. Defaulted to C.");
                    creditRate = C;
            }

            accArray[numOfAccounts++] = new HighCreditAccount(accNumber, name, balance, interestRate, creditRate);
            System.out.println("High Credit Account Created!");
        }
    }

    public void depositMoney() {
        sc.nextLine();
        System.out.print("Enter Account Number: ");
        String accNumber = sc.nextLine();

        System.out.print("Deposit Amount: ");
        int amount = sc.nextInt();

        for (int i = 0; i < numOfAccounts; i++) {
            if (accArray[i].getAccNumber().equals(accNumber)) {
                accArray[i].deposit(amount);
                System.out.println("Deposit completed!");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void withdrawMoney() {
        sc.nextLine();
        System.out.print("Enter Account Number: ");
        String accNumber = sc.nextLine();

        System.out.print("Withdraw Amount: ");
        int amount = sc.nextInt();

        for (int i = 0; i < numOfAccounts; i++) {
            if (accArray[i].getAccNumber().equals(accNumber)) {
                accArray[i].withdraw(amount);
                System.out.println("Withdrawal completed!");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void showAccInfo() {
        for (int i = 0; i < numOfAccounts; i++) {
            accArray[i].showAccInfo();
        }
    }
}
