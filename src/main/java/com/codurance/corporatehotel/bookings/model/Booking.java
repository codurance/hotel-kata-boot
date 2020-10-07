package com.codurance.corporatehotel.bookings.model;

import com.codurance.corporatehotel.common.model.RoomTypes;

import java.time.LocalDateTime;

public class Booking {

    private Integer employeeId;
    private Integer hotelId;
    private RoomTypes roomType;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Integer uuid;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public RoomTypes getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypes roomType) {
        this.roomType = roomType;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime _checkOut) {
        this.checkOut = _checkOut;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }
}
