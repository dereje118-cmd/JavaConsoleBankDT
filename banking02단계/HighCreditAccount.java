package banking02단계;

public class HighCreditAccount extends NormalAccount {
    private int creditRate; // extra interest for credit grade

    public HighCreditAccount(String accNumber, String name, int balance, int interestRate, int creditRate) {
        super(accNumber, name, balance, interestRate);
        this.creditRate = creditRate;
    }

    // Override deposit to include both base and extra credit interest
    @Override
    public void deposit(int amount) {
        int interest = (balance * (super.interestRate + creditRate)) / 100;
        balance += interest + amount;
    }

    @Override
    public void showAccInfo() {
        System.out.println("[High Credit Account]");
        super.showAccInfo();
        System.out.println("Credit Grade Bonus: " + creditRate + "%");
        System.out.println();
    }
}
