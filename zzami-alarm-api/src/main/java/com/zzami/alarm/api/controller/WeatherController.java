package com.zzami.alarm.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzami.alarm.api.dto.WeatherInfoDTO;
import com.zzami.alarm.api.service.WeatherService;
import com.zzami.alarm.core.dto.result.ApiResponseBody;
import com.zzami.alarm.core.dto.result.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "20.날씨관리")
@RequestMapping("/api/o")
@RestController
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  /**
   * @author : bong 
   * @date : 2022.04.01 
   * @description : 날씨 상세 조회 
   */
  @ApiOperation(value = "날씨 상세 조회")
  @GetMapping("/weather/{weatherId}")
  public ResponseEntity<ApiResponseBody<WeatherInfoDTO>> getWeather(@PathVariable Long weatherId) {

    ApiResponseBody<WeatherInfoDTO> responseBody = new ApiResponseBody<>();
    responseBody.setResponse(ResultStatus.OK);

    try {
      WeatherInfoDTO weatherDto = weatherService.getWeatherById(weatherId);
      responseBody.setResult(weatherDto);

    } catch (Exception ex) {
      throw ex;
    }

    return ResponseEntity.ok().body(responseBody);

  }
  
  /**
   * @author : bong 
   * @date : 2022.04.01 
   * @description : 날씨 저장 
   */
  @ApiOperation(value = "날씨 저장") 
  @ApiImplicitParams({
      @ApiImplicitParam(name = "addCd", required = true,  paramType = "form", value = "주소코드(1000000000)"),
  })
  @PostMapping(value="/weather", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public ResponseEntity<ApiResponseBody<Void>> saveWeather(
           WeatherInfoDTO weatherInfoDto
          ) {
      ApiResponseBody<Void> responseBody = new ApiResponseBody<>();
      responseBody.setResponse(ResultStatus.OK);

      try {  
          
          weatherService.insertWeather(weatherInfoDto); 

      } catch (Exception ex) {
          throw ex;
      }

      return ResponseEntity.ok().body(responseBody);

  }
  
}
