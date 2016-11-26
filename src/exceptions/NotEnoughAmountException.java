package exceptions;

public class NotEnoughAmountException extends Exception {
    public NotEnoughAmountException() {
        super(ExceptionValues.NOT_ENOUGH_AMOUNT_EXCEPTION_VALUE.toString());
    }
}