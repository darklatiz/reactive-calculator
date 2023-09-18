package io.gigabyte.labs.playground.exception;

import lombok.Getter;

@Getter
public class InputValidationException extends RuntimeException {

    private String message = "Allowed range is -50 TO 50";
    private final int errorCode = 100;
    private int input;
    private String operation;

    public InputValidationException(int input, String operation) {
        super();
        this.input = input;
        this.operation = operation;
    }


    public InputValidationException(int input, String operation, String message) {
        super();
        this.input = input;
        this.operation = operation;
        this.message = message;
    }



}
