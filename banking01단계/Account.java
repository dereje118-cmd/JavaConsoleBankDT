package banking01단계;

public class Account {
    private String accountNum;  // 계좌번호
    private String name;        // 예금주 이름
    private int balance;        // 잔액
    
    
    //constructor
    public Account(String accountNum, String name, int balance) {
        this.accountNum = accountNum;
        this.name = name;
        this.balance = balance;
    }
    
    //Getters
    public String getAccountNum() {
        return accountNum;
    }
    
    //Deposit
    public void deposit(int amount) {
        balance += amount;
    }

    	//Withdraw
    public void withdraw(int amount) {
        balance -= amount;
    }

    	//Display account info
    public void showAccInfo() {
        System.out.println("계좌번호: " + accountNum);
        System.out.println("이름: " + name);
        System.out.println("잔액: " + balance);
        System.out.println("-------------------------");
    }
}
