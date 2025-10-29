package banking05단계;

/**
 * 메뉴선택 예외처리를 위한 개발자 정의 예외 클래스
 */
public class MenuSelectException extends Exception {
    public MenuSelectException(String message) {
        super(message);
    }
}
