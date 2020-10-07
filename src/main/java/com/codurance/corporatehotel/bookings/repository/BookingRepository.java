package com.codurance.corporatehotel.bookings.repository;

import com.codurance.corporatehotel.bookings.model.Booking;
import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.model.Room;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository {

  void persist(Booking booking);

  List<Room> findAvailableRooms(RoomTypes roomType, LocalDateTime checkIn, LocalDateTime checkOut);
}
