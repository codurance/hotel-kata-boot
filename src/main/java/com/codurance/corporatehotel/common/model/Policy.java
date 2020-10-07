package com.codurance.corporatehotel.common.model;

import java.util.ArrayList;
import java.util.List;

public class Policy {

  private List<RoomTypes> roomTypes = new ArrayList<>();

  public List<RoomTypes> getRoomTypes() {
    return roomTypes;
  }

  public void setRoomTypes(List<RoomTypes> roomTypes) {
    this.roomTypes = roomTypes;
  }

  public void addRoomType(RoomTypes roomType) {
    this.roomTypes.add(roomType);
  }
}
