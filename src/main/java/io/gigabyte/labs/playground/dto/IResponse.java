package io.gigabyte.labs.playground.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public interface IResponse {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    record Answer(
      @JsonProperty("answer")
      BigDecimal output,
      @JsonProperty("operation")
      OperationEnum operationEnum,

      @JsonProperty("answer_readable")
      String ansReadable,

      Error error,

      @JsonProperty("time_stamp_calculation")
      LocalDateTime tsCalculated) {
        public static Answer createAnswer(BigDecimal out, OperationEnum op, Error error, String ansReadable) {
            return new Answer(out, op, ansReadable, error, LocalDateTime.now());
        }

        public static Answer createAnswerWithError(Error error) {
            return new Answer(null, null, null, error, LocalDateTime.now());
        }
    }

    record MultipleAnswer(

      @JsonProperty("answers")
      List<Answer> answers

    ) { }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    record Error(
      @JsonProperty("code")
      Integer code,
      @JsonProperty("error")
      String message,
      @JsonProperty("additional_info")
      String additionalInfo) {

        public static final Error EMPTY = new Error(null, null, null);
    }
}
