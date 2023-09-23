package io.gigabyte.labs.playground.core;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public interface Parser {

    Predicate<String> notNullAndNotEmpty = s -> Objects.nonNull(s) && s.trim().isEmpty();
    Predicate<String> isNullOrEmpty = s -> Objects.isNull(s) || s.trim().isEmpty();

    Map<String, String> parse(String strToParse);

}
