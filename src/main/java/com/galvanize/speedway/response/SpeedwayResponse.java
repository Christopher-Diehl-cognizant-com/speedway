package com.galvanize.speedway.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeedwayResponse<T> {
    HttpStatus status;
    int status_code;
    List<T> data;
}
