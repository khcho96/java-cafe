package christmas.service;

import christmas.domain.OrderedMenu;
import christmas.dto.OrderedMenuDto;
import java.util.List;

public class MenuService {

    public OrderedMenuDto calculateOrderedMenu(List<String> menuNames) {
        OrderedMenu orderedMenu = OrderedMenu.from(menuNames);
        return orderedMenu.getOrderedMenu();
    }
}
