package com.galvanize.speedway.raceIT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.speedway.driver.Driver;
import com.galvanize.speedway.races.Race;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
public class RaceIT {

    @InjectMocks
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRacesTest() throws Exception{
        this.mockMvc.perform(get("/speedway/races"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0))
        ;
    }

    @Test
    public void postRacesTest() throws Exception{

        Race race = new Race();
        race.setName("Race Name");
        race.setCategory("Race Category");
        race.setDate("2020-06-03");
        race.setBestTime("03:36:78");

        Driver winner = new Driver();
        winner.setFirstName("Test First Name");
        winner.setLastName("Test lastname");
        winner.setLosses((short) 2);
        winner.setWins((short) 5);
        winner.setAge((short) 25);
        winner.setNickName("Test Nickname");

        race.setWinner(winner);
        List<Driver> drivers = new ArrayList<Driver>();
        drivers.add(winner);

        Driver driver2 = new Driver();
        driver2.setFirstName("Test First Name 2");
        driver2.setLastName("Test lastname 2");
        driver2.setLosses((short) 8);
        driver2.setWins((short) 10);
        driver2.setAge((short) 30);
        driver2.setNickName("Test Nickname 2");

        drivers.add(driver2);
        race.setParticipants(drivers);

        RequestBuilder rq = post("/speedway/races")
                .content(objectMapper.writeValueAsString(race))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(rq)
                .andExpect(status().isCreated())
                .andDo(print())
//                .andDo(document("Post-Races",requestFields(
//                        fieldWithPath("name").description("Driver FirstName"),
//                        fieldWithPath("category").description("Driver LastName"),
//                        fieldWithPath("date").description("Driver Age"),
//                        fieldWithPath("bestTime").description("Driver NickName"),
//                        fieldWithPath("winner.firstName").description("Winner FirstName"),
//                        fieldWithPath("winner.lastName").description("Winner LastName"),
//                        fieldWithPath("winner.age").description("Winner Age"),
//                        fieldWithPath("winner.nickName").description("Winner NickName"),
//                        fieldWithPath("winner.wins").description("Winner Wins"),
//                        fieldWithPath("winner.losses").description("Winner Losses"),
//                        fieldWithPath("winner.cars").description("Winner Cars"),
//                        fieldWithPath("participants.[0].firstName").description("Driver FirstName"),
//                        fieldWithPath("participants.[0].lastName").description("Driver LastName"),
//                        fieldWithPath("participants.[0].age").description("Driver Age"),
//                        fieldWithPath("participants.[0].nickName").description("Driver NickName"),
//                        fieldWithPath("participants.[0].wins").description("Driver Wins"),
//                        fieldWithPath("participants.[0].losses").description("Driver Losses"),
//                        fieldWithPath("participants.[0].cars").description("Driver Cars"),
//                        fieldWithPath("participants.[1].firstName").description("Driver FirstName"),
//                        fieldWithPath("participants.[1].lastName").description("Driver LastName"),
//                        fieldWithPath("participants.[1].age").description("Driver Age"),
//                        fieldWithPath("participants.[1].nickName").description("Driver NickName"),
//                        fieldWithPath("participants.[1].wins").description("Driver Wins"),
//                        fieldWithPath("participants.[1].losses").description("Driver Losses"),
//                        fieldWithPath("participants.[1].cars").description("Driver Cars")
//                        )
//              responseFields(
//                      fieldWithPath("message").description("Response message")))))
                ;

        this.mockMvc.perform(get("/speedway/races"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
        .andDo(print())
//                .andDo(document("Get-Races",responseFields(
//                        fieldWithPath("$.name").description("Race Name"),
//                        fieldWithPath("$[0].category").description("Race Category"),
//                        fieldWithPath("$[0].date").description("Race Date"),
//                        fieldWithPath("$[0].bestTime").description("Race Best Time"),
//                        fieldWithPath("$[0].winner.firstName").description("Winner FirstName"),
//                        fieldWithPath("$[0].winner.lastName").description("Winner LastName"),
//                        fieldWithPath("$[0].winner.age").description("Winner Age"),
//                        fieldWithPath("$[0].winner.nickName").description("Winner NickName"),
//                        fieldWithPath("$[0].winner.wins").description("Winner Wins"),
//                        fieldWithPath("$[0].winner.losses").description("Winner Losses"),
//                        fieldWithPath("$[0].winner.cars").description("Winner Cars"),
//                        fieldWithPath("$[0].participants.[0].firstName").description("Driver FirstName"),
//                        fieldWithPath("$[0].participants.[0].lastName").description("Driver LastName"),
//                        fieldWithPath("$[0].participants.[0].age").description("Driver Age"),
//                        fieldWithPath("$[0].participants.[0].nickName").description("Driver NickName"),
//                        fieldWithPath("$[0].participants.[0].wins").description("Driver Wins"),
//                        fieldWithPath("$[0].participants.[0].losses").description("Driver Losses"),
//                        fieldWithPath("$[0].participants.[0].cars").description("Driver Cars"),
//                        fieldWithPath("$[0].participants.[1].firstName").description("Driver FirstName"),
//                        fieldWithPath("$[0].participants.[1].lastName").description("Driver LastName"),
//                        fieldWithPath("$[0].participants.[1].age").description("Driver Age"),
//                        fieldWithPath("$[0].participants.[1].nickName").description("Driver NickName"),
//                        fieldWithPath("$[0].participants.[1].wins").description("Driver Wins"),
//                        fieldWithPath("$[0].participants.[1].losses").description("Driver Losses"),
//                        fieldWithPath("$[0].participants.[1].cars").description("Driver Cars")
//                )))
        ;
    }
}
