package com.mannazo.shopservice.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "상품을 찾을 수 없습니다.";

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
