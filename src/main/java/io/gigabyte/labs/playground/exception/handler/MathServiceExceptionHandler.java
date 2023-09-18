package io.gigabyte.labs.playground.exception.handler;

import io.gigabyte.labs.playground.dto.InputFailedValidationResponse;
import io.gigabyte.labs.playground.exception.InputValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MathServiceExceptionHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleValidationException(InputValidationException ex) {
        InputFailedValidationResponse inputFailedValidationResponse = new InputFailedValidationResponse(
          ex.getErrorCode(),
          ex.getInput(),
          ex.getOperation(),
          ex.getMessage()
        );
        return ResponseEntity.badRequest().body(inputFailedValidationResponse);
    }

}
