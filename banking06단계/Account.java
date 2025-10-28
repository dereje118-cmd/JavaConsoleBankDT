package banking06단계;

import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Serializable {
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

    public abstract void deposit(int amount);

    public void withdraw(int amount) {
        if(amount > balance) balance = 0;
        else balance -= amount;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Account other = (Account) obj;
        return Objects.equals(accNum, other.accNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accNum);
    }

    @Override
    public String toString() {
        return accNum + " | " + name + " | " + balance;
    }
}
