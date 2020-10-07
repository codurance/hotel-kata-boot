package com.codurance.corporatehotel.hotels.repository;

import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.model.Room;

public interface RoomRepository {

    void persist(Integer hotelId, Integer roomNumber, RoomTypes roomType);

    Room findByHotelAndNumber(Integer hotelId, Integer roomNumber);

    void update(Integer hotelId, Integer roomNumber, RoomTypes roomType);
}
