package christmas.domain;

import static christmas.constant.ErrorMessage.MENU_ERROR;
import static christmas.constant.ErrorMessage.MENU_MAX_ERROR;
import static christmas.constant.ErrorMessage.MENU_ONLY_BEVERAGE_ERROR;

import christmas.constant.Menu;
import christmas.dto.OrderedMenuDto;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class OrderedMenu {

    private final EnumMap<Menu, Integer> orderedMenu;

    private OrderedMenu(EnumMap<Menu, Integer> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    public static OrderedMenu from(List<String> menuNames) {
        EnumMap<Menu, Integer> orderedMenu = new EnumMap<>(Menu.class);
        for (String menuName : menuNames) {
            Menu menu = Menu.from(menuName);
            validateExistence(menu);

            orderedMenu.put(menu, orderedMenu.getOrDefault(menu, 0) + 1);
        }

        validateOnlyBeverage(orderedMenu);
        validateCount(orderedMenu);

        return new OrderedMenu(orderedMenu);
    }

    private static void validateExistence(Menu menu) {
        if (menu.equals(Menu.NONE)) {
            throw new IllegalArgumentException(MENU_ERROR.getErrorMessage());
        }
    }

    private static void validateOnlyBeverage(EnumMap<Menu, Integer> orderedMenu) {
        Set<Menu> menus = orderedMenu.keySet();
        if (menus.stream().allMatch(Menu::isBeverage)) {
            throw new IllegalArgumentException(MENU_ONLY_BEVERAGE_ERROR.getErrorMessage());
        }
    }

    private static void validateCount(EnumMap<Menu, Integer> orderedMenu) {
        Optional<Integer> sum = orderedMenu.values().stream().reduce(Integer::sum);
        if (sum.isPresent() && sum.get() > 20) {
            throw new IllegalArgumentException(MENU_MAX_ERROR.getErrorMessage());
        }
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Menu menu : orderedMenu.keySet()) {
            totalPrice += menu.getPrice() * orderedMenu.get(menu);
        }
        return totalPrice;
    }

    public OrderedMenuDto getOrderedMenu() {
        return new OrderedMenuDto(new EnumMap<>(orderedMenu), calculateTotalPrice());
    }
}
