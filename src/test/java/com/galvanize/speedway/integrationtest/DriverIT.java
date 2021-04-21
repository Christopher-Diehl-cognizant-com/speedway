package com.galvanize.speedway.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class DriverIT{

   @InjectMocks
   private ObjectMapper objectMapper;

   @Autowired
   private MockMvc mockMvc;

   @Test
   public void getDriversTest() throws Exception{
      this.mockMvc.perform(get("/speedway/drivers"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("length()").value(0))
//         .andDo(document("GetDrivers",responseFields(
//            fieldWithPath("[0].firstName").description("First Name of driver"),
//            fieldWithPath("[0].lastName").description("Last Name of driver"),
//            fieldWithPath("[0].age").description("Age of driver"),
//            fieldWithPath("[0].nickName").description("Nick Name of driver"),
//            fieldWithPath("[0].wins").description("Wins of driver"),
//            fieldWithPath("[0].losses").description("Losses of driver"),
//             fieldWithPath("[0].cars").description("Cars the driver has driven")
//         )))
         ;
   }
}
