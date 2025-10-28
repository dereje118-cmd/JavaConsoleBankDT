package banking06단계;

public class NormalAccount extends Account {
    protected int interest;

    public NormalAccount(String accNum, String name, int balance, int interest) {
        super(accNum, name, balance);
        this.interest = interest;
    }

    @Override
    public void deposit(int amount) {
        balance += balance * interest / 100 + amount;
    }
}
