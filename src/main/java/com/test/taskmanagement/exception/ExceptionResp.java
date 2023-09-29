package com.test.taskmanagement.exception;

import java.time.LocalDateTime;

public class ExceptionResp {
    private final int code;
    private final String message;
    private final LocalDateTime timestamp;

    public ExceptionResp(int statusCode, String message) {
        this.code = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
