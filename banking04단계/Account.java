package banking04단계;

public abstract class Account {
    protected String accountNumber;
    protected String name;
    protected int balance;

    public Account(String accountNumber, String name, int balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    // 추상 메소드
    public abstract void deposit(int amount);

    public void withdraw(int amount) {
        if (amount < 0) {
            System.out.println("음수를 출금할 수 없습니다.");
            return;
        }

        if (amount % 1000 != 0) {
            System.out.println("출금은 1000원 단위로만 가능합니다.");
            return;
        }

        if (balance < amount) {
            System.out.println("잔고가 부족합니다. 금액 전체를 출금할까요? (Y/N)");
            java.util.Scanner sc = new java.util.Scanner(System.in);
            String sel = sc.nextLine();
            if (sel.equalsIgnoreCase("Y")) {
                amount = balance;
            } else {
                System.out.println("출금요청이 취소되었습니다.");
                return;
            }
        }

        balance -= amount;
        System.out.println("출금 완료. 현재 잔액: " + balance + "원");
    }

    public void showAccountInfo() {
        System.out.printf("계좌번호: %s, 이름: %s, 잔액: %d원%n", accountNumber, name, balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // HashSet 중복 방지용 equals, hashCode
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account acc) {
            return accountNumber.equals(acc.accountNumber);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }
}
