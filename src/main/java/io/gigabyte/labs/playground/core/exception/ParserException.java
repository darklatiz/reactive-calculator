package io.gigabyte.labs.playground.core.exception;

public class ParserException extends Exception{

    public ParserException(String message, Throwable ex) {
        super(message, ex);
    }

    public ParserException(String message) {
        super(message);
    }
}
