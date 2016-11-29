package exceptions.utils;

public enum ExceptionValues {
    CUSTOMER_NOT_EXIST_EXCEPTION_VALUE("Customer doesn\'t exist!"),
    GOOD_NOT_EXIST_EXCEPTION_VALUE("Good doesn\'t exist!"),
    NOT_ENOUGH_AMOUNT_EXCEPTION_VALUE("Not enough amount in stock!"),
    DATA_PARSE_EXCEPTION_VALUE("Data parse exception!"),
    DATABASE_CLASS_INDEX_NOT_EXIST_EXCEPTION("Such class-index pair doesn\'t exist in database exception!"),
    ATABASE_CLASS_INDEX_ALREADY_EXIST_EXCEPTION("Such class-index pair already exist in database exception!");

    private String value;

    ExceptionValues(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}