package com.zzami.alarm.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzami.alarm.api.dto.DustInfoDTO;
import com.zzami.alarm.api.service.DustService;
import com.zzami.alarm.core.dto.result.ApiResponseBody;
import com.zzami.alarm.core.dto.result.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "30.미세먼지관리")
@RequestMapping("/api/o")
@RestController
public class DustController {

  @Autowired
  DustService dustService;

  
  /**
   * @author : bong 
   * @date : 2022.04.01 
   * @description : 미세먼지 정보 저장
   */
  @ApiOperation(value = "미세먼지 저장")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "addCd", required = true,  paramType = "form", value = "주소코드"),
      @ApiImplicitParam(name = "pm10", required = true,  paramType = "form", value = "미세먼지"),
      @ApiImplicitParam(name = "pm25", required = true, paramType = "form", value = "초미세먼지"),
      @ApiImplicitParam(name = "stationNm", required = true, paramType = "form", value = "관측소명"),
      @ApiImplicitParam(name = "fcastDt", required = true, paramType = "form", value = "예보시간(yyyy-MM-dd HH:mm:ss)"),
      @ApiImplicitParam(name = "createDt", required = true, paramType = "form", value = "등록일(yyyy-MM-dd HH:mm:ss)"),
  })
  @PostMapping(value="/dust", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public ResponseEntity<ApiResponseBody<Void>> saveWeather(
          DustInfoDTO dustInfo) {
      ApiResponseBody<Void> responseBody = new ApiResponseBody<>();
      responseBody.setResponse(ResultStatus.OK);

      try {   
          dustService.insertDustInfo(dustInfo); 

      } catch (Exception ex) {
          throw ex;
      }

      return ResponseEntity.ok().body(responseBody);

  }
  
}
