package com.zzami.alarm.api.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzami.alarm.api.dto.NewsInfoDTO;
import com.zzami.alarm.api.dto.NewsMetaTagDTO;
import com.zzami.alarm.api.dto.NewsSourceDTO;
import com.zzami.alarm.api.entity.NewsInfo;
import com.zzami.alarm.api.entity.NewsSourceInfo;
import com.zzami.alarm.api.repository.impl.NewsInfoRepositoryImpl;
import com.zzami.alarm.api.repository.impl.NewsSourceInfoRepositoryImpl;


@Service
@Transactional
public class NewsService {

  @Autowired
  NewsSourceInfoRepositoryImpl newsSourceRepo;
  
  @Autowired
  NewsInfoRepositoryImpl newsInfoRepo;
  
  @Autowired
  ModelMapper modelMapper;
  
  /**
   * @author : bong 
   * @date : 2022.04.03 
   * @description : 뉴스 소스 등록 
   */
  public void save(NewsSourceDTO dto) {
      
      NewsSourceInfo en = modelMapper.map(dto, NewsSourceInfo.class);
      en.setCreateDt(new Date());
      newsSourceRepo.save(en);
  }
  
  /** 
   * @author : bong 
   * @date : 2022.04.03 
   * @description : 뉴스 리포트 등록 
   */
  public void save(NewsInfoDTO dto) {
      NewsSourceInfo newsSourceInfo = newsSourceRepo.getById(dto.getNewsSourceId());      
      if(newsSourceInfo == null) {
          throw new RuntimeException("뉴스소스 정보를 찾을 수 없습니다.");
      }
      
      NewsInfo newsReport = modelMapper.map(dto, NewsInfo.class);
      newsReport.setNewsSourceInfo(newsSourceInfo);
      newsReport.setIsUse(1);
      
      newsInfoRepo.save(newsReport);
       
  }
  
  
  
  public List<NewsMetaTagDTO> getCurrentNewsReporMeta(List<String> mataCodeList) {
      return newsInfoRepo.getCurrentNewsReporMeta(Arrays.asList("N01520","N01530"));
      
  }
  
  
  
  
   
  
}
