package com.galvanize.speedway.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.speedway.driver.Driver;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
public class DriverIT{

   @InjectMocks
   private ObjectMapper objectMapper;

   @Autowired
   private MockMvc mockMvc;

   @Test
   public void getDriversTest() throws Exception{
      this.mockMvc.perform(get("/speedway/drivers"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.length()").value(0))
              .andDo(document("getDrivers"));

   }

   @Test
   public void postDriversTest() throws Exception{

      Driver driver = new Driver();
      driver.setFirstName("Test First Name");
      driver.setLastName("Test lastname");
      driver.setLosses((short) 2);
      driver.setWins((short) 5);
      driver.setAge((short) 25);
      driver.setNickName("Test Nickname");


      RequestBuilder rq = post("/speedway/drivers")
              .content(objectMapper.writeValueAsString(driver))
              .contentType(MediaType.APPLICATION_JSON);

      mockMvc.perform(rq)
              .andExpect(status().isCreated())
              .andDo(document("postDrivers"));

      this.mockMvc.perform(get("/speedway/drivers"))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.data.length()").value(1))
         .andDo(document("getDriverDetails",responseFields(
                 fieldWithPath("status").description("Http Status"),
                 fieldWithPath("status_code").description("Http Status Code"),
            fieldWithPath("data[0].firstName").description("First Name of driver"),
            fieldWithPath("data[0].lastName").description("Last Name of driver"),
            fieldWithPath("data[0].age").description("Age of driver"),
            fieldWithPath("data[0].nickName").description("Nick Name of driver"),
            fieldWithPath("data[0].wins").description("Wins of driver"),
            fieldWithPath("data[0].losses").description("Losses of driver"),
             fieldWithPath("data[0].cars").description("Cars the driver has driven")
         )))
      ;
   }
}
