package io.gigabyte.labs.playground.factory;

import io.gigabyte.labs.playground.factory.impl.Division;
import io.gigabyte.labs.playground.factory.impl.Multiplication;
import io.gigabyte.labs.playground.factory.impl.Subtraction;
import io.gigabyte.labs.playground.factory.impl.Sum;
import io.gigabyte.labs.playground.factory.impl.Undefined;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OperationFactory {

    public Operation createOperation(String operation, BigDecimal num1, BigDecimal num2) {
        return switch (operation) {
            case "+" -> new Sum(num1, num2);
            case "-" -> new Subtraction(num1, num2);
            case "*" -> new Multiplication(num1, num2);
            case "/" -> new Division(num1, num2);
            case null, default -> new Undefined();
        };
    }
}
