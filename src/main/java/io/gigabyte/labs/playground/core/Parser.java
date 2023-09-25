package io.gigabyte.labs.playground.core;

import io.gigabyte.labs.playground.core.exception.ParserException;

import java.util.Objects;
import java.util.function.Predicate;

public interface Parser<T> {

    Predicate<String> isNullOrEmpty = s -> Objects.isNull(s) || s.trim().isEmpty();

    T parse(String strToParse) throws ParserException;

}
