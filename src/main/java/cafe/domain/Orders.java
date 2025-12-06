package cafe.domain;

import java.util.ArrayList;
import java.util.List;

public class Orders {

    private final List<Order> orders;

    private Orders() {
        this.orders = new ArrayList<>();
    }

    public static Orders newInstance() {
        return new Orders();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getMostWaitingCustomer() {
        int maxWaitingTime = 0;
        int orderNumber = 1;
        for (Order order : orders) {
            if (order.getWaitingTime() > maxWaitingTime) {
                maxWaitingTime = order.getWaitingTime();
                orderNumber = order.getOrderNumber();
            }
        }
        return orderNumber;
    }

    public int getMostWaitingTime() {
        int maxWaitingTime = 0;
        for (Order order : orders) {
            if (order.getWaitingTime() > maxWaitingTime) {
                maxWaitingTime = order.getWaitingTime();
            }
        }
        return maxWaitingTime;
    }

    public double getWaitingTimeAvg() {
        int waitingTimeSum = 0;
        for (Order order : orders) {
            waitingTimeSum += order.getWaitingTime();
        }
        return waitingTimeSum / (double) orders.size();
    }

    public int getTotalProfit() {
        int totalProfit = 0;
        for (Order order : orders) {
            totalProfit += order.calculateTotalProfit();
        }
        return totalProfit;
    }

    public int getDrinkProfit() {
        int totalProfit = 0;
        for (Order order : orders) {
            totalProfit += order.calculateDrinkProfit();
        }
        return totalProfit;
    }

    public int getDessertProfit() {
        int totalProfit = 0;
        for (Order order : orders) {
            totalProfit += order.calculateDessertProfit();
        }
        return totalProfit;
    }
}
