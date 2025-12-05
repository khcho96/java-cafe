package christmas.service;

import christmas.domain.Event;
import christmas.domain.OrderDate;
import christmas.dto.EventDto;
import christmas.dto.OrderedMenuDto;

public class EventService {

    private OrderDate orderDate;

    public void registerDate(int date) {
        orderDate = OrderDate.from(date);
    }

    public EventDto calculateEvent(OrderedMenuDto orderedMenuDto) {
        int totalPrice = orderedMenuDto.totalPrice();
        Event event = Event.newInstance();

        if (totalPrice >= 10_000) {
            event.calculateChristmasDDayEvent(orderDate);
            event.calculateWeekdaysEvent(orderDate, orderedMenuDto);
            event.calculateWeekendEvent(orderDate, orderedMenuDto);
            event.calculateSpecialEvent(orderDate);
            event.calculatePresentEvent(orderedMenuDto);
        }
        event.selectBadge();
        return event.getEventResult();
    }
}
