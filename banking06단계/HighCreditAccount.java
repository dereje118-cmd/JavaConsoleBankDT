package banking06단계;

public class HighCreditAccount extends NormalAccount {
    private char creditGrade;

    public HighCreditAccount(String accNum, String name, int balance, int interest, char creditGrade) {
        super(accNum, name, balance, interest);
        this.creditGrade = creditGrade;
    }

    @Override
    public void deposit(int amount) {
        int extra = 0;
        switch (creditGrade) {
            case 'A': extra = 7; break;
            case 'B': extra = 4; break;
            case 'C': extra = 2; break;
        }
        balance += balance * (interest + extra) / 100 + amount;
    }
}
