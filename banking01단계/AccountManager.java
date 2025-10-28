package banking01단계;

import java.util.Scanner;

import banking02단계.MenuSelectException;

public class AccountManager implements MenuSelectException {

    private Account[] accArr = new Account[50]; // 최대 50개 계좌 저장
    private int numOfAcc = 0; // 현재 저장된 계좌 수
    private Scanner sc = new Scanner(System.in);

    // 메뉴 출력
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. 계좌개설");
            System.out.println("2. 입금");
            System.out.println("3. 출금");
            System.out.println("4. 전체계좌정보출력");
            System.out.println("5. 프로그램종료");
            System.out.print("선택: ");

            int choice = sc.nextInt();
            System.out.println();

            switch (choice) {
                case MAKE:
                    makeAccount();
                    break;
                case DEPOSIT:
                    depositMoney();
                    break;
                case WITHDRAW:
                    withdrawMoney();
                    break;
                case INQUIRE:
                    showAccInfo();
                    break;
                case EXIT:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    // 계좌 개설
    public void makeAccount() {
        System.out.print("계좌번호: ");
        String accNum = sc.next();
        System.out.print("이름: ");
        String name = sc.next();
        System.out.print("입금액: ");
        int balance = sc.nextInt();

        accArr[numOfAcc++] = new Account(accNum, name, balance);
        System.out.println(">> 계좌개설이 완료되었습니다.");
    }

    // 입금
    public void depositMoney() {
        System.out.print("계좌번호: ");
        String accNum = sc.next();
        System.out.print("입금액: ");
        int money = sc.nextInt();

        for (int i = 0; i < numOfAcc; i++) {
            if (accNum.equals(accArr[i].getAccountNum())) {
                accArr[i].deposit(money);
                System.out.println(">> 입금이 완료되었습니다.");
                return;
            }
        }
        System.out.println(">> 해당 계좌번호가 존재하지 않습니다.");
    }

    // 출금
    public void withdrawMoney() {
        System.out.print("계좌번호: ");
        String accNum = sc.next();
        System.out.print("출금액: ");
        int money = sc.nextInt();

        for (int i = 0; i < numOfAcc; i++) {
            if (accNum.equals(accArr[i].getAccountNum())) {
                accArr[i].withdraw(money);
                System.out.println(">> 출금이 완료되었습니다.");
                return;
            }
        }
        System.out.println(">> 해당 계좌번호가 존재하지 않습니다.");
    }

    // 전체 계좌정보 출력
    public void showAccInfo() {
        for (int i = 0; i < numOfAcc; i++) {
            accArr[i].showAccInfo();
        }
    }
}
