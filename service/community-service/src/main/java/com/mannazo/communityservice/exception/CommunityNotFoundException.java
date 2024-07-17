package com.mannazo.communityservice.exception;

public class CommunityNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "커뮤니티를 찾을 수 없습니다.";

    public CommunityNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CommunityNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public CommunityNotFoundException(String message) {
        super(message);
    }

    public CommunityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
