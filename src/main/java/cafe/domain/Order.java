package cafe.domain;

import static cafe.constant.ErrorMessage.ORDER_ERROR;

import cafe.constant.Menu;
import java.util.EnumMap;
import java.util.List;

public class Order {

    private final int orderNumber;
    private final EnumMap<Menu, Integer> orderMenus;
    private int waitingTime;
    private int workTime;

    private Order(EnumMap<Menu, Integer> orderMenus, int orderNumber) {
        for (Menu menu : orderMenus.keySet()) {
            workTime += menu.getWorkTime() * orderMenus.get(menu);
        }

        this.orderMenus = orderMenus;
        this.orderNumber = orderNumber;
    }

    public static Order from(List<String> menus, int orderNumber) {
        EnumMap<Menu, Integer> orderMenus = new EnumMap<>(Menu.class);

        if (menus.isEmpty() || menus.size() > 10) {
            throw new IllegalArgumentException(ORDER_ERROR.getErrorMessage());
        }

        for (String menuNames : menus) {
            Menu menu = Menu.from(menuNames);
            if (menu.isNone()) {
                throw new IllegalArgumentException(ORDER_ERROR.getErrorMessage());
            }

            orderMenus.put(menu, orderMenus.getOrDefault(menu, 0) + 1);
        }

        return new Order(orderMenus, orderNumber);
    }

    public int calculateDrinkProfit() {
        int totalProfit = 0;
        for (Menu menu : orderMenus.keySet()) {
            if (!menu.isDessert()) {
                totalProfit += menu.getPrice() * orderMenus.get(menu);
            }
        }
        return totalProfit;
    }

    public int calculateDessertProfit() {
        int totalProfit = 0;
        for (Menu menu : orderMenus.keySet()) {
            if (menu.isDessert()) {
                totalProfit += menu.getPrice() * orderMenus.get(menu);
            }
        }
        return totalProfit;
    }

    public int calculateTotalProfit() {
        return calculateDrinkProfit() + calculateDessertProfit();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public EnumMap<Menu, Integer> getOrderMenus() {
        return orderMenus;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void addWaitingTime(int worktime) {
        waitingTime = worktime;
    }

    public int getTotalWorkTime() {
        return waitingTime + workTime;
    }
}
