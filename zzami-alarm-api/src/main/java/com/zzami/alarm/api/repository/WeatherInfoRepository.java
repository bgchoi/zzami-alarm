package com.zzami.alarm.api.repository;

import java.util.List;
import com.zzami.alarm.api.dto.WeatherInfoDTO;
import com.zzami.alarm.api.entity.WeatherInfo;

 
public interface WeatherInfoRepository  extends BaseRepository<WeatherInfo, Long>{

    public WeatherInfoDTO getCurrentWeatherInfo(String addCd);
    
    public List<WeatherInfoDTO> getWeatherInfoList(String addCd);

}
