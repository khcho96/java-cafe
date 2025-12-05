package christmas.domain;

import static christmas.constant.ErrorMessage.DATE_ERROR;

public class OrderDate {

    private final int date;

    private OrderDate(int date) {
        validateAll(date);

        this.date = date;
    }

    public static OrderDate from(int date) {
        return new OrderDate(date);
    }

    private void validateAll(int date) {
        validateRange(date);
    }

    private void validateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(DATE_ERROR.getErrorMessage());
        }
    }

    public int getDate() {
        return date;
    }
}
