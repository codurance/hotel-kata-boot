package com.codurance.corporatehotel.hotels.repository;

import com.codurance.corporatehotel.hotels.model.Hotel;

public interface HotelRepository {

    void persist(Integer hotelId, String hotelName);

    Hotel findById(Integer hotelId);
}
