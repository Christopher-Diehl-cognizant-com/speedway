package com.galvanize.speedway.unittest;

import com.galvanize.speedway.driver.Driver;
import com.galvanize.speedway.driver.DriverRepository;
import com.galvanize.speedway.driver.DriverService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.galvanize.speedway.response.SpeedwayResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

/**

 @author cld71
 */
@ExtendWith(MockitoExtension.class)
public class DriverTest{

   @Mock
   DriverRepository repository;

   @InjectMocks
   DriverService service;

   @Test
   public void getDriversTest(){
      List<String> carList = new ArrayList<String>();
      carList.add("A");
      carList.add("B");
      Driver d1=new Driver("firstName","lastName",Short.MIN_VALUE,"nickName",Short.MIN_VALUE,Short.MIN_VALUE, carList);
      Driver d2=new Driver("firstName","lastName",Short.MAX_VALUE,"nickName",Short.MAX_VALUE,Short.MAX_VALUE, carList);
      when(this.repository.findAll())
         .thenReturn(
            Arrays.asList(
               d1,
               d2));

     SpeedwayResponse<Driver> actual=this.service.getDrivers();
      assertThat(actual.getData()).isEqualTo(
         Arrays.asList(
            new Driver("firstName","lastName",Short.MIN_VALUE,"nickName",Short.MIN_VALUE,Short.MIN_VALUE, carList),
            new Driver("firstName","lastName",Short.MAX_VALUE,"nickName",Short.MAX_VALUE,Short.MAX_VALUE, carList)
         ));
   }
}
