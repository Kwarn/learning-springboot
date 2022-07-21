package com.example.demo;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class Response {

  @Getter
  @Setter
  @NotBlank(message = "Missing 'message' field")
  private String message;

  @Getter
  @Setter
  @NotBlank(message = "Missing 'status' field")
  private HttpStatus status;

  public Response(String message, HttpStatus status) {
    this.message = message;
    this.status = status;
  }
}
