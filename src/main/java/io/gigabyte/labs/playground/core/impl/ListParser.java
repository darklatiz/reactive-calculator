package io.gigabyte.labs.playground.core.impl;

import io.gigabyte.labs.playground.core.Parser;
import io.gigabyte.labs.playground.core.exception.ParserException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListParser implements Parser<List<String>> {
    @Override
    public List<String> parse(String strToParse) throws ParserException {
        if (Parser.isNullOrEmpty.test(strToParse)) {
            return new ArrayList<>();
        }
        return Arrays.stream(strToParse.split(","))
          .toList();
    }
}
