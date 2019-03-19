package com.sysco.order.exception;

public class EmptyOrderException extends RuntimeException {
    public EmptyOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}