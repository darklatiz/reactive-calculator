package io.gigabyte.labs.playground.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MultiplyCommand(
  @JsonProperty("first_number")
  int firstNumber,
  @JsonProperty("second_number")
  int secondNumber) {

}
