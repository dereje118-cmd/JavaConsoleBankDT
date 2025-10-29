package banking04단계;

public class HighCreditAccount extends NormalAccount {
    private String creditGrade;

    public HighCreditAccount(String accountNumber, String name, int balance, double interestRate, String creditGrade) {
        super(accountNumber, name, balance, interestRate);
        this.creditGrade = creditGrade;
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

        double extraRate = switch (creditGrade.toUpperCase()) {
            case "A" -> 7.0;
            case "B" -> 4.0;
            case "C" -> 2.0;
            default -> 0.0;
        };

        balance += (int)(balance * ((superInterest() + extraRate) / 100)) + amount;
        System.out.println("입금 완료. 현재 잔액: " + balance + "원");
    }

    private double superInterest() {
        return 2.0; // 기본 이율 예시 (NormalAccount에 접근 가능하도록 별도 관리 가능)
    }
}
