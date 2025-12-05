package christmas.view;

import static christmas.constant.Constant.EVENT_NAME;
import static christmas.constant.Constant.PRESENT_EVENT_INDEX;

import christmas.constant.Menu;
import christmas.domain.Badge;
import christmas.dto.EventDto;
import christmas.dto.OrderedMenuDto;
import java.util.EnumMap;
import java.util.List;

public class OutputView {

    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String RESULT_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDER_MENU = "<주문 메뉴>";
    private static final String ORDER_MENU_DETAIL = "%s %d개\n";
    private static final String TOTAL_PRICE = "<할인 전 총주문 금액>";
    private static final String TOTAL_PRICE_DETAIL = "%,d원\n";
    private static final String PRESENT_MENU = "<증정 메뉴>";
    private static final String PRESENT_MENU_DETAIL = "샴페인 1개\n";
    private static final String BENEFIT = "<혜택 내역>";
    private static final String BENEFIT_DETAIL = "%s: -%,d원\n";
    private static final String TOTAL_BENEFIT = "<총혜택 금액>";
    private static final String TOTAL_BENEFIT_DETAIL = "-%,d원\n";
    private static final String FINAL_PRICE = "<할인 후 예상 결제 금액>";
    private static final String FINAL_PRICE_DETAIL = "%,d원\n";
    private static final String BADGE = "<12월 이벤트 배지>";
    private static final String NOTHING = "없음";

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printResult(OrderedMenuDto orderedMenuDto, EventDto eventDto) {

        System.out.println(RESULT_MESSAGE);

        EnumMap<Menu, Integer> menus = orderedMenuDto.orderedMenu();
        int totalPrice = orderedMenuDto.totalPrice();
        List<Boolean> eventResult = eventDto.eventResult();
        List<Integer> benefitPrice = eventDto.benefitPrice();
        Badge badge = eventDto.badge();

        System.out.println(ORDER_MENU);
        for (Menu menu : menus.keySet()) {
            System.out.printf(ORDER_MENU_DETAIL, menu.getName(), menus.get(menu));
        }
        System.out.println();

        System.out.println(TOTAL_PRICE);
        System.out.println(String.format(TOTAL_PRICE_DETAIL, totalPrice));

        System.out.println(PRESENT_MENU);
        if (eventResult.get(PRESENT_EVENT_INDEX).equals(true)) {
            System.out.println(PRESENT_MENU_DETAIL);
        } else {
            System.out.println(NOTHING);
        }
        System.out.println();

        System.out.println(BENEFIT);
        if (!eventResult.contains(true)) {
            System.out.println(NOTHING);
        } else {
            for (int i = 0; i < eventResult.size(); i++) {
                if (eventResult.get(i).equals(true)) {
                    System.out.print(String.format(BENEFIT_DETAIL, EVENT_NAME.get(i), benefitPrice.get(i)));
                }
            }
        }
        System.out.println();

        System.out.println(TOTAL_BENEFIT);
        Integer totalBenefit = benefitPrice.stream().reduce(0, Integer::sum);
        if (totalBenefit == 0) {
            System.out.println(NOTHING);
        } else {
            System.out.println(String.format(TOTAL_BENEFIT_DETAIL, totalBenefit));
        }
        System.out.println();

        System.out.println(FINAL_PRICE);
        System.out.println(String.format(FINAL_PRICE_DETAIL, totalPrice - totalBenefit));

        System.out.println(BADGE);
        if (badge.equals(Badge.NONE)) {
            System.out.println(NOTHING);
        } else {
            System.out.println(badge.getName());
        }
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
