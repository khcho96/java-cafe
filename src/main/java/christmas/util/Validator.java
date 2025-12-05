package christmas.util;

import static christmas.constant.ErrorMessage.DATE_ERROR;
import static christmas.constant.ErrorMessage.MENU_ERROR;

import java.util.List;

public final class Validator {

    private static final String CSV_FORMAT = "^ *(\\S+-\\d+)+ *(, *(\\S+-\\d+)+ *)*$";

    private Validator() {}

    public static void validateDate(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(DATE_ERROR.getErrorMessage());
        }
    }

    public static void validateMenu(String input) {
        if (input == null || input.isBlank() || !input.strip().matches(CSV_FORMAT)) {
            throw new IllegalArgumentException(MENU_ERROR.getErrorMessage());
        }
    }

    public static void validateMenuCount(int count) {
        if (count == 0) {
            throw new IllegalArgumentException(MENU_ERROR.getErrorMessage());
        }
    }

    public static void validateMenuUnique(List<String> menuNames, String menuName) {
        if (menuNames.contains(menuName)) {
            throw new IllegalArgumentException(MENU_ERROR.getErrorMessage());
        }
    }
}
