package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class Response {

  @NotBlank(message = "Missing 'message' field")
  private final String message;

  @NotBlank(message = "Missing 'status' field")
  private final HttpStatus status;

  @NotBlank(message = "Missing 'statusCode' field")
  private final Integer statusCode;
}
