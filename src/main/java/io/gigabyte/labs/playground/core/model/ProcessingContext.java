package io.gigabyte.labs.playground.core.model;

import java.util.List;
import java.util.Map;

public class ProcessingContext {

    private final String jsonString;
    private List<String> tokens;
    private Map<String, String> keyValuePairs;

    public ProcessingContext(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getJsonString() {
        return jsonString;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public Map<String, String> getKeyValuePairs() {
        return keyValuePairs;
    }

    public void setKeyValuePairs(Map<String, String> keyValuePairs) {
        this.keyValuePairs = keyValuePairs;
    }

    public String removeWhiteSpaces() {
        return this.jsonString.replaceAll("\\s+", "");
    }
}
