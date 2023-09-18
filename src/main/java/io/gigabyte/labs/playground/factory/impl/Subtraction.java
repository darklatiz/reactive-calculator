package io.gigabyte.labs.playground.factory.impl;

import io.gigabyte.labs.playground.dto.OperationEnum;
import io.gigabyte.labs.playground.factory.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Subtraction implements Operation {
    private final String num1PlainString;
    private final String num2PlainString;
    private final BigDecimal result;

    public Subtraction(BigDecimal num1, BigDecimal num2) {
        this.num1PlainString = num1.setScale(2, RoundingMode.HALF_UP).toPlainString();
        this.num2PlainString = num2.setScale(2, RoundingMode.HALF_UP).toPlainString();
        this.result = num1.subtract(num2);
    }

    @Override
    public BigDecimal calculate() {
        return result;
    }

    @Override
    public String description() {
        BigDecimal subtraction = calculate();
        String subPlainString = subtraction.setScale(2, RoundingMode.HALF_UP).toPlainString();
        return String.format("%s - %s = %s", num1PlainString, num2PlainString, subPlainString);
    }

    @Override
    public String resultToPlainString() {
        return this.result.setScale(3, RoundingMode.CEILING).toPlainString();
    }

    @Override
    public OperationEnum operationEnum() {
        return OperationEnum.SUBTRACTION;
    }
}
