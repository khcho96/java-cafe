package christmas.util;

import static christmas.constant.ErrorMessage.DATE_ERROR;
import static christmas.constant.ErrorMessage.MENU_ERROR;

public final class NumberConvertor {

    public static Integer convertDateToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_ERROR.getErrorMessage());
        }
    }

    public static Integer convertMenuCountToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MENU_ERROR.getErrorMessage());
        }
    }
}
