package exceptions;

import exceptions.utils.ExceptionValues;

public class GoodNotExistException extends Exception {
    public GoodNotExistException() {
        super(ExceptionValues.GOOD_NOT_EXIST_EXCEPTION_VALUE.toString());
    }
}