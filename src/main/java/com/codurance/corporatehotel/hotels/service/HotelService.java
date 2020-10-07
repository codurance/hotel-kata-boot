package com.codurance.corporatehotel.hotels.service;

import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.model.Hotel;

public interface HotelService {

    void addHotel(Integer hotelId, String hotelName);

    void setRoom(Integer hotelId, Integer roomNumber, RoomTypes roomType);

    Hotel findHotelById(Integer hotelId);
}
