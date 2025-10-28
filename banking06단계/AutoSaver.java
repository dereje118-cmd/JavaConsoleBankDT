package banking06단계;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class AutoSaver extends Thread {
    private final Set<Account> accSet;

    public AutoSaver(Set<Account> accSet) {
        this.accSet = accSet;
        setDaemon(true); // 데몬 스레드
    }

    @Override
    public void run() {
        while(true) {
            try {
                saveToTextFile();
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                System.out.println("[자동저장 중지]");
                return;
            } catch(IOException e) {
                System.out.println("[자동저장 오류] " + e.getMessage());
            }
        }
    }

    private void saveToTextFile() throws IOException {
        try(PrintWriter pw = new PrintWriter(new FileWriter("AutoSaveAccount.txt"))) {
            for(Account acc : accSet) {
                pw.println(acc);
            }
        }
        System.out.println("[자동저장 완료]");
    }
}
