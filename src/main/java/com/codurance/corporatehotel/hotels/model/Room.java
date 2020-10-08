package com.codurance.corporatehotel.hotels.model;

import com.codurance.corporatehotel.common.model.RoomTypes;
import javax.validation.constraints.NotNull;


public class Room {

  @NotNull(message = "cannot be null")
  private Integer hotelId;
  @NotNull(message = "cannot be null")
  private Integer roomNumber;
  @NotNull(message = "cannot be null")
  private RoomTypes roomType;

  public Integer getHotelId() {
    return hotelId;
  }

  public void setHotelId(Integer hotelId) {
    this.hotelId = hotelId;
  }

  public Integer getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
  }

  public RoomTypes getRoomType() {
    return roomType;
  }

  public void setRoomType(RoomTypes roomType) {
    this.roomType = roomType;
  }
}
