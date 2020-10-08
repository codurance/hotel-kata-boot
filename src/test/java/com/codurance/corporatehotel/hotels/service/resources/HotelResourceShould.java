package com.codurance.corporatehotel.hotels.service.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.model.Hotel;
import com.codurance.corporatehotel.hotels.model.Room;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HotelResourceShould {

  private final String GET_URL = "/hotels/1";
  private final String POST_URL = "/hotels";
  private final String POST_ROOM_URL = "/hotels/rooms";
  private final int HOTEL_ID = 1;
  final String HOTEL_NAME = "Risk";
  ObjectMapper objectMapper = new ObjectMapper();

  @Autowired private MockMvc mockMvc;

  @Test
  void not_find_an_hotel_given_is_not_in_the_system() throws Exception {
    mockMvc.perform(get(GET_URL)).andExpect(status().isNotFound());
  }

  @Test
  void create_and_fetch_a_hotel() throws Exception {
    Hotel hotel = new Hotel(HOTEL_ID, HOTEL_NAME);
    String postValue = objectMapper.writeValueAsString(hotel);
    mockMvc
        .perform(post(POST_URL).content(postValue).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    mockMvc.perform((get(GET_URL))).andExpect(status().isOk());
  }

  @Test
  void create_a_hotel_and_add_a_room_to_it() throws Exception {
    Hotel hotel = new Hotel(HOTEL_ID, HOTEL_NAME);
    String hotelBody = createPostBody(hotel);

    final Room room = new Room().roomNumber(1).hotelId(1).roomType(RoomTypes.STANDARD);
    String roomBody = createPostBody(room);

    mockMvc
        .perform(post(POST_URL).content(hotelBody).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    mockMvc.perform(post(POST_ROOM_URL).content(roomBody).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }


  private String createPostBody(Object object) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(object);

  }
}
