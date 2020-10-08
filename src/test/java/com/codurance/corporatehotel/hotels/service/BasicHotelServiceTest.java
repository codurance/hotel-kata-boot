package com.codurance.corporatehotel.hotels.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.exception.HotelExistsException;
import com.codurance.corporatehotel.hotels.model.Hotel;
import com.codurance.corporatehotel.hotels.model.Room;
import com.codurance.corporatehotel.hotels.repository.HotelRepository;
import com.codurance.corporatehotel.hotels.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BasicHotelServiceTest {

  @InjectMocks
  private BasicHotelService hotelService;

  @Mock
  private HotelRepository hotelRepositoryStub;

  @Mock
  private RoomRepository roomRepositoryStub;

  private final Integer roomNumber = 1;
  private final RoomTypes roomType = RoomTypes.STANDARD;
  private final Integer hotelId = 1;
  private final String hotelName = "Marriott - London";

  @BeforeEach
  private void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldAddHotel() throws Exception {
    // when
    hotelService.addHotel(hotelId, hotelName);

    // then
    verify(hotelRepositoryStub).persist(hotelId, hotelName);
  }

  @Test
  public void shouldNotAddHotel() throws Exception {
    // given
    doThrow(HotelExistsException.class).when(hotelRepositoryStub).persist(hotelId, hotelName);

    // when
    // then
    assertThrows(HotelExistsException.class, () -> hotelService.addHotel(hotelId, hotelName));

  }

  @Test
  public void shouldSetRoom() throws Exception {
    // given
    given(hotelRepositoryStub.findById(hotelId)).willReturn(new Hotel());

    // when
    hotelService.setRoom(hotelId, roomNumber, roomType);

    // then
    verify(roomRepositoryStub).persist(hotelId, roomNumber, roomType);
  }

  @Test
  public void shouldUpdateRoom() {
    // given
    given(hotelRepositoryStub.findById(hotelId)).willReturn(new Hotel());
    given(roomRepositoryStub.findByHotelAndNumber(hotelId, roomNumber)).willReturn(new Room());

    // when
    hotelService.setRoom(hotelId, roomNumber, roomType);

    // then
    verify(roomRepositoryStub).update(hotelId, roomNumber, roomType);
    verify(roomRepositoryStub, times(0)).persist(hotelId, roomNumber, roomType);
  }

}