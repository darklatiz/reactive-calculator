package io.gigabyte.labs.playground.factory.impl;

import io.gigabyte.labs.playground.dto.OperationEnum;
import io.gigabyte.labs.playground.factory.Operation;

import java.math.BigDecimal;

public class Undefined implements Operation {
    @Override
    public BigDecimal calculate() {
        return null;
    }

    @Override
    public String description() {
        return "Operation UNDEFINED";
    }

    @Override
    public String resultToPlainString() {
        return "Operation UNDEFINED";
    }

    @Override
    public OperationEnum operationEnum() {
        return OperationEnum.UNDEFINED;
    }
}
