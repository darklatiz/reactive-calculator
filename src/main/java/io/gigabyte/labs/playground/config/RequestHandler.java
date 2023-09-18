package io.gigabyte.labs.playground.config;

import io.gigabyte.labs.playground.dto.IResponse;
import io.gigabyte.labs.playground.dto.MultiplyCommand;
import io.gigabyte.labs.playground.dto.OperationEnum;
import io.gigabyte.labs.playground.dto.Response;
import io.gigabyte.labs.playground.exception.InputValidationException;
import io.gigabyte.labs.playground.service.ReactiveMathService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class RequestHandler {

    private final ReactiveMathService reactiveMathService;

    public RequestHandler(ReactiveMathService reactiveMathService) {
        this.reactiveMathService = reactiveMathService;
    }

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
        String numberString = serverRequest.pathVariable("number");
        int number = Integer.parseInt(numberString);
        if (number < 10 || number > 50) {
            InputValidationException err = new InputValidationException(number, OperationEnum.SQUARE.name());
            return Mono.error(err);
        }
        Mono<IResponse.Answer> square = this.reactiveMathService.square(number);
        return ServerResponse.ok().body(square, Response.class);
    }

    public Mono<ServerResponse> multiplicationTableHandler(ServerRequest serverRequest) {
        String numberString = serverRequest.pathVariable("number");
        Flux<IResponse.Answer> table = this.reactiveMathService.table(Integer.parseInt(numberString));
        return ServerResponse.ok().body(table, IResponse.Answer.class);
    }

    public Mono<ServerResponse> streamMultiplicationTableHandler(ServerRequest serverRequest) {
        String numberString = serverRequest.pathVariable("number");
        Flux<IResponse.Answer> table = this.reactiveMathService.table(Integer.parseInt(numberString));
        return ServerResponse
          .ok()
          .contentType(MediaType.TEXT_EVENT_STREAM)
          .body(table, Response.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest) {
        Mono<MultiplyCommand> multiplyCommandMono = serverRequest.bodyToMono(MultiplyCommand.class);
        Mono<Response> responseMono = this.reactiveMathService.multiply(multiplyCommandMono);
        return ServerResponse
          .ok()
          .body(responseMono, Response.class);
    }

    public Mono<ServerResponse> calculator(ServerRequest serverRequest) {
        String number1 = serverRequest.pathVariable("number1");
        String number2 = serverRequest.pathVariable("number2");
        String operation = serverRequest.headers().firstHeader("operation");

        // Validations
        if (Objects.nonNull(operation) &&
          operation.equalsIgnoreCase("/") &&
          new BigDecimal(number2).intValue() == 0) {
            return Mono.error(new InputValidationException(0, operation, "Division by 0 is not allowed"));
        }

        BigDecimal bigDecimal1 = new BigDecimal(number1);
        BigDecimal bigDecimal2 = new BigDecimal(number2);
        var calculatorResponseMono = reactiveMathService.calculator_OOP(bigDecimal1, bigDecimal2, operation);
        var responseMono = calculatorResponseMono
          .switchIfEmpty(Mono.just(IResponse.Answer.createAnswerWithError(new IResponse.Error(500, "OPERATION FAILED", "Operation not defined: " + operation))));

        return ServerResponse
          .ok()
          .body(responseMono, IResponse.Answer.class);
    }
}
