package banking04단계;

public class NormalAccount extends Account {
    private double interestRate; // 기본이자율

    public NormalAccount(String accountNumber, String name, int balance, double interestRate) {
        super(accountNumber, name, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("음수를 입금할 수 없습니다.");
            return;
        }

        if (amount % 500 != 0) {
            System.out.println("입금은 500원 단위로만 가능합니다.");
            return;
        }

        balance += (int)(balance * (interestRate / 100)) + amount;
        System.out.println("입금 완료. 현재 잔액: " + balance + "원");
    }
}
