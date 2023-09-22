package io.gigabyte.labs.playground.Util;


import java.time.Duration;

public class Utility {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Utility.class);

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            log.error("Error:", e);
        }
    }

}
