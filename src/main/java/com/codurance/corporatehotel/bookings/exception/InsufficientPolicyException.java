package com.codurance.corporatehotel.bookings.exception;

public class InsufficientPolicyException extends RuntimeException {

  public InsufficientPolicyException() {
    super("The policy is not sufficient for the requested action.");
  }

}
