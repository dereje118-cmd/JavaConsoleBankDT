package banking05단계;

public class NormalAccount extends Account {
    private static final long serialVersionUID = 1L;
    protected int interestRate;

    public NormalAccount(String accNum, String name, int balance, int interestRate) {
        super(accNum, name, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(int money) {
        balance += money + (balance * interestRate / 100);
    }

    @Override
    public String toString() {
        return "[보통예금계좌] " + super.toString() + ", 기본이자:" + interestRate + "%";
    }
}
