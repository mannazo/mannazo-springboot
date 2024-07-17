package com.mannazo.shopservice.exception;

public class DatabaseException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "데이터베이스에서 예외가 발생하였습니다.";

    public DatabaseException() {
        super(DEFAULT_MESSAGE);
    }

    public DatabaseException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
