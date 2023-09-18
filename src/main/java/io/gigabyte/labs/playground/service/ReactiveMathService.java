package io.gigabyte.labs.playground.service;

import io.gigabyte.labs.playground.dto.IResponse;
import io.gigabyte.labs.playground.dto.MultiplyCommand;
import io.gigabyte.labs.playground.dto.OperationEnum;
import io.gigabyte.labs.playground.dto.Response;
import io.gigabyte.labs.playground.factory.OperationFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
public class ReactiveMathService {

    private final OperationFactory operationFactory;

    public ReactiveMathService(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }

    public Mono<IResponse.Answer> square(int number) {
        return Mono.fromSupplier(() -> number * number)
          .map(square -> {
              String formatted = String.format("%d^2 = %d", number, square);
              return IResponse.Answer.createAnswer(BigDecimal.valueOf(square), OperationEnum.SQUARE, IResponse.Error.EMPTY, formatted);
          });
    }

    public Flux<IResponse.Answer> table(int number) {
        return Flux.range(1, 10)
          .doOnNext(integer -> log.info("reactive-math-service processing: {}", integer))
          .map(i -> {
              int output = number * i;
              String formatted = String.format("%d x %d = %d", number, i, output);
              return new IResponse.Answer(BigDecimal.valueOf(output), OperationEnum.MULTIPLICATION_TABLE, formatted, IResponse.Error.EMPTY, LocalDateTime.now());
          });
    }

    public Mono<Response> multiply(Mono<MultiplyCommand> multiplyCommandMono) {
        return multiplyCommandMono
          .map(multiplyCommand -> {
              int result = multiplyCommand.firstNumber() * multiplyCommand.secondNumber();
              String formatted = String.format("%d x %d = %d", multiplyCommand.firstNumber(), multiplyCommand.secondNumber(), result);
              return Response.createResponse(result, formatted);
          });
    }

    public Mono<IResponse.Answer> calculator_OOP(BigDecimal num1, BigDecimal num2, String strOperation) {
        return Mono.fromSupplier(() -> this.operationFactory.createOperation(strOperation, num1, num2))
          .map(operation -> IResponse.Answer.createAnswer(operation.calculate(), operation.operationEnum(), IResponse.Error.EMPTY, operation.resultToPlainString()));
    }
}
