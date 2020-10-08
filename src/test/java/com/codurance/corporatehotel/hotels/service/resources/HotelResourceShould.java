package com.codurance.corporatehotel.hotels.service.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HotelResourceShould {

  private final String URL = "/hotels/1";

  @Autowired
  private MockMvc mockMvc;

  @Test
  void not_find_an_hotel_given_is_not_in_the_system() throws Exception {
    mockMvc.perform(get(URL))
        .andExpect(status().isNotFound());
  }
}
