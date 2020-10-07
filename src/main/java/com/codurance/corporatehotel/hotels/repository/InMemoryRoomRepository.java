package com.codurance.corporatehotel.hotels.repository;

import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.model.Room;

public class InMemoryRoomRepository implements RoomRepository {

    public void persist(Integer hotelId, Integer roomNumber, RoomTypes roomType) {

    }

    public Room findByHotelAndNumber(Integer hotelId, Integer roomNumber){
        return null;
    }

    public void update(Integer hotelId, Integer roomNumber, RoomTypes roomType) {

    }
}
