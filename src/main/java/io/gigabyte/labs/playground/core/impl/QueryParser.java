package io.gigabyte.labs.playground.core.impl;

import io.gigabyte.labs.playground.core.Parser;
import io.gigabyte.labs.playground.core.exception.ParserException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class QueryParser implements Parser<Map<String, String>> {

    private static final String LIST_DELIMITER = "list_delimiter";
    Predicate<String> usingURIClass = urlString -> {
        try {
            URI uri = new URI(urlString);
            return "http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme());
        } catch (URISyntaxException e) {
            return false;
        }
    };

    @Override
    public Map<String, String> parse(String strToParse) throws ParserException {
        if (Parser.isNullOrEmpty.test(strToParse)) {
            return new HashMap<>();
        }

        Map<String, String> parsedData = new HashMap<>();
        // http://www.bla.com/?as=1&df=3
        if (isValidHttpUrl(strToParse, usingURIClass)) {
            var qryParams = getQueryString(strToParse);
            var split = qryParams.split("&");

            for (String s : split) {
                String[] splitted = s.split("=");
                String decodedValue = decode(splitted[1]);
                parsedData.put(splitted[0], decodedValue);
            }

        } else {
            throw new ParserException("URL: " + strToParse + " is not well formed");
        }
        return parsedData;
    }

    private String decode(String s) {
        return URLDecoder.decode(s, StandardCharsets.UTF_8);
    }

    private static String getQueryString(String url) {
        int queryStart = url.indexOf("?");
        if (queryStart == -1) {
            return "";
        }
        return url.substring(queryStart + 1);
    }

    public static boolean isValidHttpUrl(String urlString, Predicate<String> validator) {
        return validator.test(urlString);
    }
}
