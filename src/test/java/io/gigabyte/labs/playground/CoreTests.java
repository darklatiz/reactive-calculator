package io.gigabyte.labs.playground;


import io.gigabyte.labs.playground.core.Parser;
import io.gigabyte.labs.playground.core.impl.URIParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class CoreTests {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CoreTests.class);
    @Test
    void test_url_parser(){
        //
        String url = "https://spring.io/blog/2023/09/20/hello-java-21?fbclid=IwAR0OqN3B9sL3R3W2w5Ei0Y-wNjssU0qW7_VT6w60fqzPZeUblAaymgZ62ek_aem_AY9U133sMoCCcOqfEiYJjPdBAg1jjmFk2mw66jpjJdfl6r87Fk9uM008tlRF1cfCvLI" ;
        String url1 = "https://www.google.com/search?q=Building%20an%20Expression%20Tree&rlz=1C1ONGR_enUS991US991&sourceid=chrome&ie=UTF-8#ip=1";
        Parser urlParser = new URIParser();

        Map<String, String> parse = urlParser.parse(url);
        Map<String, String> parsed = urlParser.parse(url1);

        log.info("Qry Params = {}", parse);
        log.info("Qry Params = {}", parsed);

        Assertions.assertEquals(1, parse.size());
        Assertions.assertEquals(4, parsed.size());

        Assertions.assertEquals(0, urlParser.parse("").size());
        Assertions.assertEquals(0, urlParser.parse(null).size());


    }

}
