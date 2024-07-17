package com.mannazo.shopservice.exception;

public class OrderCreationException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "상품을 찾을 수 없습니다.";

    public OrderCreationException() {
        super(DEFAULT_MESSAGE);
    }

    public OrderCreationException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public OrderCreationException(String message) {
        super(message);
    }

    public OrderCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
