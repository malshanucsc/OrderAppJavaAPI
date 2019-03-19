package com.sysco.order.exception;

public class EmptyUserException extends RuntimeException {
    public EmptyUserException(String message,Throwable cause){super(message,cause);}
}
