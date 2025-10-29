package banking05단계;

import java.io.Serializable;
import java.util.Objects;

/**
 * Account (추상클래스)
 * - 공통 속성(계좌번호, 이름, 잔액)
 * - 입금/출금의 기본 형식 정의
 * - 직렬화 가능
 */
public abstract class Account implements Serializable {
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

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public abstract void deposit(int amount);
    public abstract void withdraw(int amount);

    public void showAccInfo() {
        System.out.println("계좌번호: " + accountNum);
        System.out.println("이름: " + name);
        System.out.println("잔액: " + balance);
    }

    // HashSet 중복방지를 위한 equals, hashCode 재정의
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account)) return false;
        Account acc = (Account) obj;
        return accountNum.equals(acc.accountNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNum);
    }
}
