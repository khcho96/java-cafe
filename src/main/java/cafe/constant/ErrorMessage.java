package cafe.constant;

public enum ErrorMessage {

    CUSTOMER_COUNT_ERROR("유효하지 않은 손님 수입니다. 다시 입력해 주세요."),
    ORDER_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE_PREFIX + errorMessage;
    }
}
