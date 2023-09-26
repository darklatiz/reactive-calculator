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
    public void process(ProcessingContext processingContext) throws JsonParsingException {
        String removedWhiteSpaces = processingContext.removeWhiteSpaces();
        if (Parser.isNullOrEmpty.test(removedWhiteSpaces)) {
            throw new JsonParsingException("Quotes are not balanced: " + processingContext.getJsonString());
        }

        String jsonString = processingContext.getJsonString();
        boolean insideString = false;

        for (int i = 0; i < jsonString.length(); i++) {
            char ch = jsonString.charAt(i);
            if (ch == '"' && (i == 0 || jsonString.charAt(i - 1) != '\\')) {
                insideString = !insideString; // Toggle the flag
            }
        }



        if (insideString) {
            throw new JsonParsingException("Unbalanced quotes");
        }

        // If this step is successful and there's a next step, invoke it
        if (next != null) {
            next.process(processingContext);
        }

    }

    public static void main(String[] args) {
        log.info("No vale la pena");
    }
}
