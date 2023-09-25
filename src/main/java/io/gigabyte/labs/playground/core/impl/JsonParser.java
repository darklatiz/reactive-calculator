package io.gigabyte.labs.playground.core.impl;

import io.gigabyte.labs.playground.core.Parser;
import io.gigabyte.labs.playground.core.exception.ParserException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class JsonParser implements Parser<Map<String, String>> {

    private Deque<String> brackets;

    public JsonParser() {
        this.brackets = new ArrayDeque<>();
    }

    @Override
    public Map<String, String> parse(String strToParse) throws ParserException {
        if (Parser.isNullOrEmpty.test(strToParse)) {
            return new HashMap<>();
        }

        return null;
    }
}
