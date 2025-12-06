package cafe.util;

import static cafe.constant.ErrorMessage.CUSTOMER_COUNT_ERROR;
import static cafe.constant.ErrorMessage.ORDER_ERROR;

public final class Validator {

    private static final String CSV_FORMAT = "^ *(\\S+-\\d+)+ *(, *(\\S+-\\d+)+ *)*$";
    private static final String NUMBER_FORMAT = "\\d";
    private static final int CUSTOMER_MIN = 1;
    private static final int CUSTOMER_MAX = 20;

    private Validator() {}

    public static void validateCustomerCount(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(CUSTOMER_COUNT_ERROR.getErrorMessage());
        }

        if (!input.matches(NUMBER_FORMAT)) {
            throw new IllegalArgumentException(CUSTOMER_COUNT_ERROR.getErrorMessage());
        }

        int customCnt = NumberConvertor.convertToNumber(input);
        if (customCnt < CUSTOMER_MIN || customCnt > CUSTOMER_MAX) {
            throw new IllegalArgumentException(CUSTOMER_COUNT_ERROR.getErrorMessage());
        }
    }

    public static void validateOrder(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ORDER_ERROR.getErrorMessage());
        }

        if (!input.matches(CSV_FORMAT)) {
            throw new IllegalArgumentException(ORDER_ERROR.getErrorMessage());
        }
    }
}
