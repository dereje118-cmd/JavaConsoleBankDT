package banking03단계;

import java.io.Serializable;

// Abstract class for inheritance only
public abstract class Account implements Serializable {
    protected String accNumber;
    protected String name;
    protected int balance;

    public Account(String accNumber, String name, int balance) {
        this.accNumber = accNumber;
        this.name = name;
        this.balance = balance;
    }

    // Abstract deposit method to be implemented differently in each subclass
    public abstract void deposit(int amount);

    // Common withdrawal logic (can be overridden if needed)
    public void withdraw(int amount) {
        balance -= amount;
    }

    public void showAccInfo() {
        System.out.println("Account Number: " + accNumber);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
        System.out.println("---------------------------");
    }

    public String getAccNumber() {
        return accNumber;
    }

    public int getBalance() {
        return balance;
    }
}
