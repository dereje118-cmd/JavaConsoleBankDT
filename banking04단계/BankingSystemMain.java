package banking04단계;

import java.util.Scanner;

public class BankingSystemMain {
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            manager.showMenu();

            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case AccountManager.Menu.MAKE -> manager.makeAccount();
                    case AccountManager.Menu.DEPOSIT -> manager.depositMoney();
                    case AccountManager.Menu.WITHDRAW -> manager.withdrawMoney();
                    case AccountManager.Menu.INQUIRE -> manager.showAccInfo();
                    case AccountManager.Menu.DELETE -> manager.deleteAccount();
                    case AccountManager.Menu.EXIT -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    default -> throw new Exception("메뉴 선택 오류: 1~6 사이의 숫자를 입력하세요.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력하세요.");
                sc.nextLine(); // 버퍼 비우기
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
