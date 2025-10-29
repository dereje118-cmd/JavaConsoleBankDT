package banking05단계;

/**
 * 메뉴선택 및 이자율 상수를 정의한 인터페이스
 */
public interface ICustomDefine {
    // 메뉴
    int MAKE = 1;
    int DEPOSIT = 2;
    int WITHDRAW = 3;
    int INQUIRE = 4;
    int EXIT = 5;

    // 신용등급별 추가이자율
    int A = 7; // 7%
    int B = 4; // 4%
    int C = 2; // 2%
}
