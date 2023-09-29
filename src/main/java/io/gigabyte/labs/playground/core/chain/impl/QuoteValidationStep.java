package io.gigabyte.labs.playground.core.chain.impl;

import io.gigabyte.labs.playground.core.Parser;
import io.gigabyte.labs.playground.core.chain.ProcessingStep;
import io.gigabyte.labs.playground.core.exception.JsonParsingException;
import io.gigabyte.labs.playground.core.model.ProcessingContext;

import java.util.Objects;

public class QuoteValidationStep implements ProcessingStep {

    private ProcessingStep next;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(QuoteValidationStep.class);

    @Override
    public void setNext(ProcessingStep nextStep) {
        this.next = nextStep;
    }

    @Override
    public void process(ProcessingContext context) throws JsonParsingException {
        String jsonStringWithoutSpaces = context.getJsonStringWithoutSpaces();
        if (Parser.isNullOrEmpty.test(jsonStringWithoutSpaces)) {
            throw new JsonParsingException("Quotes are not balanced: " + context.getJsonString());
        }

        State state = State.OUTSIDE_STRING;

        for (int i = 0; i < jsonStringWithoutSpaces.length(); i++) {
            char ch = jsonStringWithoutSpaces.charAt(i);

            switch (state) {
                case OUTSIDE_STRING:
                    if (ch == '"') {
                        state = State.INSIDE_STRING;
                    }
                    break;

                case INSIDE_STRING:
                    if (ch == '"' && (i == 0 || jsonStringWithoutSpaces.charAt(i - 1) != '\\')) {
                        state = State.EXPECTING_COMMA_OR_CLOSING;
                    }
                    break;

                case EXPECTING_COMMA_OR_CLOSING:
                    if (ch == '"') {
                        char precedingChar = jsonStringWithoutSpaces.charAt(i - 1);
                        if (precedingChar != ':' && precedingChar != ',' && precedingChar != '{' && precedingChar != '[') {
                            throw new JsonParsingException("Misplaced quotes at index: " + i);
                        } else {
                            state = State.INSIDE_STRING;
                        }
                    }
                    break;
            }
        }

        if (state == State.INSIDE_STRING) {
            throw new JsonParsingException("Unbalanced quotes");
        }

        if (Objects.nonNull(next)) {
            next.process(context);
        }


    }

    private enum State {
        OUTSIDE_STRING,
        INSIDE_STRING,
        EXPECTING_COMMA_OR_CLOSING
    }

    public static void main(String[] args) {
        log.info("No vale la pena");
    }
}
