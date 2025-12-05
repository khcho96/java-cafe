package christmas.controller;

import christmas.dto.EventDto;
import christmas.dto.OrderedMenuDto;
import christmas.service.EventService;
import christmas.service.MenuService;
import christmas.util.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class ChristmasController {

    private final EventService eventService;
    private final MenuService menuService;

    public ChristmasController(EventService eventService, MenuService menuService) {
        this.eventService = eventService;
        this.menuService = menuService;
    }

    public void run() {
        OutputView.printStartMessage();
        registerDate();
        OrderedMenuDto orderedMenuDto = calculateOrderedMenu();
        EventDto eventDto = calculateEvent(orderedMenuDto);
        OutputView.printResult(orderedMenuDto, eventDto);
    }

    private void registerDate() {
        while (true) {
            try {
                String rawDate = InputView.readDate();
                Integer date = InputParser.parseToInteger(rawDate);
                eventService.registerDate(date);
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private OrderedMenuDto calculateOrderedMenu() {
        while (true) {
            try {
                String rawMenu = InputView.readMenu();
                List<String> menuNames = InputParser.parseToElements(rawMenu);
                return menuService.calculateOrderedMenu(menuNames);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private EventDto calculateEvent(OrderedMenuDto orderedMenuDto) {
        while (true) {
            try {
                return eventService.calculateEvent(orderedMenuDto);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }
}
