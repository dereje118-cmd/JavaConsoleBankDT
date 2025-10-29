package banking05단계;

/**
 * 보통예금계좌 클래스
 * - 기본이자율(%)을 가짐
 * - 입금 시 이자 계산 후 반영
 */
public class NormalAccount extends Account {
    protected int interest; // 기본이자율(%)

    public NormalAccount(String accountNum, String name, int balance, int interest) {
        super(accountNum, name, balance);
        this.interest = interest;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0 || amount % 500 != 0) {
            System.out.println("⚠ 입금은 500원 단위의 양수만 가능합니다.");
            return;
        }
        int interestMoney = (int) (balance * (interest / 100.0));
        balance += interestMoney + amount;
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0 || amount % 1000 != 0) {
            System.out.println("⚠ 출금은 1000원 단위의 양수만 가능합니다.");
            return;
        }
        if (balance < amount) {
            System.out.println("⚠ 잔고가 부족합니다. 금액 전체를 출금할까요? (YES/NO)");
            String ans = new java.util.Scanner(System.in).nextLine();
            if (ans.equalsIgnoreCase("YES")) {
                System.out.println(balance + "원이 출금되었습니다.");
                balance = 0;
            } else {
                System.out.println("출금이 취소되었습니다.");
            }
            return;
        }
        balance -= amount;
        System.out.println(">> 출금이 완료되었습니다.");
    }

    @Override
    public void showAccInfo() {
        System.out.println("[보통예금계좌]");
        super.showAccInfo();
        System.out.println("기본이자율: " + interest + "%");
        System.out.println("-------------------------");
    }

    public int getInterest() {
        return interest;
    }
}
