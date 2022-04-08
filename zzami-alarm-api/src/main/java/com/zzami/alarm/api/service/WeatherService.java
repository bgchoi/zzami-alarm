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
import com.zzami.alarm.api.repository.impl.AddressInfoRepositoryImpl;
import com.zzami.alarm.api.repository.impl.WeatherInfoRepositoryImpl;

@Service
public class WeatherService {

  @Autowired
  private WeatherInfoRepositoryImpl weatherInfoRepo;
  
  @Autowired
  private AddressInfoRepositoryImpl addressInfoRepo;
  
  @Autowired
  ModelMapper modelMapper;
  
  /**
   * 날씨 등록
   * @param alarmWeather
   */
  @Transactional
  public void insertWeather( WeatherInfoDTO alarmWeatherDto) {
      
      Optional<AddressInfo> adressInfo = addressInfoRepo.findAddressInfoByAddcd(alarmWeatherDto.getAddCd());
      if(adressInfo.isPresent()) {
          WeatherInfo alarmWeather = modelMapper.map(alarmWeatherDto, WeatherInfo.class);
          alarmWeather.setAddressInfo(adressInfo.get());
          alarmWeather.setCreateDt(new Date());
          weatherInfoRepo.save(alarmWeather);          
      }
      
  }
  
  /**
   * 날짜 목록 조회 
   * @param addcd
   * @return
   */
  @Transactional
  public List<WeatherInfoDTO> getWeather( String addcd) {
    return weatherInfoRepo
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
    Optional<WeatherInfo> alarmWeather =  weatherInfoRepo.findById(weatherId);
    if(!alarmWeather.isPresent()) {
        return null;
    }
    
    return modelMapper.map(alarmWeather, WeatherInfoDTO.class);
    
    
  }
  
  /**
   * 날씨 삭제 
   * @param weatherId
   */
  @Transactional
  public void deleteWeather(Long weatherId) {
      weatherInfoRepo.deleteById(weatherId);
  }
  
  
  /**
   * @author : bong 
   * @date : 2022.04.01 
   * @description : 현재 주소의 최신 날씨 데이터를 조회 
   *
   */
  public WeatherInfoDTO getCurrentWeatherInfo(String addCd) {
      return weatherInfoRepo.getCurrentWeatherInfo(addCd);
  }
  
  public List<WeatherInfoDTO> getWeatherInfoList(String addCd){
      return weatherInfoRepo.getWeatherInfoList(addCd);
  }
  
}
