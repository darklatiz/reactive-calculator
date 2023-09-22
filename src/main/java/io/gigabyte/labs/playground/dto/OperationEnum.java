package io.gigabyte.labs.playground.dto;

public enum OperationEnum {
    ADD("add"), SUBTRACTION("sub"), DIVISION("div"), MULTIPLICATION("mul"), POWER("pow"), MULTIPLICATION_TABLE("tableMul"), SQUARE("sqr"), UNDEFINED("undef"), FIBONACCI("fib");

    private String shortDescription;

    OperationEnum(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String shortDescription() {
        return this.shortDescription;
    }

}
