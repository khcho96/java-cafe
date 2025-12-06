package cafe.view;

import cafe.constant.Menu;
import cafe.domain.Barista;
import cafe.domain.Order;
import cafe.domain.Orders;
import cafe.dto.ResultDto;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class OutputView {

    private static final String RESULT_MESSAGE = "\n=== 카페 주문 대기열 결과 ===\n";
    private static final String CUSTOMER_RESULT_MESSAGE = "<손님별 주문 및 대기 시간>";
    private static final String CUSTOMER = "%d번 손님: ";
    private static final String CUSTOMER_ORDER = "%s %d개";
    private static final String CUSTOMER_TIME = " - 대기 시간: %d분\n"
            + " - 주문 제조 시간: %d분\n"
            + " - 주문 완료 시각: %d분\n\n";
    private static final String BARISTA_RESULT_MESSAGE = "<직원별 작업 시간>";
    private static final String BARISTA_TIME = "직원 %s 총 작업 시간: %d분\n";
    private static final String MOST_WORK_BARISTA = "가장 많이 일한 직원: 직원 %s\n\n";
    private static final String TIME_STATUS_MESSAGE = "<대기 시간 통계>";
    private static final String MOST_WAITING_CUSTOMER = "가장 오래 기다린 손님 번호: %d번\n"
            + "가장 오래 기다린 손님의 대기 시간: %d분\n"
            + "평균 대기 시간: %.1f분\n\n";
    private static final String PROFIT_MESSAGE = "<매출 통계>";
    private static final String PROFIT_RESULT = "총 매출: %,d원\n"
            + "음료 매출: %,d원\n"
            + "디저트 매출: %,d원\n";
    private static final String DRAW = "동일";


    public static void print(ResultDto resultDto) {
        Orders orders = resultDto.getOrders();
        Barista baristaA = resultDto.getBaristaA();
        Barista baristaB = resultDto.getBaristaB();

        System.out.println(RESULT_MESSAGE);

        printCustomerResult(orders);

        printBaristaResult(baristaA, baristaB);

        printWaitingTimeStatus(orders);

        printProfitStatus(orders);
    }

    private static void printProfitStatus(Orders orders) {
        System.out.println(PROFIT_MESSAGE);
        System.out.printf(PROFIT_RESULT, orders.getTotalProfit(), orders.getDrinkProfit(), orders.getDessertProfit());
    }

    private static void printWaitingTimeStatus(Orders orders) {
        System.out.println(TIME_STATUS_MESSAGE);
        System.out.printf(MOST_WAITING_CUSTOMER, orders.getMostWaitingCustomer(), orders.getMostWaitingTime(), orders.getWaitingTimeAvg());
    }

    private static void printBaristaResult(Barista baristaA, Barista baristaB) {
        System.out.println(BARISTA_RESULT_MESSAGE);
        int totalWorkTimeA = baristaA.getTotalWorkTime();
        int totalWorkTimeB = baristaB.getTotalWorkTime();
        System.out.printf(BARISTA_TIME, baristaA.getName(), totalWorkTimeA);
        System.out.printf(BARISTA_TIME, baristaB.getName(), totalWorkTimeB);
        String mostWorkBaristaName = baristaA.mostWorkBarista(baristaB);
        if (mostWorkBaristaName == null) {
            mostWorkBaristaName = DRAW;
        }
        System.out.printf(MOST_WORK_BARISTA, mostWorkBaristaName);
    }

    private static void printCustomerResult(Orders orders) {
        System.out.println(CUSTOMER_RESULT_MESSAGE);
        List<Order> ordersList = orders.getOrders();
        for (int i = 0; i < ordersList.size(); i++) {
            Order order = ordersList.get(i);
            EnumMap<Menu, Integer> orderMenus = order.getOrderMenus();
            List<String> menus = new ArrayList<>();
            for (Menu menu : orderMenus.keySet()) {
                Integer count = orderMenus.get(menu);
                menus.add(String.format(CUSTOMER_ORDER, menu.getName(), count));
            }

            System.out.printf(CUSTOMER + String.join(", ", menus.toArray(new String[0])), i+1);
            System.out.println();
            System.out.printf(CUSTOMER_TIME, order.getWaitingTime(), order.getWorkTime(), order.getTotalWorkTime());
        }
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
