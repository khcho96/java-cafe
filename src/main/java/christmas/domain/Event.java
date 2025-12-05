package christmas.domain;

import static christmas.constant.Constant.CHRISTMAS_EVENT_INDEX;
import static christmas.constant.Constant.PRESENT_EVENT_INDEX;
import static christmas.constant.Constant.SPECIAL_EVENT_INDEX;
import static christmas.constant.Constant.WEEKDAYS_EVENT_INDEX;
import static christmas.constant.Constant.WEEKEND_EVENT_INDEX;

import christmas.constant.Menu;
import christmas.dto.EventDto;
import christmas.dto.OrderedMenuDto;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Event {

    private static final List<Integer> SPECIAL_EVENT_DATE = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));

    private final List<Boolean> eventResult;
    private final List<Integer> benefitPrice;
    private Badge badge;

    private Event() {
        eventResult = new ArrayList<>(List.of(false, false, false, false, false));
        benefitPrice = new ArrayList<>(List.of(0, 0, 0, 0, 0));
    }

    public static Event newInstance() {
        return new Event();
    }

    public void calculateChristmasDDayEvent(OrderDate orderDate) {
        int date = orderDate.getDate();

        if (date > 25) {
            return;
        }

        eventResult.set(CHRISTMAS_EVENT_INDEX, true);
        benefitPrice.set(CHRISTMAS_EVENT_INDEX, 1000 + (date - 1) * 100);
    }

    public void calculateWeekdaysEvent(OrderDate orderDate, OrderedMenuDto orderedMenuDto) {
        int date = orderDate.getDate();

        if ((date % 7) == 1 || (date % 7) == 2) {
            return;
        }


        EnumMap<Menu, Integer> menu = orderedMenuDto.orderedMenu();
        List<Menu> desserts = menu.keySet().stream().filter(Menu::isDessert).toList();
        if (desserts.isEmpty()) {
            return;
        }

        int dessert_count = 0;
        for (Menu dessert : desserts) {
            dessert_count += menu.get(dessert);
        }

        eventResult.set(WEEKDAYS_EVENT_INDEX, true);
        benefitPrice.set(WEEKDAYS_EVENT_INDEX, 2023 * dessert_count);
    }

    public void calculateWeekendEvent(OrderDate orderDate, OrderedMenuDto orderedMenuDto) {
        int date = orderDate.getDate();

        if ((date % 7) != 1 && (date % 7) != 2) {
            return;
        }


        EnumMap<Menu, Integer> menu = orderedMenuDto.orderedMenu();
        List<Menu> mains = menu.keySet().stream().filter(Menu::isMain).toList();
        if (mains.isEmpty()) {
            return;
        }

        int main_count = 0;
        for (Menu main : mains) {
            main_count += menu.get(main);
        }

        eventResult.set(WEEKEND_EVENT_INDEX, true);
        benefitPrice.set(WEEKEND_EVENT_INDEX, 2023 * main_count);
    }

    public void calculateSpecialEvent(OrderDate orderDate) {
        int date = orderDate.getDate();

        if (!SPECIAL_EVENT_DATE.contains(date)) {
            return;
        }

        eventResult.set(SPECIAL_EVENT_INDEX, true);
        benefitPrice.set(SPECIAL_EVENT_INDEX, 1000);
    }

    public void calculatePresentEvent(OrderedMenuDto orderedMenuDto) {
        int totalPrice = orderedMenuDto.totalPrice();
        if (totalPrice < 120_000) {
            return;
        }

        eventResult.set(PRESENT_EVENT_INDEX, true);
        benefitPrice.set(PRESENT_EVENT_INDEX, 25_000);
    }

    public void selectBadge() {
        Integer totalBenefit = benefitPrice.stream().reduce(0, Integer::sum);
        badge = Badge.from(totalBenefit);
    }

    public EventDto getEventResult() {
        return new EventDto(eventResult, benefitPrice, badge);
    }
}
