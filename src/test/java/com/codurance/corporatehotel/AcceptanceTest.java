package com.codurance.corporatehotel;

import com.codurance.corporatehotel.bookings.exception.InsufficientPolicyException;
import com.codurance.corporatehotel.bookings.model.Booking;
import com.codurance.corporatehotel.bookings.repository.BookingRepository;
import com.codurance.corporatehotel.bookings.repository.InMemoryBookingRepository;
import com.codurance.corporatehotel.bookings.service.BasicBookingService;
import com.codurance.corporatehotel.bookings.service.BookingService;
import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.companies.repository.CompanyRepository;
import com.codurance.corporatehotel.companies.repository.EmployeeRepository;
import com.codurance.corporatehotel.companies.repository.InMemoryCompanyRepository;
import com.codurance.corporatehotel.companies.repository.InMemoryEmployeeRepository;
import com.codurance.corporatehotel.companies.service.BasicCompanyService;
import com.codurance.corporatehotel.companies.service.CompanyService;
import com.codurance.corporatehotel.hotels.model.Hotel;
import com.codurance.corporatehotel.hotels.repository.HotelRepository;
import com.codurance.corporatehotel.hotels.repository.InMemoryHotelRepository;
import com.codurance.corporatehotel.hotels.repository.InMemoryRoomRepository;
import com.codurance.corporatehotel.hotels.repository.RoomRepository;
import com.codurance.corporatehotel.hotels.service.BasicHotelService;
import com.codurance.corporatehotel.hotels.service.HotelService;
import com.codurance.corporatehotel.policies.repository.InMemoryPolicyRepository;
import com.codurance.corporatehotel.policies.repository.PolicyRepository;
import com.codurance.corporatehotel.policies.service.BasicPolicyService;
import com.codurance.corporatehotel.policies.service.PolicyService;
import com.codurance.corporatehotel.utils.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AcceptanceTest {

    private IdGenerator idGenerator;
    private HotelService hotelService;
    private CompanyService companyService;
    private BookingService bookingService;
    private PolicyService policyService;
    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;
    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;
    private PolicyRepository policyRepository;
    private BookingRepository bookingRepository;

    Integer hotelId = 1;
    Integer employeeId = 2;
    Integer roomNumber = 1;
    Integer companyId = 10;
    List<RoomTypes> roomTypes;
    RoomTypes standardRoomType = RoomTypes.STANDARD;
    RoomTypes masterRoomType = RoomTypes.MASTER_SUITE;
    LocalDateTime checkIn = LocalDateTime.parse("2020-01-01 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    LocalDateTime checkOut = LocalDateTime.parse("2020-01-10 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    String hotelName = "Mariott - London";

    @BeforeEach
    private void init(){
        idGenerator = new IdGenerator();
        hotelRepository = new InMemoryHotelRepository();
        roomRepository = new InMemoryRoomRepository();
        policyRepository = new InMemoryPolicyRepository();
        hotelService = new BasicHotelService(hotelRepository, roomRepository);
        companyRepository = new InMemoryCompanyRepository();
        employeeRepository = new InMemoryEmployeeRepository();
        bookingRepository = new InMemoryBookingRepository();
        companyService = new BasicCompanyService(companyRepository, employeeRepository, policyRepository);
        policyService = new BasicPolicyService(policyRepository, employeeRepository);
        bookingService = new BasicBookingService(idGenerator,
                bookingRepository, policyService, hotelService);

        roomTypes = new ArrayList<>();
    }

    @Test
    public void shouldBookingContainAllExpectedData() throws Exception{
        // given
        hotelService.addHotel(hotelId, hotelName);
        hotelService.setRoom(hotelId, roomNumber, standardRoomType);

        // when
        Booking booking = bookingService.book(employeeId, hotelId, standardRoomType, checkIn, checkOut);

        // then
        assertThat(booking.getEmployeeId()).isEqualTo(employeeId);
        assertThat(booking.getHotelId()).isEqualTo(hotelId);
        assertThat(booking.getRoomType()).isEqualTo(standardRoomType);
        assertThat(booking.getCheckIn()).isEqualTo(checkIn);
        assertThat(booking.getCheckOut()).isEqualTo(checkOut);
    }

    @Test
    public void shouldAllowBooking_givenEmployeeWasDeleted() throws Exception{
        // Given
        companyService.addEmployee(companyId, employeeId);
        roomTypes.add(standardRoomType);
        policyService.setEmployeePolicy(employeeId, roomTypes);

        // When
        companyService.deleteEmployee(employeeId);

        // Then
        assertThat(policyService.isBookingAllowed(employeeId, standardRoomType)).isTrue();
    }

    @Test
    public void shouldFindHotelAfterAdding() throws Exception{
        // given
        hotelService.addHotel(hotelId, hotelName);

        // when
        Hotel hotel = hotelService.findHotelById(hotelId);

        // then
        assertThat(hotel.getId()).isEqualTo(hotelId);
        assertThat(hotel.getName()).isEqualTo(hotelName);
    }

    @Test
    public void shouldBookRoom_givenSufficientCompanyPolicy() throws Exception{
        // Given
        hotelService.addHotel(hotelId, hotelName);
        hotelService.setRoom(hotelId, roomNumber, masterRoomType);

        companyService.addEmployee(companyId, employeeId);

        roomTypes.add(masterRoomType);
        policyService.setCompanyPolicy(companyId, roomTypes);

        // When
        Booking booking = bookingService
                .book(employeeId, hotelId, masterRoomType, checkIn, checkOut);

        // Then
        assertThat(booking).isNotNull();
    }

    @Test
    public void shouldFailBooking_givenInsufficientCompanyPolicy() throws Exception{
        // Given
        hotelService.addHotel(hotelId, hotelName);
        hotelService.setRoom(hotelId, roomNumber, masterRoomType);

        companyService.addEmployee(companyId, employeeId);

        roomTypes.add(standardRoomType);
        policyService.setCompanyPolicy(companyId, roomTypes);

        // When
        // Then
        assertThatThrownBy(() -> bookingService.book(employeeId, hotelId, masterRoomType, checkIn, checkOut))
          .isInstanceOf(InsufficientPolicyException.class);
    }

    @Test
    public void shouldFailBooking_givenInsufficientEmployeePolicy() throws Exception{
        // Given
        hotelService.addHotel(hotelId, hotelName);
        hotelService.setRoom(hotelId, roomNumber, masterRoomType);

        companyService.addEmployee(companyId, employeeId);

        roomTypes.add(standardRoomType);
        roomTypes.add(masterRoomType);
        policyService.setCompanyPolicy(companyId, roomTypes);

        List<RoomTypes> employeeRoomTypes = new ArrayList<>();
        employeeRoomTypes.add(RoomTypes.STANDARD);
        policyService.setEmployeePolicy(employeeId, employeeRoomTypes);

        // when
        // Then
        assertThatThrownBy(() -> bookingService.book(employeeId, hotelId, masterRoomType, checkIn, checkOut))
            .isInstanceOf(InsufficientPolicyException.class);
    }
}
