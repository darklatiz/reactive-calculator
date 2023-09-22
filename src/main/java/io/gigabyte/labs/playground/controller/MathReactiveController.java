package io.gigabyte.labs.playground.controller;

import io.gigabyte.labs.playground.dto.IResponse;
import io.gigabyte.labs.playground.dto.MultiplyCommand;
import io.gigabyte.labs.playground.dto.Response;
import io.gigabyte.labs.playground.exception.InputValidationException;
import io.gigabyte.labs.playground.service.ReactiveMathService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/reactive/math")
public class MathReactiveController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MathReactiveController.class);
    private final ReactiveMathService mathService;

    public MathReactiveController(ReactiveMathService reactiveMathService) {
        this.mathService = reactiveMathService;
    }

    @GetMapping("/square/{number}")
    public Mono<IResponse.Answer> getSquare(@PathVariable("number") int number) {
        if (number < -50 || number > 50) {
            throw new InputValidationException(number, "SQUARE");
        }
        return mathService.square(number);
    }

    @GetMapping("/table/{number}")
    public Flux<IResponse.Answer> getTable(@PathVariable("number") int number) {
        return mathService.table(number);
    }

    @GetMapping(value = "/table/{number}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<IResponse.Answer> getTableStream(@PathVariable("number") int number) {
        //AbstractJackson2Encoder
        // When Adding to Get Mapping produces = MediaType.TEXT_EVENT_STREAM_VALUE a produced elem is consumed by the browser
        return mathService.table(number);
    }

    @PostMapping("/multiplies")
    public Mono<Response> multiply(
      @RequestBody Mono<MultiplyCommand> multiplyCommand,
      @RequestHeader Map<String, String> headers
    ) {
        log.info("Request Headers: {}", headers);
        return mathService.multiply(multiplyCommand);
    }
}
