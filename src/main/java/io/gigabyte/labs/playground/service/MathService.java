package io.gigabyte.labs.playground.service;

import io.gigabyte.labs.playground.dto.IResponse;
import io.gigabyte.labs.playground.dto.OperationEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Slf4j
public class MathService {


    public IResponse.Answer square(int n) {
        int output = n * n;
        String formatted = String.format("%d^2 = %d", n, output);
        return IResponse.Answer.createAnswer(BigDecimal.valueOf(output), OperationEnum.SQUARE, IResponse.Error.EMPTY, formatted);
    }

    public List<IResponse.Answer> multiplicationTable(int number) {
        return IntStream.rangeClosed(1, 10)
          .mapToObj(i -> {
              int output = i * number;
              String formatted = String.format("%d x %d = %d", number, i, output);
              return IResponse.Answer.createAnswer(BigDecimal.valueOf(output), OperationEnum.MULTIPLICATION_TABLE, IResponse.Error.EMPTY, formatted);
          })
          .toList();
    }

}
