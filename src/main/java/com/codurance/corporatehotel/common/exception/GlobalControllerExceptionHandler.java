package com.codurance.corporatehotel.common.exception;

import com.codurance.corporatehotel.common.model.Error;
import com.codurance.corporatehotel.hotels.exception.HotelExistsException;
import com.codurance.corporatehotel.hotels.exception.HotelNotExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

  @ExceptionHandler(HotelNotExistsException.class)
  public ResponseEntity<Error> handleHotelNotExistsException(
      HotelNotExistsException exception) {
    Error error = new Error()
        .message(exception.getMessage())
        .statusCode(404)
        .timestamp(LocalDateTime.now()
            .format((DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(HotelExistsException.class)
  public ResponseEntity<Error> handleHotelExistsException(
      HotelExistsException exception) {
    Error error = new Error()
        .message(exception.getMessage())
        .statusCode(400)
        .timestamp(LocalDateTime.now()
            .format((DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
