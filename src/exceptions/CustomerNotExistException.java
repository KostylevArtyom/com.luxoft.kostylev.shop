package exceptions;

import exceptions.utils.ExceptionValues;

public class CustomerNotExistException extends Exception {
    public CustomerNotExistException() {
        super(ExceptionValues.CUSTOMER_NOT_EXIST_EXCEPTION_VALUE.toString());
    }
}