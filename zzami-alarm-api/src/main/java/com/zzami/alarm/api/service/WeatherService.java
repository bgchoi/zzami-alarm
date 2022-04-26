package com.zzami.alarm.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzami.alarm.api.dto.WeatherInfoDTO;
import com.zzami.alarm.api.entity.AddressInfo;
import com.zzami.alarm.api.entity.WeatherInfo;
import com.zzami.alarm.api.repository.AddressInfoRepository;
import com.zzami.alarm.api.repository.WeatherInfoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeatherService {

  @Autowired
  private WeatherInfoRepository weatherInfoRepository;
  
  @Autowired
  private AddressInfoRepository addressInfoRepository;
  
  @Autowired
  ModelMapper modelMapper;
  
  /**
   * 날씨 등록
   * @param alarmWeather
   */
  @Transactional
  public void insertWeather( WeatherInfoDTO alarmWeatherDto) {
      
      Optional<AddressInfo> adressInfo = addressInfoRepository.findAddressInfoByAddcd(alarmWeatherDto.getAddCd());
      if(adressInfo.isPresent()) {
          WeatherInfo alarmWeather = modelMapper.map(alarmWeatherDto, WeatherInfo.class);
          alarmWeather.setAddressInfo(adressInfo.get());
          alarmWeather.setCreateDt(new Date());
          weatherInfoRepository.save(alarmWeather);          
      }
      
  }
  
  /**
   * 날짜 목록 조회 
   * @param addcd
   * @return
   */
  @Transactional
  public List<WeatherInfoDTO> getWeather( String addcd) {
    return weatherInfoRepository
            .findAll()
                .stream()
                .map( e ->  modelMapper.map((WeatherInfo)e, WeatherInfoDTO.class) )
                .collect(Collectors.toList());
    
  }
  
  /** 
   * 날씨 상세 조회
   * @param weatherId
   * @return
   */
  @Transactional
  public WeatherInfoDTO getWeatherById(Long weatherId) {
    Optional<WeatherInfo> result =  weatherInfoRepository.findById(weatherId);
    
    if(!result.isPresent()) {
        return null;
    }
    
    return modelMapper.map(result.get(), WeatherInfoDTO.class);
    
    
  }
  
  /**
   * 날씨 삭제 
   * @param weatherId
   */
  @Transactional
  public void deleteWeather(Long weatherId) {
      weatherInfoRepository.deleteById(weatherId);
  }
  
  
  /**
   * @author : bong 
   * @date : 2022.04.01 
   * @description : 현재 주소의 최신 날씨 데이터를 조회 
   *
   */
  public WeatherInfoDTO getCurrentWeatherInfo(String addCd) {
      return weatherInfoRepository.getCurrentWeatherInfo(addCd);
  }
  
  /**
   * 
   * @author : bong 
   * @date : 2022.04.16 
   * @description : 뉴스목록 조회 
   *
   */
  public List<WeatherInfoDTO> getWeatherInfoList(String addCd){
      return weatherInfoRepository.getWeatherInfoList(addCd);
  }
  
}
