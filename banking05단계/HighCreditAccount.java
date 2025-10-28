package banking05단계;

public class HighCreditAccount extends NormalAccount {
    private static final long serialVersionUID = 1L;
    private char grade; // A, B, C

    public HighCreditAccount(String accNum, String name, int balance, int interestRate, char grade) {
        super(accNum, name, balance, interestRate);
        this.grade = grade;
    }

    @Override
    public void deposit(int money) {
        int addRate = switch (grade) {
            case 'A' -> 7;
            case 'B' -> 4;
            case 'C' -> 2;
            default -> 0;
        };
        int totalInterest = (balance * (interestRate + addRate) / 100);
        balance += money + totalInterest;
    }

    @Override
    public String toString() {
        return "[신용신뢰계좌] " + super.toString() + ", 신용등급:" + grade;
    }
}
