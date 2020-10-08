package com.codurance.corporatehotel.hotels.resource;

import com.codurance.corporatehotel.hotels.model.Hotel;
import com.codurance.corporatehotel.hotels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotel")
public class HotelResource {

  private final HotelService hotelService;

  @Autowired
  public HotelResource(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Hotel> getHotelById(@PathVariable final String id) {
    return new ResponseEntity<>(hotelService.findHotelById(Integer.valueOf(id)), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Hotel> addHotel(@RequestBody final Hotel hotel) {
    hotelService.addHotel(hotel.getId(), hotel.getName());
    Hotel newHotel = hotelService.findHotelById(hotel.getId());
    return new ResponseEntity<>(newHotel, HttpStatus.CREATED);
  }
}
