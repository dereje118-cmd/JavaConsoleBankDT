package banking03단계;


import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager implements MenuChoice {

    private Account[] accArr = new Account[50];
    private int numOfAcc = 0;
    private Scanner sc = new Scanner(System.in);

    // 메뉴 출력
    public void showMenu() {
        while (true) {
            try {
                System.out.println("\n=== 계좌관리 프로그램 ===");
                System.out.println("1. 계좌개설");
                System.out.println("2. 입금");
                System.out.println("3. 출금");
                System.out.println("4. 전체계좌정보출력");
                System.out.println("5. 프로그램종료");
                System.out.print("선택: ");

                int choice = sc.nextInt();
                if (choice < MAKE || choice > EXIT) {
                    throw new MenuSelectException("메뉴 선택 범위를 벗어났습니다.");
                }

                switch (choice) {
                    case MAKE: makeAccount(); break;
                    case DEPOSIT: depositMoney(); break;
                    case WITHDRAW: withdrawMoney(); break;
                    case INQUIRE: showAccInfo(); break;
                    case EXIT:
                        System.out.println("프로그램을 종료합니다.");
                        return;
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠ 숫자를 입력해야 합니다!");
                sc.nextLine(); // 잘못된 입력 버퍼 비우기
            } catch (MenuSelectException e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }

    // 계좌 개설
    public void makeAccount() {
        System.out.println("1. 보통예금계좌  2. 신용신뢰계좌");
        System.out.print("선택: ");
        int choice = sc.nextInt();

        System.out.print("계좌번호: ");
        String accNum = sc.next();
        System.out.print("이름: ");
        String name = sc.next();
        System.out.print("입금액: ");
        int balance = sc.nextInt();
        System.out.print("기본이자율(%): ");
        double interest = sc.nextDouble();

        if (choice == 1) {
            accArr[numOfAcc++] = new NormalAccount(accNum, name, balance, interest);
        } else if (choice == 2) {
            System.out.print("신용등급(A,B,C): ");
            String grade = sc.next();
            accArr[numOfAcc++] = new HighCreditAccount(accNum, name, balance, interest, grade);
        }

        System.out.println(">> 계좌가 개설되었습니다.");
    }

    // 입금
    public void depositMoney() {
        try {
            System.out.print("계좌번호: ");
            String accNum = sc.next();
            System.out.print("입금액: ");
            int money = sc.nextInt();

            if (money <= 0) {
                System.out.println("⚠ 음수나 0원은 입금할 수 없습니다.");
                return;
            }

            if (money % 500 != 0) {
                System.out.println("⚠ 입금은 500원 단위로만 가능합니다.");
                return;
            }

            for (int i = 0; i < numOfAcc; i++) {
                if (accNum.equals(accArr[i].getAccountNum())) {
                    accArr[i].deposit(money);
                    System.out.println(">> 입금이 완료되었습니다.");
                    return;
                }
            }
            System.out.println("⚠ 해당 계좌번호가 존재하지 않습니다.");
        } catch (InputMismatchException e) {
            System.out.println("⚠ 숫자만 입력 가능합니다!");
            sc.nextLine();
        }
    }

    // 출금
    public void withdrawMoney() {
        try {
            System.out.print("계좌번호: ");
            String accNum = sc.next();
            System.out.print("출금액: ");
            int money = sc.nextInt();

            if (money <= 0) {
                System.out.println("⚠ 음수나 0원은 출금할 수 없습니다.");
                return;
            }

            if (money % 1000 != 0) {
                System.out.println("⚠ 출금은 1000원 단위로만 가능합니다.");
                return;
            }

            for (int i = 0; i < numOfAcc; i++) {
                if (accNum.equals(accArr[i].getAccountNum())) {
                    if (money > accArr[i].balance) {
                        System.out.println("잔고가 부족합니다. 금액전체를 출금할까요? (YES / NO)");
                        String ans = sc.next();

                        if (ans.equalsIgnoreCase("YES")) {
                            System.out.println(accArr[i].balance + "원이 출금되었습니다.");
                            accArr[i].withdraw(accArr[i].balance);
                        } else {
                            System.out.println("출금이 취소되었습니다.");
                        }
                        return;
                    }

                    accArr[i].withdraw(money);
                    System.out.println(">> 출금이 완료되었습니다.");
                    return;
                }
            }
            System.out.println("⚠ 해당 계좌번호가 존재하지 않습니다.");
        } catch (InputMismatchException e) {
            System.out.println("⚠ 숫자만 입력 가능합니다!");
            sc.nextLine();
        }
    }

    // 전체계좌정보출력
    public void showAccInfo() {
        for (int i = 0; i < numOfAcc; i++) {
            accArr[i].showAccInfo();
        }
    }
}
