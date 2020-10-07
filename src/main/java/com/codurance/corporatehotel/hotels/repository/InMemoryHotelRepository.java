package com.codurance.corporatehotel.hotels.repository;

import com.codurance.corporatehotel.hotels.model.Hotel;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryHotelRepository implements HotelRepository {

  private final Map<Integer, Hotel> hotels;

  public InMemoryHotelRepository() {
    this.hotels = new HashMap<>();
  }

  public void persist(Integer hotelId, String hotelName) {
    Hotel hotel = new Hotel();
    hotel.setId(hotelId);
    hotel.setName(hotelName);
    this.hotels.put(hotelId, hotel);
  }

  public Hotel findById(Integer hotelId) {
    return this.hotels.get(hotelId);
  }
}
