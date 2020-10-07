package com.codurance.corporatehotel.bookings.exception;

public class NoRoomAvailableException extends RuntimeException {

  public NoRoomAvailableException() {
    super("There are no more room available with requested specification.");
  }

}
