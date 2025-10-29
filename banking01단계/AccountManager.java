package banking01단계;

import java.util.Scanner;

public class AccountManager implements ICustomDefine {
    private Account[] accArr = new Account[50]; // 최대 50개의 계좌
    private int numOfAcc = 0;                   // 현재 저장된 계좌 수
    private Scanner sc = new Scanner(System.in);

    // 메뉴 출력 및 실행 루프
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. 계좌개설");
            System.out.println("2. 입금");
            System.out.println("3. 출금");
            System.out.println("4. 전체계좌정보출력");
            System.out.println("5. 프로그램종료");
            System.out.print("선택: ");

            int choice = Integer.parseInt(sc.nextLine());
            sc.nextLine(); //clear buffer
            
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
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    //1. 계좌 개설 -> create Account
    private void makeAccount() {
        System.out.print("계좌번호: ");
        String accNum = sc.nextLine();
        System.out.print("이름: ");
        String name = sc.nextLine();
        System.out.print("입금액: ");
        int balance = Integer.parseInt(sc.nextLine());

        accArr[numOfAcc++] = new Account(accNum, name, balance);
        System.out.println(">> 계좌개설이 완료되었습니다.");
    }

    //2. 입금 -> Deposite Money
    private void depositMoney() {
        System.out.print("계좌번호: ");
        String accNum = sc.nextLine();
        System.out.print("입금액: ");
        int money = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numOfAcc; i++) {
            if (accArr[i].getAccountNum().equals(accNum)) {
                accArr[i].deposit(money);
                System.out.println(">> 입금이 완료되었습니다.");
                return;
            }
        }
        System.out.println(">> 계좌를 찾을 수 없습니다.");
    }

    //3. 출금 -> Withdraw Money
    private void withdrawMoney() {
        System.out.print("계좌번호: ");
        String accNum = sc.nextLine();
        System.out.print("출금액: ");
        int money = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numOfAcc; i++) {
            if (accArr[i].getAccountNum().equals(accNum)) {
                accArr[i].withdraw(money);
                System.out.println(">> 출금이 완료되었습니다.");
                return;
            }
        }
        System.out.println(">> 계좌를 찾을 수 없습니다.");
    }

    //4. 전체 계좌정보 출력 -> Show All Account Info
    private void showAccInfo() {
        System.out.println("\n[전체 계좌정보 출력]");
        for (int i = 0; i < numOfAcc; i++) {
            accArr[i].showAccInfo();
        }
    }
}
