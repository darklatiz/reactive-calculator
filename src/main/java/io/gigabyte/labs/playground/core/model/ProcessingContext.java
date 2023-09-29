package io.gigabyte.labs.playground.core.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProcessingContext {

    private final String jsonString;
    private String jsonStringWithoutSpaces;
    private List<String> tokens;
    private Map<String, String> keyValuePairs;
    private Map<String, Boolean> results;

    public ProcessingContext(String jsonString) {
        this.jsonString = jsonString;
        this.jsonStringWithoutSpaces = removeWhiteSpaces();
        if (Objects.isNull(results)) {
            this.results = new HashMap<>();
        }
    }

    public String getJsonString() {
        return jsonString;
    }

    public String getJsonStringWithoutSpaces(){
        return jsonStringWithoutSpaces;
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

    private String removeWhiteSpaces() {
        return this.jsonString.replaceAll("\\s+", "");
    }
}
