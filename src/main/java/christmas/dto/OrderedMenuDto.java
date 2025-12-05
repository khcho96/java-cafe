package christmas.dto;

import christmas.constant.Menu;
import java.util.EnumMap;

public record OrderedMenuDto(EnumMap<Menu, Integer> orderedMenu, int totalPrice) {
}
