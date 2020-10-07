package com.codurance.corporatehotel.hotels.exception;

public class HotelExistsException extends RuntimeException {

  public HotelExistsException() {
    super("A hotel with given id is already in the system.");
  }

}
