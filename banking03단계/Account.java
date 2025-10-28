package banking03단계;

public abstract class Account {
    protected String accountNum;
    protected String name;
    protected int balance;

    public Account(String accountNum, String name, int balance) {
        this.accountNum = accountNum;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public abstract void deposit(int amount); // 추상 메서드로 변경

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
