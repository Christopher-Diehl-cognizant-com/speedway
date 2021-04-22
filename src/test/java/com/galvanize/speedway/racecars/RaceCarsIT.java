package com.galvanize.speedway.racecars;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.speedway.racecars.model.RaceCarDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RaceCarsIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getAllRaceCarsWhenEmpty() throws Exception {
        mockMvc.perform(get("/speedway/racecars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0))
                .andDo(document("getAllRaceCarsWhenEmpty"));
    }

    @Test
    public void postRaceCarAndGetRaceCarList() throws Exception {
        RaceCarDto raceCarDto = new RaceCarDto(
                "The Condor",
                "Corvette",
                "2019",
                27,
                "AVAILABLE",
                189,
                "compact");
        mockMvc.perform(post("/speedway/racecars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(raceCarDto)))
                .andExpect(status().isCreated())
                .andDo(document("addRaceCars"));

        mockMvc.perform(get("/speedway/racecars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
                .andExpect(jsonPath("[0].owner").value(27))
                .andExpect(jsonPath("[0].nickname").value("The Condor"))
                .andDo(document("getRaceCars"));
    }

    @Test
    public void postMultipleRaceCarAndGetRaceCarList() throws Exception {
        RaceCarDto raceCarDto1 = new RaceCarDto(
                "The Condor",
                "Corvette",
                "2019",
                27,
                "AVAILABLE",
                189,
                "compact");

        RaceCarDto raceCarDto2 = new RaceCarDto(
                "The Ferrari",
                "Ferrari",
                "2019",
                30,
                "AVAILABLE",
                220,
                "compact");
        mockMvc.perform(post("/speedway/racecars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(raceCarDto1)))
                .andExpect(status().isCreated())
                .andDo(document("addRaceCars"));

        mockMvc.perform(post("/speedway/racecars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(raceCarDto2)))
                .andExpect(status().isCreated())
                .andDo(document("addRaceCars"));

        mockMvc.perform(get("/speedway/racecars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(2))
                .andExpect(jsonPath("[1].owner").value(30))
                .andExpect(jsonPath("[1].nickname").value("The Ferrari"))
                .andExpect(jsonPath("[0].owner").value(27))
                .andExpect(jsonPath("[0].nickname").value("The Condor"))
                .andDo(document("getRaceCars"));
    }
}




