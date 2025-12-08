package cafe.service;

import cafe.domain.Barista;
import cafe.domain.Order;
import cafe.domain.Orders;
import cafe.dto.ResultDto;
import java.util.List;

public class CafeService {

    private final Orders orders;
    private final Barista baristaA;
    private final Barista baristaB;

    public CafeService() {
        this.baristaA = Barista.from("A");
        this.baristaB = Barista.from("B");
        this.orders = Orders.newInstance();;
    }

    public ResultDto computeResult(List<String> orderMenus, int orderNumber) {
        Order order = Order.from(orderMenus, orderNumber);
        orders.addOrder(order);

        Barista workableBarista = getworkableBarista(baristaA, baristaB);
        Barista unworkableBarista = getunworkableBarista(workableBarista, baristaA, baristaB);

        int cumulativeWaitingTime = workableBarista.getTotalWorkTime();
        order.addWaitingTime(cumulativeWaitingTime);

        int worktime = workableBarista.getWorkTime();
        workableBarista.doWork(order);
        unworkableBarista.updateWorkTime(worktime);

        return ResultDto.of(orders, baristaA, baristaB);
    }

    private static Barista getworkableBarista(Barista baristaA, Barista baristaB) {
        if (baristaA.getWorkTime() <= baristaB.getWorkTime()) {
            return baristaA;
        }
        return baristaB;
    }

    private static Barista getunworkableBarista(Barista workableBarista, Barista baristaA, Barista baristaB) {
        if (workableBarista == baristaA) {
            return baristaB;
        }
        return baristaA;
    }
}
