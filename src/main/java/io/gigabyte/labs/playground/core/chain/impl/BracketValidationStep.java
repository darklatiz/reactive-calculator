package io.gigabyte.labs.playground.core.chain.impl;

import io.gigabyte.labs.playground.core.Parser;
import io.gigabyte.labs.playground.core.chain.ProcessingStep;
import io.gigabyte.labs.playground.core.exception.JsonParsingException;
import io.gigabyte.labs.playground.core.model.ProcessingContext;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class BracketValidationStep implements ProcessingStep {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BracketValidationStep.class);
    private final Deque<Character> brackets;
    private ProcessingStep next;
    public BracketValidationStep() {
        this.brackets = new ArrayDeque<>();
    }

    @Override
    public void setNext(ProcessingStep nextStep) {
        this.next = nextStep;
    }

    @Override
    public void process(ProcessingContext context) throws JsonParsingException {
        String removedWhiteSpaces = context.removeWhiteSpaces();
        if (Parser.isNullOrEmpty.test(removedWhiteSpaces)) {
            throw new JsonParsingException("Json String is null: " + removedWhiteSpaces);
        }

        Boolean isValid = isBracketBalanced(removedWhiteSpaces);

        if (Boolean.FALSE.equals(isValid)) {
            throw new JsonParsingException("BracketValidation Step Exception: Brackets not balanced for json: " + removedWhiteSpaces);
        }

        if (Objects.nonNull(this.next)) {
            this.next.process(context);
        }

    }

    private Boolean isBracketBalanced(String jsonString) {
        if (Parser.isNullOrEmpty.test(jsonString)) {
            return Boolean.FALSE;
        }

        for (int i = 0; i < jsonString.length(); i++) {
            var ch = jsonString.charAt(i);
            if (ch == '[' || ch == '{') {
                this.brackets.push(ch);
                continue;
            }

            if (this.brackets.isEmpty()) {
                return Boolean.FALSE;
            }

            var check = brackets.peek();
            switch (ch) {
                case ']':
                    if (check == '{' || check == '"') {
                        return false;
                    }
                    this.brackets.pop();
                    break;
                case '}':
                    if (check == '[' || check == '"')
                        return false;
                    this.brackets.pop();
                    break;
                default:
                    log.debug("Not a bracket: {}", ch);
            }

        }
        return this.brackets.isEmpty();
    }
}
