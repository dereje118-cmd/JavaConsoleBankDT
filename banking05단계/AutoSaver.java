package banking05단계;

import java.io.*;
import java.util.Set;

/**
 * AutoSaver Thread
 * - 5초마다 자동으로 계좌정보를 파일에 저장
 * - AccountManager에서 ON/OFF 가능
 */
public class AutoSaver extends Thread {
    private Set<Account> accSet;
    private boolean running = true;
    private static final String FILE_NAME = "AccountInfo.obj";

    public AutoSaver(Set<Account> accSet) {
        this.accSet = accSet;
    }

    public void stopSaver() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                saveAccounts();
                Thread.sleep(5000); // 5초마다 저장
            } catch (InterruptedException e) {
                System.out.println("자동저장 스레드가 중단되었습니다.");
            }
        }
    }

    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accSet);
            System.out.println("[자동저장] 계좌정보 저장 완료");
        } catch (IOException e) {
            System.out.println("⚠ 자동저장 중 오류 발생");
        }
    }
}
