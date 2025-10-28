package banking02단계;

public class HighCreditAccount extends NormalAccount {
    private String creditGrade; // 신용등급 (A, B, C)
    private double addInterest; // 추가이율

    public HighCreditAccount(String accountNum, String name, int balance, double interest, String grade) {
        super(accountNum, name, balance, interest);
        this.creditGrade = grade.toUpperCase();

        switch (creditGrade) {
            case "A":
                addInterest = 7.0;
                break;
            case "B":
                addInterest = 4.0;
                break;
            case "C":
                addInterest = 2.0;
                break;
            default:
                addInterest = 0.0;
        }
    }

    @Override
    public void deposit(int amount) {
        // 신용계좌: 잔고 + (잔고 * 기본이자) + (잔고 * 추가이자) + 입금액
        int baseInterest = (int)(balance * super.getInterest() / 100);
        int extraInterest = (int)(balance * addInterest / 100);
        balance = balance + baseInterest + extraInterest + amount;
    }

    @Override
    public void showAccInfo() {
        System.out.println("[신용신뢰계좌]");
        super.showAccInfo();
        System.out.println("신용등급: " + creditGrade);
        System.out.println("추가이자율: " + addInterest + "%");
        System.out.println("---------------------------");
    }
}
