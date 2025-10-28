package banking03단계;


public class NormalAccount extends Account {
    protected double interest;

    public NormalAccount(String accountNum, String name, int balance, double interest) {
        super(accountNum, name, balance);
        this.interest = interest;
    }

    @Override
    public void deposit(int amount) {
        int interestMoney = (int)(balance * interest / 100);
        balance = balance + interestMoney + amount;
    }

    @Override
    public void showAccInfo() {
        System.out.println("[보통예금계좌]");
        super.showAccInfo();
        System.out.println("기본이자율: " + interest + "%");
        System.out.println("---------------------------");
    }
}
