package banking02단계;

public class NormalAccount extends Account {
    private int interestRate; // base interest rate (e.g., 2%)

    public NormalAccount(String accNumber, String name, int balance, int interestRate) {
        super(accNumber, name, balance);
        this.interestRate = interestRate;
    }

    // Override deposit to include interest calculation
    @Override
    public void deposit(int amount) {
        int interest = (balance * interestRate) / 100;
        balance += interest + amount;
    }

    @Override
    public void showAccInfo() {
        System.out.println("[Normal Account]");
        super.showAccInfo();
        System.out.println("Base Interest Rate: " + interestRate + "%");
        System.out.println();
    }
}
