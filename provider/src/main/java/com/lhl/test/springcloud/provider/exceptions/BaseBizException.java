package com.lhl.test.springcloud.provider.exceptions;

public class BaseBizException extends RuntimeException {

    public BaseBizException(String message) {
        super(message);
    }

    public BaseBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseBizException(Throwable cause) {
        super(cause);
    }

}
