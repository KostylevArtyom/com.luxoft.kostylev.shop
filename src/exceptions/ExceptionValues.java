package exceptions;

public enum ExceptionValues {
    CUSTOMER_NOT_EXIST_EXCEPTION_VALUE("Customer doesn\'t exist!"),
    GOOD_NOT_EXIST_EXCEPTION_VALUE("Good doesn\'t exist!"),
    NOT_ENOUGH_AMOUNT_EXCEPTION_VALUE("Not enough amount in stock!");

    private String value;

    ExceptionValues(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}