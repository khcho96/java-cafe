package cafe.dto;

import cafe.domain.Barista;
import cafe.domain.Orders;

public class ResultDto {

    private final Orders orders;
    private final Barista baristaA;
    private final Barista baristaB;

    private ResultDto(Orders orders, Barista baristaA, Barista baristaB) {
        this.orders = orders;
        this.baristaA = baristaA;
        this.baristaB = baristaB;
    }

    public static ResultDto of(Orders orders, Barista baristaA, Barista baristaB) {
        return new ResultDto(orders, baristaA, baristaB);
    }

    public Barista getBaristaA() {
        return baristaA;
    }

    public Barista getBaristaB() {
        return baristaB;
    }

    public Orders getOrders() {
        return orders;
    }
}
