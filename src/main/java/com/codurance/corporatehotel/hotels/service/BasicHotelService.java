package com.codurance.corporatehotel.hotels.service;

import com.codurance.corporatehotel.common.exception.HotelNotExistsException;
import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.exception.HotelExistsException;
import com.codurance.corporatehotel.hotels.model.Hotel;
import com.codurance.corporatehotel.hotels.model.Room;
import com.codurance.corporatehotel.hotels.repository.HotelRepository;
import com.codurance.corporatehotel.hotels.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicHotelService implements HotelService {

  private HotelRepository hotelRepository;
  private RoomRepository roomRepository;

  @Autowired
  public BasicHotelService(HotelRepository hotelRepository, RoomRepository roomRepository) {
    this.hotelRepository = hotelRepository;
    this.roomRepository = roomRepository;
  }

  public void addHotel(Integer hotelId, String hotelName) {
    if (this.findHotelById(hotelId) != null) {
      throw new HotelExistsException();
    }

    this.hotelRepository.persist(hotelId, hotelName);
  }

  public void setRoom(Integer hotelId, Integer roomNumber, RoomTypes roomType) {
    if (this.hotelRepository.findById(hotelId) == null) {
      throw new HotelNotExistsException();
    }

    Room room = this.roomRepository.findByHotelAndNumber(hotelId, roomNumber);

    if (room != null) {
      this.roomRepository.update(hotelId, roomNumber, roomType);
    } else {
      this.roomRepository.persist(hotelId, roomNumber, roomType);
    }

  }

  public Hotel findHotelById(Integer hotelId) {
    return this.hotelRepository.findById(hotelId);
  }
}
