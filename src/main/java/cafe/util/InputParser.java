package cafe.util;

import static cafe.constant.ErrorMessage.ORDER_ERROR;

import java.util.ArrayList;
import java.util.List;

public final class InputParser {

    private static final String FIRST_DELIMITER = ",";
    private static final String SECOND_DELIMITER = "-";

    private InputParser() {}

    public static Integer parseCustomerCount(String rawInput) {
        Validator.validateCustomerCount(rawInput);
        rawInput = rawInput.strip();

        return NumberConvertor.convertToNumber(rawInput);
    }

    public static List<String> parseOrders(String rawInput) {
        Validator.validateOrder(rawInput);
        rawInput = rawInput.strip();

        List<String> orderMenus = new ArrayList<>();
        String[] split = rawInput.split(FIRST_DELIMITER);
        for (String s : split) {
            String[] order = s.strip().split(SECOND_DELIMITER);
            String name = order[0];
            int count = NumberConvertor.convertToNumber(order[1]);

            validateRange(count);
            validateUnique(orderMenus, name);

            for (int i = 0; i < count; i++) {
                orderMenus.add(name);
            }
        }

        return orderMenus;
    }

    private static void validateUnique(List<String> orderMenus, String name) {
        if (orderMenus.contains(name)) {
            throw new IllegalArgumentException(ORDER_ERROR.getErrorMessage());
        }
    }

    private static void validateRange(int count) {
        if (count == 0) {
            throw new IllegalArgumentException(ORDER_ERROR.getErrorMessage());
        }
    }
}
