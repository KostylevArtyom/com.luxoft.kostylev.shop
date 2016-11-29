package exceptions;

import exceptions.utils.ExceptionValues;

public class DatabaseClassIndexAlreadyExistException extends Exception {
    public DatabaseClassIndexAlreadyExistException() {
        super(ExceptionValues.DATABASE_CLASS_INDEX_NOT_EXIST_EXCEPTION.toString());
    }
}