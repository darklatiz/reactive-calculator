package io.gigabyte.labs.playground.factory;

import io.gigabyte.labs.playground.dto.OperationEnum;

import java.math.BigDecimal;

public interface Operation {

    BigDecimal calculate();

    String description();

    String resultToPlainString();

    OperationEnum operationEnum();
}
