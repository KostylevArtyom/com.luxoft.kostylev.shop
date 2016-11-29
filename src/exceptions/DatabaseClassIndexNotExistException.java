package exceptions;

import exceptions.utils.ExceptionValues;

public class DatabaseClassIndexNotExistException extends Exception {
    public DatabaseClassIndexNotExistException() {
        super(ExceptionValues.ATABASE_CLASS_INDEX_ALREADY_EXIST_EXCEPTION.toString());
    }
}