package io.gigabyte.labs.playground;

import io.gigabyte.labs.playground.dto.Response;
import io.gigabyte.labs.playground.exception.InputValidationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class SingleResponseTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    void block_test() {
        Response block = this.webClient
          .get()
          .uri("router/math/square/{input}", 5)
          .retrieve()
          .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(InputValidationException.class))
          .bodyToMono(Response.class)
          .block();

        Assertions.assertNotNull(block);
    }



}
