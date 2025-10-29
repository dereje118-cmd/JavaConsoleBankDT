package banking04단계;

import java.util.*;

public class AccountManager {
    private Set<Account> accSet = new HashSet<>();
    private Scanner sc = new Scanner(System.in);

    interface Menu {
        int MAKE = 1, DEPOSIT = 2, WITHDRAW = 3, INQUIRE = 4, DELETE = 5, EXIT = 6;
    }

    public void showMenu() {
        System.out.println("\n=== 계좌관리 프로그램 ===");
        System.out.println("1. 계좌개설");
        System.out.println("2. 입금");
        System.out.println("3. 출금");
        System.out.println("4. 전체계좌정보출력");
        System.out.println("5. 계좌삭제");
        System.out.println("6. 프로그램종료");
        System.out.print("선택: ");
    }

    public void makeAccount() {
        System.out.println("[계좌종류선택] 1. 보통계좌  2. 신용계좌");
        int sel = sc.nextInt();
        sc.nextLine();

        System.out.print("계좌번호: ");
        String accNum = sc.nextLine();
        System.out.print("이름: ");
        String name = sc.nextLine();
        System.out.print("잔액: ");
        int balance = sc.nextInt();
        System.out.print("기본이자율(%): ");
        double rate = sc.nextDouble();
        sc.nextLine();

        Account newAcc = null;

        if (sel == 1) {
            newAcc = new NormalAccount(accNum, name, balance, rate);
        } else {
            System.out.print("신용등급(A,B,C): ");
            String grade = sc.nextLine();
            newAcc = new HighCreditAccount(accNum, name, balance, rate, grade);
        }

        if (accSet.contains(newAcc)) {
            System.out.print("중복계좌발견됨. 덮어쓸까요?(y/n): ");
            String ans = sc.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                accSet.remove(newAcc);
                accSet.add(newAcc);
                System.out.println("기존 계좌가 덮어쓰기 되었습니다.");
            } else {
                System.out.println("새로운 계좌 개설이 취소되었습니다.");
            }
        } else {
            accSet.add(newAcc);
            System.out.println("계좌개설이 완료되었습니다.");
        }
    }

    public void depositMoney() {
        System.out.print("입금할 계좌번호: ");
        String accNum = sc.next();
        System.out.print("입금액: ");
        int amount = sc.nextInt();

        for (Account acc : accSet) {
            if (acc.getAccountNumber().equals(accNum)) {
                acc.deposit(amount);
                return;
            }
        }
        System.out.println("해당 계좌를 찾을 수 없습니다.");
    }

    public void withdrawMoney() {
        System.out.print("출금할 계좌번호: ");
        String accNum = sc.next();
        System.out.print("출금액: ");
        int amount = sc.nextInt();

        for (Account acc : accSet) {
            if (acc.getAccountNumber().equals(accNum)) {
                acc.withdraw(amount);
                return;
            }
        }
        System.out.println("해당 계좌를 찾을 수 없습니다.");
    }

    public void showAccInfo() {
        for (Account acc : accSet) {
            acc.showAccountInfo();
        }
    }

    public void deleteAccount() {
        System.out.print("삭제할 계좌번호: ");
        String accNum = sc.next();
        Account temp = null;

        for (Account acc : accSet) {
            if (acc.getAccountNumber().equals(accNum)) {
                temp = acc;
                break;
            }
        }

        if (temp != null) {
            accSet.remove(temp);
            System.out.println("계좌가 삭제되었습니다.");
        } else {
            System.out.println("해당 계좌가 존재하지 않습니다.");
        }
    }
}
