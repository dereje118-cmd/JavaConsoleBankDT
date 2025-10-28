package banking05단계;

import java.io.Serializable;
import java.util.Objects;

// 추상 클래스 + 직렬화
public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String accNum;
    protected String name;
    protected int balance;

    public Account(String accNum, String name, int balance) {
        this.accNum = accNum;
        this.name = name;
        this.balance = balance;
    }

    public String getAccNum() {
        return accNum;
    }

    public abstract void deposit(int money);

    public void withdraw(int money) {
        balance -= money;
    }

    @Override
    public String toString() {
        return String.format("계좌번호:%s, 이름:%s, 잔액:%d", accNum, name, balance);
    }

    // HashSet 중복 판별용 - 계좌번호 기준
    @Override
    public int hashCode() {
        return Objects.hash(accNum);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account acc)
            return this.accNum.equals(acc.accNum);
        return false;
    }
}
