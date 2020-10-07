package com.codurance.corporatehotel.bookings.service;

import com.codurance.corporatehotel.bookings.model.Booking;
import com.codurance.corporatehotel.common.model.RoomTypes;
import java.time.LocalDateTime;

public interface BookingService {

  Booking book(Integer employeeId, Integer hotelId, RoomTypes roomType,
      LocalDateTime checkIn, LocalDateTime checkOut);
}
