package com.codurance.corporatehotel.bookings.service;

import com.codurance.corporatehotel.bookings.exception.InsufficientPolicyException;
import com.codurance.corporatehotel.bookings.exception.NoRoomAvailableException;
import com.codurance.corporatehotel.bookings.model.Booking;
import com.codurance.corporatehotel.bookings.repository.BookingRepository;
import com.codurance.corporatehotel.common.exception.HotelNotExistsException;
import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.service.HotelService;
import com.codurance.corporatehotel.policies.service.PolicyService;
import com.codurance.corporatehotel.utils.IdGenerator;

import java.time.LocalDateTime;

public class BasicBookingService implements BookingService{

    private IdGenerator idGenerator = new IdGenerator();
    private BookingRepository bookingRepository;
    private PolicyService policyService;
    private HotelService hotelService;

    public BasicBookingService(IdGenerator idGenerator, BookingRepository bookingRepository, PolicyService policyService, HotelService hotelService) {
        this.idGenerator = idGenerator;
        this.bookingRepository = bookingRepository;
        this.policyService = policyService;
        this.hotelService = hotelService;
    }

    public Booking book(Integer employeeId, Integer hotelId, RoomTypes roomType,
                        LocalDateTime checkIn, LocalDateTime checkOut) {
        this.validate(employeeId, hotelId, roomType, checkIn, checkOut);

        Booking booking = new Booking();

        booking.setUuid(this.idGenerator.generate());

        booking.setEmployeeId(employeeId);
        booking.setHotelId(hotelId);
        booking.setRoomType(roomType);
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);

        this.bookingRepository.persist(booking);

        return booking;
    }

    private void validate(Integer employeeId, Integer hotelId, RoomTypes roomType,
                          LocalDateTime checkIn, LocalDateTime checkOut) {
        if (this.hotelService.findHotelById(hotelId) == null){
            throw new HotelNotExistsException();
        }

        if (!this.policyService.isBookingAllowed(employeeId, roomType)){
            throw new InsufficientPolicyException();
        }

        if (this.bookingRepository.findAvailableRooms(roomType, checkIn, checkOut) == null){
            throw new NoRoomAvailableException();
        }
    }
}
