package com.sysco.order.exception;

public class EmptyOrdertException extends RuntimeException {
    public EmptyOrdertException(String message, Throwable cause) {
        super(message, cause);
    }
}