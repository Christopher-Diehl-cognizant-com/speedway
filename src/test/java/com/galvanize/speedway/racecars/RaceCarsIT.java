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
        mockMvc.perform(get("/racecars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0));
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
        mockMvc.perform(post("/racecars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(raceCarDto)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/racecars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
                .andExpect(jsonPath("[0].owner").value(27))
                .andExpect(jsonPath("[0].nickname").value("The Condor"));
    }
}




