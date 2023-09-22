package io.gigabyte.labs.playground;

import io.gigabyte.labs.playground.dto.IResponse;
import io.gigabyte.labs.playground.dto.Response;
import io.gigabyte.labs.playground.exception.InputValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class SingleResponseTest extends BaseTest {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SingleResponseTest.class);

    @Autowired
    private WebClient webClient;

    @Test
    void block_test() {
        IResponse.Answer block = this.webClient
          .get()
          .uri("router/math/square/{input}", 50)
          .retrieve()
          .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(InputValidationException.class))
          .bodyToMono(IResponse.Answer.class)
          .block();

        Assertions.assertNotNull(block);
    }



}
