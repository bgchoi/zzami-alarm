package com.zzami.alarm.api.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import com.querydsl.core.types.Projections;
import com.zzami.alarm.api.dto.WeatherInfoDTO;
import com.zzami.alarm.api.entity.WeatherInfo;
import com.zzami.alarm.api.repository.WeatherInfoRepository;

public class WeatherInfoRepositoryImpl extends BaseRepositoryImpl<WeatherInfo, Long>
    implements WeatherInfoRepository {

  public WeatherInfoRepositoryImpl( EntityManager em) {
      super(WeatherInfo.class, em);
  }

  @Override
  public WeatherInfoDTO getCurrentWeatherInfo(String addCd) {
      return jpaQueryFactory 
                  .from(addressInfo)
                  .leftJoin(addressInfo.weatherInfoList, weatherInfo)
                  .where(addressInfo.addCd.eq(addCd))
                  .orderBy(weatherInfo.fcastDt.desc())
                  .limit(1)
                  .select(Projections.bean(WeatherInfoDTO.class, 
                          addressInfo.addCd, 
                          weatherInfo.weatherId,
                          weatherInfo.templature,
                          weatherInfo.tipMessage, 
                          weatherInfo.templatureMin,
                          weatherInfo.templatureMax,
                          weatherInfo.status,
                          weatherInfo.fcastDt,
                          weatherInfo.createDt
                   ))
                  .fetchOne();
       
  }
  
  
  @Override
  public List<WeatherInfoDTO> getWeatherInfoList(String addCd) {
      
      return null;
//      return jpaQueryFactory 
//                 .from(addressInfo)
//                 .innerJoin(addressInfo.weatherInfoList, weatherInfo)
//                 .where(addressInfo.addCd.eq(addCd))
//                 .groupBy(addressInfo.addCd)
//                 .select(Projections.constructor(WeatherInfoDTO.class, 
//                         addressInfo.addCd,   
//                         weatherInfo.templature.max())).fetch();
      
  
                 
  }


}
