package io.gigabyte.labs.playground.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InputFailedValidationResponse(

  @JsonProperty("error_code")
  int errorCode,
  int input,
  String operation,
  String message
) {

}
