package banking05단계;

/**
 * 신용신뢰계좌 클래스
 * - 기본이자율 + 신용등급별 추가이자율 적용
 * - A: 7%, B: 4%, C: 2%
 */
public class HighCreditAccount extends NormalAccount {
    private String grade;

    public HighCreditAccount(String accountNum, String name, int balance, int interest, String grade) {
        super(accountNum, name, balance, interest);
        this.grade = grade.toUpperCase();
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0 || amount % 500 != 0) {
            System.out.println("⚠ 입금은 500원 단위의 양수만 가능합니다.");
            return;
        }

        int addRate = 0;
        switch (grade) {
            case "A": addRate = ICustomDefine.A; break;
            case "B": addRate = ICustomDefine.B; break;
            case "C": addRate = ICustomDefine.C; break;
            default:
                System.out.println("⚠ 잘못된 신용등급입니다.");
        }

        int interestMoney = (int)(balance * ((interest + addRate) / 100.0));
        balance += interestMoney + amount;
    }

    @Override
    public void showAccInfo() {
        System.out.println("[신용신뢰계좌]");
        super.showAccInfo();
        System.out.println("신용등급: " + grade);
        System.out.println("-------------------------");
    }
}
