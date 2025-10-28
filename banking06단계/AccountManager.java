package banking06단계;

import java.io.*;
import java.util.*;

interface MenuChoice {
    int MAKE=1, DEPOSIT=2, WITHDRAW=3, INQUIRE=4, DELETE=5, EXIT=6, AUTOSAVE=7;
}

public class AccountManager implements MenuChoice {
    private Set<Account> accSet = new HashSet<>();
    private final Scanner sc = new Scanner(System.in);
    private final String FILE_NAME = "AccountInfo.obj";
    private AutoSaver autoSaver;

    public AccountManager() { loadAccountInfo(); }

    public void showMenu() {
        while(true) {
            System.out.println("\n=== 계좌관리 프로그램 ===");
            System.out.println("1. 계좌개설");
            System.out.println("2. 입금");
            System.out.println("3. 출금");
            System.out.println("4. 전체계좌정보출력");
            System.out.println("5. 계좌삭제");
            System.out.println("6. 프로그램종료");
            System.out.println("7. 저장옵션(자동저장 On/Off)");
            System.out.print("선택: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch(choice) {
                    case MAKE -> makeAccount();
                    case DEPOSIT -> depositMoney();
                    case WITHDRAW -> withdrawMoney();
                    case INQUIRE -> showAccInfo();
                    case DELETE -> deleteAccount();
                    case AUTOSAVE -> manageAutoSave();
                    case EXIT -> { saveAccountInfo(); stopAutoSave(); System.out.println("프로그램 종료"); return; }
                    default -> System.out.println("잘못된 선택입니다.");
                }
            } catch(NumberFormatException e) {
                System.out.println("숫자만 입력 가능합니다.");
            } catch(Exception e) {
                System.out.println("오류: " + e.getMessage());
            }
        }
    }

    // 계좌 개설
    private void makeAccount() {
        try {
            System.out.println("[계좌개설]");
            System.out.print("1. 보통계좌 | 2. 신용계좌 선택: ");
            int sel = Integer.parseInt(sc.nextLine());

            System.out.print("계좌번호: "); String accNum = sc.nextLine();
            System.out.print("이름: "); String name = sc.nextLine();
            System.out.print("잔액: "); int balance = Integer.parseInt(sc.nextLine());
            System.out.print("기본이자율(%): "); int interest = Integer.parseInt(sc.nextLine());

            Account newAcc;
            if(sel==1) newAcc = new NormalAccount(accNum, name, balance, interest);
            else {
                System.out.print("신용등급(A,B,C): "); char grade = sc.nextLine().toUpperCase().charAt(0);
                newAcc = new HighCreditAccount(accNum, name, balance, interest, grade);
            }

            if(!accSet.add(newAcc)) {
                System.out.print("중복계좌 발견. 덮어쓸까요?(y/n): ");
                String ans = sc.nextLine();
                if(ans.equalsIgnoreCase("y")) { accSet.remove(newAcc); accSet.add(newAcc); System.out.println("덮어쓰기 완료"); }
                else System.out.println("기존정보 유지");
            } else System.out.println("계좌개설 완료");
        } catch(Exception e) { System.out.println("계좌개설 오류: "+e.getMessage()); }
    }

    // 입금
    private void depositMoney() {
        try {
            System.out.print("입금할 계좌번호: "); String accNum = sc.nextLine();
            Account acc = findAccount(accNum); if(acc==null) { System.out.println("계좌없음"); return; }

            System.out.print("입금금액: "); int money = Integer.parseInt(sc.nextLine());
            if(money<0 || money%500!=0) { System.out.println("입금은 500원 단위만 가능"); return; }

            acc.deposit(money); System.out.println("입금완료");
        } catch(Exception e) { System.out.println("입금오류"); }
    }

    // 출금
    private void withdrawMoney() {
        try {
            System.out.print("출금할 계좌번호: "); String accNum = sc.nextLine();
            Account acc = findAccount(accNum); if(acc==null) { System.out.println("계좌없음"); return; }

            System.out.print("출금금액: "); int money = Integer.parseInt(sc.nextLine());
            if(money<0 || money%1000!=0) { System.out.println("출금은 1000원 단위만 가능"); return; }

            if(acc.balance < money) {
                System.out.print("잔고 부족. 전체출금? y/n: "); String ans = sc.nextLine();
                if(ans.equalsIgnoreCase("y")) { acc.withdraw(acc.balance); System.out.println("전체출금 완료"); }
                else System.out.println("출금취소");
            } else { acc.withdraw(money); System.out.println("출금완료"); }
        } catch(Exception e) { System.out.println("출금오류"); }
    }

    private Account findAccount(String accNum) {
        for(Account acc: accSet) if(acc.getAccNum().equals(accNum)) return acc;
        return null;
    }

    private void showAccInfo() {
        System.out.println("\n[전체 계좌]");
        if(accSet.isEmpty()) { System.out.println("없음"); return; }
        for(Account acc: accSet) System.out.println(acc);
    }

    private void deleteAccount() {
        System.out.print("삭제할 계좌번호: "); String accNum = sc.nextLine();
        Account acc = findAccount(accNum); if(acc==null) { System.out.println("없음"); return; }
        accSet.remove(acc); System.out.println("삭제완료");
    }

    private void saveAccountInfo() {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(accSet); System.out.println("계좌정보 저장 완료");
        } catch(Exception e) { System.out.println("저장오류"); }
    }

    @SuppressWarnings("unchecked")
    private void loadAccountInfo() {
        File file = new File(FILE_NAME); if(!file.exists()) return;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            accSet = (HashSet<Account>) in.readObject();
            System.out.println("기존계좌 불러오기 완료 ("+accSet.size()+"개)");
        } catch(Exception e) { System.out.println("불러오기 오류"); }
    }

    private void manageAutoSave() {
        System.out.println("[저장옵션] 1. ON | 2. OFF"); int sel = Integer.parseInt(sc.nextLine());
        if(sel==1) startAutoSave(); else if(sel==2) stopAutoSave(); else System.out.println("잘못된 선택");
    }

    private void startAutoSave() {
        if(autoSaver!=null && autoSaver.isAlive()) { System.out.println("이미 실행중"); return; }
        autoSaver = new AutoSaver(accSet); autoSaver.start(); System.out.println("자동저장 시작");
    }

    private void stopAutoSave() {
        if(autoSaver!=null && autoSaver.isAlive()) { autoSaver.interrupt(); System.out.println("자동저장 종료"); }
        else System.out.println("자동저장 실행중 아님");
    }
}
