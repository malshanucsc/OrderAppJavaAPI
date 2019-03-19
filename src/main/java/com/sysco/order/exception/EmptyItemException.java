package com.sysco.order.exception;

public class EmptyItemException extends RuntimeException {
    public EmptyItemException(String message,Throwable cause){super(message,cause);}
}
