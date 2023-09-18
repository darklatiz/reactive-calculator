package io.gigabyte.labs.playground.config;

import io.gigabyte.labs.playground.dto.IResponse;
import io.gigabyte.labs.playground.exception.InputValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
public class RouterConfig {

    private final RequestHandler requestHandler;

    public RouterConfig(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> highLevelRouter() {
        return RouterFunctions.route()
          .path("router/math", this::serverResponseRouterFunction)
          .build();
    }

    private RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return RouterFunctions.route()
          .GET("square/{number}", requestHandler::squareHandler)
          .GET("table/{number}", requestHandler::multiplicationTableHandler)
          .GET("table/{number}/stream", requestHandler::streamMultiplicationTableHandler)
          .POST("multiplies", requestHandler::multiplyHandler)
          .GET("calculator/{number1}/{number2}", requestHandler::calculator)
          .onError(InputValidationException.class, exceptionHandler())
          .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler() {
        return (error, request) -> {
            InputValidationException ex = (InputValidationException) error;
            IResponse.Error error1 = new IResponse.Error(ex.getErrorCode(), "Error in operation: ".concat(ex.getOperation().toUpperCase()), ex.getMessage());
            IResponse.Answer data = IResponse.Answer.createAnswerWithError(error1);
            return ServerResponse.badRequest().bodyValue(data);
        };
    }

}
