package cafe.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String CUSTOMER_COUNT_REQUEST = "현재 줄에 서 있는 손님 수를 입력해 주세요.";
    private static final String ORDER_REQUEST = "%d번 손님의 주문을 입력해 주세요. (e.g. 아메리카노-2,치즈케이크-1)\n";

    public static String readCustomerCount() {
        System.out.println(CUSTOMER_COUNT_REQUEST);
        return Console.readLine();
    }

    public static String readOrder(int orderNumber) {
        System.out.printf(ORDER_REQUEST, orderNumber);
        return Console.readLine();
    }
}
