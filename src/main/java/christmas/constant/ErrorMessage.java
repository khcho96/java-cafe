package christmas.constant;

public enum ErrorMessage {

    DATE_ERROR("유효하지 않은 날짜입니다."),

    MENU_ERROR("유효하지 않은 주문입니다."),
    MENU_ONLY_BEVERAGE_ERROR("음료만 주문할 수 없습니다."),
    MENU_MAX_ERROR("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String ERROR_MESSAGE_SUFFIX = " 다시 입력해 주세요.";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE_PREFIX + errorMessage + ERROR_MESSAGE_SUFFIX;
    }
}
