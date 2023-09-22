package io.gigabyte.labs.playground.factory.impl.functions;

import io.gigabyte.labs.playground.factory.MathFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Fibonacci implements MathFunction {

    private static final Map<Long, Long> cache = new HashMap<>();

    private Number number;

    private Number result;

    public Fibonacci(Number number) {
        this.number = number;
    }

    public Fibonacci() {}

    public static Long hasBeenCalculated(long data) {
        return cache.get(data);
    }

    private long fib(long n) {
        if (n == 0 || n == 1) {
            return n;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        long res = fib(n - 1) + fib(n - 2);
        cache.put(n, res);
        return res;
    }

    @Override
    public Number resolve() {
        this.result = fib(number.longValue());
        return result;
    }

    @Override
    public String description() {
        return Optional.ofNullable(this.result)
          .map(number1 -> String.format("fib(%d) = %,d", number, number1.longValue()))
          .orElse("Function has not been calculated");
    }

    @Override
    public Number result() {
        if (Objects.nonNull(number)) {
            return cache.get(number.longValue());
        } else {
            return null;
        }
    }

}
