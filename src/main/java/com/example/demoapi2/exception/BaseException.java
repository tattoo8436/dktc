package com.example.demoapi2.exception;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 2714470317189257851L;

    private final String error;
    private final transient Object data;

    public BaseException(String error, Object data, Throwable cause) {
        super(error, cause);
        this.error = error;
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public Object getData() {
        return data;
    }
}
