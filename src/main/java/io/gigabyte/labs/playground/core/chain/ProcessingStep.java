package io.gigabyte.labs.playground.core.chain;

import io.gigabyte.labs.playground.core.exception.JsonParsingException;
import io.gigabyte.labs.playground.core.model.ProcessingContext;

public interface ProcessingStep {

    void setNext(ProcessingStep nextStep);
    void process(ProcessingContext processingContext) throws JsonParsingException;

}
