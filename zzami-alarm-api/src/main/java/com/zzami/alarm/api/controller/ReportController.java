package com.zzami.alarm.api.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zzami.alarm.api.dto.AddressInfoDTO;
import com.zzami.alarm.api.dto.DustInfoDTO;
import com.zzami.alarm.api.dto.NewsMetaTagDTO;
import com.zzami.alarm.api.dto.ReportDTO;
import com.zzami.alarm.api.dto.WeatherInfoDTO;
import com.zzami.alarm.api.repository.UserRepository;
import com.zzami.alarm.api.service.AddressService;
import com.zzami.alarm.api.service.DustService;
import com.zzami.alarm.api.service.NewsService;
import com.zzami.alarm.api.service.WeatherService;
import com.zzami.alarm.core.dto.result.ApiResponseBody;
import com.zzami.alarm.core.dto.result.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "00.리포트")
@RequestMapping("/api/o")
@RestController
public class ReportController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    AddressService addressService;

    @Autowired
    DustService dustService;
    
    @Autowired
    NewsService newsService;
    
    @Autowired
    UserRepository userRepository;

    /**
     * @author : bong
     * @date : 2022.04.01
     * @description : 알림 리포트
     */
    @ApiOperation(value = "알림상세 홈")
    @GetMapping("/report")
    public ResponseEntity<ApiResponseBody<ReportDTO>> getReport(
            @RequestParam(value = "addCd", defaultValue = "1000000000") String addCd,
            @RequestParam(value = "metaCodeList", defaultValue = "N01520,N01530") String metaCodeStr) {

        ApiResponseBody<ReportDTO> responseBody = new ApiResponseBody<>();
        ReportDTO report = new ReportDTO();

        responseBody.setResponse(ResultStatus.OK);

        try {
            // 주소 조회
            AddressInfoDTO address = addressService.findAddressInfoByAddcd(addCd);
            // 날씨 조회 
            WeatherInfoDTO weather = weatherService.getCurrentWeatherInfo(addCd);
            // 미세먼지 조회 
            DustInfoDTO dust = dustService.getCurrentDustInfo(addCd);
            
            List<String> metaCodeList = Arrays.asList(metaCodeStr.split(","));
            // 뉴스 목록 조회 
            List<NewsMetaTagDTO> newsList = newsService.getCurrentNewsInfoMeta(metaCodeList);
            
            // 데이터 세팅
            report.setAddress(address);
            report.setDust(dust);
            report.setWeather(weather);
            report.setNewsList(newsList);

            responseBody.setResult(report);
            

            userRepository.count();
            userRepository.findAll();
            userRepository.findByEncrytedPassword("aa");
            userRepository.findByUsername("hello");

        } catch (Exception ex) {
            throw ex;
        }

        return ResponseEntity.ok().body(responseBody);

    }


}
