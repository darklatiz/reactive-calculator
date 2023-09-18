package io.gigabyte.labs.playground.controller;

import io.gigabyte.labs.playground.dto.IResponse;
import io.gigabyte.labs.playground.service.MathService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("math")
public class MathController {
    private final MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("/square/{number}")
    public ResponseEntity<IResponse.Answer> getSquare(@PathVariable("number") int number) {
        try {
            var square = mathService.square(number);
            return new ResponseEntity<>(square, HttpStatus.OK);
        } catch (Exception e) {
            IResponse.Error error = new IResponse.Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e.toString());
            IResponse.Answer answerWithError = IResponse.Answer.createAnswerWithError(error);
            return new ResponseEntity<>(answerWithError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/table/{number}")
    public ResponseEntity<IResponse.MultipleAnswer> getTable(@PathVariable("number") int number) {
        try {
            var multiplicationTable = mathService.multiplicationTable(number);
            return new ResponseEntity<>(new IResponse.MultipleAnswer(multiplicationTable), HttpStatus.OK);

        } catch (Exception e) {
            IResponse.Error error = new IResponse.Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e.toString());
            IResponse.Answer answerWithError = IResponse.Answer.createAnswerWithError(error);
            return new ResponseEntity<>(new IResponse.MultipleAnswer(Collections.singletonList(answerWithError)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
