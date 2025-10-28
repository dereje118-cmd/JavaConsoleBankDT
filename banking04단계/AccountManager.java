package banking04단계;


import java.util.*;

interface MenuChoice {
    int MAKE = 1, DEPOSIT = 2, WITHDRAW = 3, INQUIRE = 4, DELETE = 5, EXIT = 6;
}

public class AccountManager implements MenuChoice {
    private Set<Account> accSet = new HashSet<>();
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n=== 계좌관리 프로그램 ===");
            System.out.println("1. 계좌개설");
            System.out.println("2. 입금");
            System.out.println("3. 출금");
            System.out.println("4. 전체계좌정보출력");
            System.out.println("5. 계좌삭제");
            System.out.println("6. 프로그램종료");
            System.out.print("선택: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case MAKE -> makeAccount();
                    case DEPOSIT -> depositMoney();
                    case WITHDRAW -> withdrawMoney();
                    case INQUIRE -> showAccInfo();
                    case DELETE -> deleteAccount();
                    case EXIT -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    default -> throw new InputMismatchException("잘못된 번호입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력 가능합니다.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void makeAccount() {
        System.out.println("[계좌개설]");
        System.out.print("1. 보통계좌 | 2. 신용계좌 선택: ");
        int sel = Integer.parseInt(sc.nextLine());

        System.out.print("계좌번호: ");
        String accNum = sc.nextLine();
        System.out.print("이름: ");
        String name = sc.nextLine();
        System.out.print("잔액: ");
        int balance = Integer.parseInt(sc.nextLine());
        System.out.print("기본이자율(%): ");
        int interest = Integer.parseInt(sc.nextLine());

        Account newAcc = null;

        if (sel == 1) {
            newAcc = new NormalAccount(accNum, name, balance, interest);
        } else {
            System.out.print("신용등급(A, B, C): ");
            char grade = sc.nextLine().toUpperCase().charAt(0);
            newAcc = new HighCreditAccount(accNum, name, balance, interest, grade);
        }

        if (!accSet.add(newAcc)) {
            System.out.print("중복계좌발견됨. 덮어쓸까요?(y/n): ");
            String ans = sc.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                accSet.remove(newAcc);
                accSet.add(newAcc);
                System.out.println("덮어쓰기 완료.");
            } else {
                System.out.println("기존 정보 유지.");
            }
        } else {
            System.out.println("계좌개설 완료!");
        }
    }

    private Account findAccount(String accNum) {
        for (Account acc : accSet) {
            if (acc.getAccNum().equals(accNum)) return acc;
        }
        return null;
    }

    private void depositMoney() {
        System.out.print("입금할 계좌번호: ");
        String accNum = sc.nextLine();
        Account acc = findAccount(accNum);

        if (acc == null) {
            System.out.println("계좌를 찾을 수 없습니다.");
            return;
        }

        System.out.print("입금금액: ");
        int money = Integer.parseInt(sc.nextLine());

        if (money < 0 || money % 500 != 0) {
            System.out.println("입금은 0원 이상, 500원 단위로만 가능합니다.");
            return;
        }

        acc.deposit(money);
        System.out.println("입금완료.");
    }

    private void withdrawMoney() {
        System.out.print("출금할 계좌번호: ");
        String accNum = sc.nextLine();
        Account acc = findAccount(accNum);

        if (acc == null) {
            System.out.println("계좌를 찾을 수 없습니다.");
            return;
        }

        System.out.print("출금금액: ");
        int money = Integer.parseInt(sc.nextLine());

        if (money < 0 || money % 1000 != 0) {
            System.out.println("출금은 0원 이상, 1000원 단위로만 가능합니다.");
            return;
        }

        if (acc.balance < money) {
            System.out.print("잔고가 부족합니다. 금액전체를 출금할까요?(y/n): ");
            String ans = sc.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                acc.withdraw(acc.balance);
                System.out.println("전체 금액 출금 완료.");
            } else {
                System.out.println("출금 취소.");
            }
        } else {
            acc.withdraw(money);
            System.out.println("출금 완료.");
        }
    }

    private void showAccInfo() {
        System.out.println("\n[전체 계좌정보]");
        for (Account acc : accSet) {
            System.out.println(acc);
        }
    }

    private void deleteAccount() {
        System.out.print("삭제할 계좌번호: ");
        String accNum = sc.nextLine();
        Account acc = findAccount(accNum);

        if (acc == null) {
            System.out.println("해당 계좌가 없습니다.");
            return;
        }

        accSet.remove(acc);
        System.out.println("계좌가 삭제되었습니다.");
    }
}
