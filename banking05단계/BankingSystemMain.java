package banking05단계;

/**
 * 프로그램의 진입점 (Main 클래스)
 * - AccountManager 객체를 통해 전체 프로그램이 실행된다.
 */
public class BankingSystemMain {
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        manager.showMenu();
    }
}
