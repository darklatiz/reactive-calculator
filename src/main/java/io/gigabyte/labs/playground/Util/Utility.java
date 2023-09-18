package io.gigabyte.labs.playground.Util;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class Utility {

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            log.error("Error:", e);
        }
    }

}
