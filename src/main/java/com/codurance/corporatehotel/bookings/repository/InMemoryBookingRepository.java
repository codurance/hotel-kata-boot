package com.codurance.corporatehotel.bookings.repository;

import com.codurance.corporatehotel.bookings.model.Booking;
import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.model.Room;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InMemoryBookingRepository implements BookingRepository {

  public void persist(Booking booking) {

  }

  public List<Room> findAvailableRooms(RoomTypes roomType, LocalDateTime checkIn,
      LocalDateTime checkOut) {
    return new ArrayList<>();
  }
}
