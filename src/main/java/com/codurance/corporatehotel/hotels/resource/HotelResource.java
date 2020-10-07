package com.codurance.corporatehotel.hotels.resource;

import com.codurance.corporatehotel.hotels.model.Hotel;
import com.codurance.corporatehotel.hotels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotel/")
public class HotelResource {

  private final HotelService hotelService;

  @Autowired
  public HotelResource(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping("{id}")
  public Hotel getHotelById(@PathVariable String id) {
    return hotelService.findHotelById(Integer.valueOf(id));
  }
}
