package io.gigabyte.labs.playground.factory.impl;

import io.gigabyte.labs.playground.dto.OperationEnum;
import io.gigabyte.labs.playground.factory.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sum implements Operation {
    private final String num1PlainString;
    private final String num2PlainString;
    private final BigDecimal result;
    public static final OperationEnum operation = OperationEnum.ADD;

    public Sum(BigDecimal num1, BigDecimal num2) {
        this.num1PlainString = num1.setScale(2, RoundingMode.HALF_UP).toPlainString();
        this.num2PlainString = num2.setScale(2, RoundingMode.HALF_UP).toPlainString();
        this.result = num1.add(num2);
    }

    @Override
    public BigDecimal calculate() {
        return this.result;
    }

    @Override
    public String description() {
        BigDecimal sum = calculate();
        String sumPlainString = sum.setScale(2, RoundingMode.HALF_UP).toPlainString();
        return String.format("%s + %s = %s", num1PlainString, num2PlainString, sumPlainString);
    }

    @Override
    public String resultToPlainString() {
        return this.result.setScale(3, RoundingMode.CEILING).toPlainString();
    }

    @Override
    public OperationEnum operationEnum() {
        return OperationEnum.ADD;
    }
}
