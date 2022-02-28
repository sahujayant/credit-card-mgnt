package com.example.card.credit.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {

  private String errorMessage;
  private HttpStatus httpStatus;
  private String time;

    public ApiError(String errorMessage, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.time = LocalDateTime.now().toString();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "errorMessage='" + errorMessage + '\'' +
                ", httpStatus=" + httpStatus +
                ", time='" + time + '\'' +
                '}';
    }
}
