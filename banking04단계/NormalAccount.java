package banking04단계;

public class NormalAccount extends Account {
    private int interestRate;

    public NormalAccount(String accNum, String name, int balance, int interestRate) {
        super(accNum, name, balance);
        this.setInterestRate(interestRate);
    }

    @Override
    public void deposit(int money) {
        balance += money + (balance * getInterestRate() / 100);
    }

    @Override
    public String toString() {
        return "[보통예금계좌] " + super.toString() + ", 기본이자:" + getInterestRate() + "%";
    }

	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}
}
