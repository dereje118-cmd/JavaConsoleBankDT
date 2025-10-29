package banking05단계;

import java.io.*;
import java.util.*;

/**
 * AccountManager
 * - 계좌관리, 입출금, 직렬화, 자동저장 기능 포함
 */
public class AccountManager implements ICustomDefine {
    private Set<Account> accSet = new HashSet<>();
    private Scanner sc = new Scanner(System.in);
    private static final String FILE_NAME = "AccountInfo.obj";
    private AutoSaver autoSaver;
    private boolean autoSaveEnabled = false;

    public AccountManager() {
        loadAccounts();
    }

    public void showMenu() {
        while (true) {
            try {
                System.out.println("\n=== 계좌관리 프로그램 ===");
                System.out.println("1. 계좌개설");
                System.out.println("2. 입금");
                System.out.println("3. 출금");
                System.out.println("4. 전체계좌정보출력");
                System.out.println("5. 프로그램종료");
                System.out.println("6. 자동저장 ON/OFF");
                System.out.print("선택: ");

                int choice = Integer.parseInt(sc.nextLine());
                if (choice < 1 || choice > 6)
                    throw new MenuSelectException("⚠ 1~6 사이의 숫자만 입력 가능합니다.");

                switch (choice) {
                    case MAKE: makeAccount(); break;
                    case DEPOSIT: depositMoney(); break;
                    case WITHDRAW: withdrawMoney(); break;
                    case INQUIRE: showAccInfo(); break;
                    case EXIT:
                        exitProgram();
                        return;
                    case 6:
                        toggleAutoSave();
                        break;
                }
            } catch (MenuSelectException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("⚠ 메뉴선택은 숫자만 가능합니다!");
            }
        }
    }

    private void makeAccount() {
        System.out.println("1. 보통예금계좌  2. 신용신뢰계좌");
        int sel = Integer.parseInt(sc.nextLine());

        System.out.print("계좌번호: ");
        String accNum = sc.nextLine();
        System.out.print("이름: ");
        String name = sc.nextLine();
        System.out.print("입금액: ");
        int balance = Integer.parseInt(sc.nextLine());

        Account acc = null;
        if (sel == 1) {
            System.out.print("기본이자율(%): ");
            int interest = Integer.parseInt(sc.nextLine());
            acc = new NormalAccount(accNum, name, balance, interest);
        } else if (sel == 2) {
            System.out.print("기본이자율(%): ");
            int interest = Integer.parseInt(sc.nextLine());
            System.out.print("신용등급(A, B, C): ");
            String grade = sc.nextLine();
            acc = new HighCreditAccount(accNum, name, balance, interest, grade);
        }

        if (accSet.contains(acc)) {
            System.out.print("중복계좌발견됨. 덮어쓸까요?(y/n): ");
            String ans = sc.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                accSet.remove(acc);
                accSet.add(acc);
                System.out.println(">> 기존 계좌 덮어쓰기 완료.");
            } else {
                System.out.println(">> 기존 계좌 유지됨.");
            }
        } else {
            accSet.add(acc);
            System.out.println(">> 계좌 개설 완료.");
        }
    }

    private void depositMoney() {
        System.out.print("계좌번호: ");
        String accNum = sc.nextLine();
        Account acc = findAccount(accNum);

        if (acc == null) {
            System.out.println("⚠ 계좌를 찾을 수 없습니다.");
            return;
        }

        try {
            System.out.print("입금액: ");
            int amount = Integer.parseInt(sc.nextLine());
            acc.deposit(amount);
            System.out.println(">> 입금 완료.");
        } catch (NumberFormatException e) {
            System.out.println("⚠ 금액은 숫자로 입력하세요!");
        }
    }

    private void withdrawMoney() {
        System.out.print("계좌번호: ");
        String accNum = sc.nextLine();
        Account acc = findAccount(accNum);

        if (acc == null) {
            System.out.println("⚠ 계좌를 찾을 수 없습니다.");
            return;
        }

        try {
            System.out.print("출금액: ");
            int amount = Integer.parseInt(sc.nextLine());
            acc.withdraw(amount);
        } catch (NumberFormatException e) {
            System.out.println("⚠ 금액은 숫자로 입력하세요!");
        }
    }

    private void showAccInfo() {
        for (Account acc : accSet) {
            acc.showAccInfo();
        }
        System.out.println("총 계좌수: " + accSet.size());
    }

    private Account findAccount(String accNum) {
        for (Account acc : accSet) {
            if (acc.getAccountNum().equals(accNum)) return acc;
        }
        return null;
    }

    private void toggleAutoSave() {
        if (!autoSaveEnabled) {
            autoSaver = new AutoSaver(accSet);
            autoSaver.setDaemon(true);
            autoSaver.start();
            autoSaveEnabled = true;
            System.out.println("✅ 자동저장 기능이 켜졌습니다.");
        } else {
            autoSaver.stopSaver();
            autoSaveEnabled = false;
            System.out.println("🛑 자동저장 기능이 꺼졌습니다.");
        }
    }

    private void exitProgram() {
        System.out.println("프로그램을 종료합니다.");
        saveAccounts();
        if (autoSaver != null) autoSaver.stopSaver();
    }

    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accSet);
            System.out.println(">> 계좌정보를 파일에 저장했습니다.");
        } catch (IOException e) {
            System.out.println("⚠ 저장 중 오류 발생!");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadAccounts() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println(">> 기존 계좌파일이 없습니다.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            accSet = (Set<Account>) ois.readObject();
            System.out.println(">> " + accSet.size() + "개의 계좌정보를 불러왔습니다.");
        } catch (Exception e) {
            System.out.println("⚠ 파일 로딩 오류!");
        }
    }
}
