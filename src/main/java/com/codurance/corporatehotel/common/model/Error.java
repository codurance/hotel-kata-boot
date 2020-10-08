package com.codurance.corporatehotel.common.model;

public class Error {
  private String message;
  private int statusCode;
  private String timestamp;

  public String getMessage() {
    return message;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public Error message(String message) {
    this.message = message;
    return this;
  }

  public Error statusCode(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public Error timestamp(String timestamp) {
    this.timestamp = timestamp;
    return this;
  }
}
