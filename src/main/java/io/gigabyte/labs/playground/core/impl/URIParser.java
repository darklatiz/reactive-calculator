package io.gigabyte.labs.playground.core.impl;

import io.gigabyte.labs.playground.core.Parser;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class URIParser implements Parser {

    Predicate<String> usingURLClass = urlString -> {
        try {
            URL url = new URL(urlString);
            return "http".equals(url.getProtocol()) || "https".equals(url.getProtocol());
        } catch (MalformedURLException e) {
            return false;
        }
    };

    Predicate<String> usingURIclass = urlString -> {
        try {
            URI uri = new URI(urlString);
            return "http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme());
        } catch (URISyntaxException e) {
            return false;
        }
    };
    @Override
    public Map<String, String> parse(String strToParse) {
        if (Parser.isNullOrEmpty.test(strToParse)) {
            return new HashMap<>();
        }

        Map<String, String> parsedData = new HashMap<>();
        // http://www.bla.com/?as=1&df=3
        if (isValidHttpUrl(strToParse, usingURIclass)) {
            String qryParams = getQueryString(strToParse);
            String[] split = qryParams.split("&");
            for (int i = 0; i < split.length; i++) {
                String[] splitted = split[i].split("=");
                parsedData.put(splitted[0], decode(splitted[1]));
            }
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
