package christmas.util;

import java.util.ArrayList;
import java.util.List;

public final class InputParser {

    private static final String FIRST_DELIMITER = ",";
    private static final String SECOND_DELIMITER = "-";

    private InputParser() {
    }

    public static Integer parseToInteger(String rawInput) {
        Validator.validateDate(rawInput);
        rawInput = rawInput.strip();

        return NumberConvertor.convertDateToNumber(rawInput);
    }

    public static List<String> parseToElements(String rawInput) {
        Validator.validateMenu(rawInput);

        List<String> menuNames = new ArrayList<>();
        String[] split = rawInput.strip().split(FIRST_DELIMITER);

        for (String s : split) {
            String[] split1 = s.split(SECOND_DELIMITER);
            String menuName = split1[0];
            int count = NumberConvertor.convertMenuCountToNumber(split1[1]);

            Validator.validateMenuCount(count);
            Validator.validateMenuUnique(menuNames, menuName);

            for (int i = 0; i < count; i++) {
                menuNames.add(menuName);
            }
        }

        return menuNames;
    }
}
