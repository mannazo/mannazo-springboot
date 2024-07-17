package com.mannazo.communityservice.exception;

public class UserServiceException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "사용자 서비스에서 예외가 발생하였습니다.";

    public UserServiceException() {
        super(DEFAULT_MESSAGE);
    }

    public UserServiceException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
