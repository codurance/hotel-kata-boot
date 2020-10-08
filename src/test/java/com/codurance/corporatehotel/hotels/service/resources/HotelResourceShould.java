package com.codurance.corporatehotel.hotels.service.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.codurance.corporatehotel.hotels.model.Hotel;
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
}
