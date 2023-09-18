package io.gigabyte.labs.playground.dto;

import java.time.LocalDateTime;

public record Response(

  LocalDateTime localDateTime,
  int output,
  String description) {

    public static Response createResponse(int output, String description) {
        return new Response(LocalDateTime.now(), output, description);
    }

}
