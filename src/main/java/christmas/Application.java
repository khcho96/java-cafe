package christmas;

import christmas.controller.ChristmasController;
import christmas.service.EventService;
import christmas.service.MenuService;

public class Application {
    public static void main(String[] args) {
        EventService eventService = new EventService();
        MenuService menuService = new MenuService();
        ChristmasController christmasController = new ChristmasController(eventService, menuService);
        christmasController.run();
    }
}
