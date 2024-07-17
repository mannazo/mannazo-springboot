package com.mannazo.shopservice.exception;

public class InvalidCommunityDataException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "잘못된 커뮤니티 데이터입니다.";

    public InvalidCommunityDataException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidCommunityDataException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
