package io.gigabyte.labs.playground;


import io.gigabyte.labs.playground.core.Parser;
import io.gigabyte.labs.playground.core.exception.ParserException;
import io.gigabyte.labs.playground.core.impl.ListParser;
import io.gigabyte.labs.playground.core.impl.QueryParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class CoreTests {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CoreTests.class);
    Parser<Map<String, String>> urlParser = new QueryParser();
    Parser<List<String>> listParser = new ListParser();



    @Test
    void test_url_parser() throws ParserException {
        //
        String url = "https://spring.io/blog/2023/09/20/hello-java-21?fbclid=IwAR0OqN3B9sL3R3W2w5Ei0Y-wNjssU0qW7_VT6w60fqzPZeUblAaymgZ62ek_aem_AY9U133sMoCCcOqfEiYJjPdBAg1jjmFk2mw66jpjJdfl6r87Fk9uM008tlRF1cfCvLI" ;
        String url1 = "https://www.google.com/search?q=Building%20an%20Expression%20Tree&rlz=1C1ONGR_enUS991US991&sourceid=chrome&ie=UTF-8#ip=1";

        Map<String, String> parse = urlParser.parse(url);
        Map<String, String> parsed = urlParser.parse(url1);

        log.info("Qry Params = {}", parse);
        log.info("Qry Params = {}", parsed);

        Assertions.assertEquals(1, parse.size());
        Assertions.assertEquals(4, parsed.size());

        Assertions.assertEquals(0, urlParser.parse("").size());
        Assertions.assertEquals(0, urlParser.parse(null).size());
    }

    @Test
    void test_listPArser() throws ParserException {

        List<String> parse = listParser.parse("1,2,3,4,5,6,7,8,9,0");
        log.info("list: {}",  parse);

        Assertions.assertNotNull(parse);
        Assertions.assertEquals(10, parse.size());

    }
    @Test
    void test_expected_exception() {

        String url_01 = "http//spring.io/blog/2023/09/20/hello-java-21?fbclid=IwAR0OqN3B9sL3R3W2w5Ei0Y-wNjssU0qW7_VT6w60fqzPZeUblAaymgZ62ek_aem_AY9U133sMoCCcOqfEiYJjPdBAg1jjmFk2mw66jpjJdfl6r87Fk9uM008tlRF1cfCvLI" ;
        String url_03 = "h://spring/blog/2023/09/20/hello-java-21?fbclid=IwAR0OqN3B9sL3R3W2w5Ei0Y-wNjssU0qW7_VT6w60fqzPZeUblAaymgZ62ek_aem_AY9U133sMoCCcOqfEiYJjPdBAg1jjmFk2mw66jpjJdfl6r87Fk9uM008tlRF1cfCvLI" ;

        ParserException malformedURLException = Assertions.assertThrows(ParserException.class, () -> urlParser.parse(url_01));

        Assertions.assertNotNull(malformedURLException);
        Assertions.assertNotNull(malformedURLException.getMessage());
        Assertions.assertFalse(malformedURLException.getMessage().trim().isEmpty());

        malformedURLException = Assertions.assertThrows(ParserException.class, () -> urlParser.parse(url_03));

        Assertions.assertNotNull(malformedURLException);
        Assertions.assertNotNull(malformedURLException.getMessage());
        Assertions.assertFalse(malformedURLException.getMessage().trim().isEmpty());


    }

}
