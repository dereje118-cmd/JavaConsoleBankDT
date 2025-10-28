package banking02단계;

public class NormalAccount extends Account {
    private double interest; // 기본 이자율

    public NormalAccount(String accountNum, String name, int balance, double interest) {
        super(accountNum, name, balance);
        this.setInterest(interest);
    }

    @Override
    public void deposit(int amount) {
        // 이자 계산: 잔고 + (잔고 * 기본이자율) + 입금액
        int interestMoney = (int)(balance * getInterest() / 100);
        balance = balance + interestMoney + amount;
    }

    @Override
    public void showAccInfo() {
        System.out.println("[보통예금계좌]");
        super.showAccInfo();
        System.out.println("기본이자율: " + getInterest() + "%");
        System.out.println("---------------------------");
    }

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}
}
