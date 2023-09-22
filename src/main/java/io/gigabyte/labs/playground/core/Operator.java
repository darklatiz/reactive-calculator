package io.gigabyte.labs.playground.core;

public enum Operator {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private String operatorString;

    Operator(String op) {
        this.operatorString = op;
    }

    public static Operator parse(String operator) {
        for (Operator op : Operator.values()) {
            if (op.operatorString.equals(operator)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unrecognized Operator: " + operator);
    }

    public String operatorPlainString() {
        return this.operatorString;
    }
}
