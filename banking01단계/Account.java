package banking01단계;

public class Account {
    private String accountNum; // 계좌번호
    private String name;       // 고객명
    private int balance;       // 잔액

    public Account(String accountNum, String name, int balance) {
        this.accountNum = accountNum;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void showAccInfo() {
        System.out.println("계좌번호: " + accountNum);
        System.out.println("이름: " + name);
        System.out.println("잔고: " + balance);
        System.out.println("-------------------------");
    }
}
