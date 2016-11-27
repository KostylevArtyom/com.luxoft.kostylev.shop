package exceptions;

import exceptions.utils.ExceptionValues;

public class DataParseException extends Exception {
    public DataParseException() {
        super(ExceptionValues.DATA_PARSE_EXCEPTION_VALUE.toString());
    }
}