package banking02단계;

public class Account {
    protected String accountNum; // 계좌번호
    protected String name;       // 이름
    protected int balance;       // 잔액

    public Account(String accountNum, String name, int balance) {
        this.accountNum = accountNum;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void deposit(int amount) {
        balance += amount;  // 기본 계좌는 이자 계산 없음
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void showAccInfo() {
        System.out.println("계좌번호: " + accountNum);
        System.out.println("이름: " + name);
        System.out.println("잔액: " + balance);
        System.out.println("---------------------------");
    }
}
