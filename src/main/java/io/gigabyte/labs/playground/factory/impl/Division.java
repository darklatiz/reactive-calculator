package io.gigabyte.labs.playground.factory.impl;

import io.gigabyte.labs.playground.dto.OperationEnum;
import io.gigabyte.labs.playground.factory.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division implements Operation {

    private final String num1PlainString;
    private final String num2PlainString;
    private final BigDecimal result;

    public Division(BigDecimal num1, BigDecimal num2) {
        this.num1PlainString = num1.setScale(2, RoundingMode.HALF_UP).toPlainString();
        this.num2PlainString = num2.setScale(2, RoundingMode.HALF_UP).toPlainString();
        this.result = num1.divide(num2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculate() {
        return this.result;
    }

    @Override
    public String description() {
        BigDecimal division = calculate();
        String divPlainString = division.setScale(2, RoundingMode.HALF_UP).toPlainString();
        return String.format("%s * %s = %s", num1PlainString, num2PlainString, divPlainString);
    }

    @Override
    public String resultToPlainString() {
        return this.result.setScale(3, RoundingMode.CEILING).toPlainString();
    }

    @Override
    public OperationEnum operationEnum() {
        return OperationEnum.DIVISION;
    }
}
