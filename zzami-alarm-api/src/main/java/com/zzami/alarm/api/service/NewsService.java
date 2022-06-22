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
import com.zzami.alarm.api.repository.NewsInfoRepository;
import com.zzami.alarm.api.repository.NewsSourceInfoRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
public class NewsService {

  @Autowired
  NewsSourceInfoRepository newsSourceInfoRepository;
  
  @Autowired
  NewsInfoRepository newsInfoRepository;
  
  @Autowired
  ModelMapper modelMapper;
  
  /**
   * @author : bong 
   * @date : 2022.04.03 
   * @description : 뉴스 소스 db 등록 
   */
  public void save(NewsSourceDTO dto) {
      
      NewsSourceInfo entity = modelMapper.map(dto, NewsSourceInfo.class);
      entity.setCreateDt(new Date());
      newsSourceInfoRepository.save(entity);
  }
  
  /** 
   * @author : bong 
   * @date : 2022.04.03 
   * @description : 뉴스 리포트 등록 
   */
  public void save(NewsInfoDTO dto) {
      NewsSourceInfo newsSourceInfo = newsSourceInfoRepository.getById(dto.getNewsSourceId());      
      if(newsSourceInfo == null) {
          throw new RuntimeException("뉴스소스 정보를 찾을 수 없습니다.");
      }
      
      NewsInfo newsReport = modelMapper.map(dto, NewsInfo.class);
      newsReport.setNewsSourceInfo(newsSourceInfo);
      newsReport.setIsUse(1);
      
      newsInfoRepository.save(newsReport);
       
  }
  
  /**
   * @author : bong 
   * @date : 2022.04.15 
   * @description : 뉴스목록 저장 
   */
  public void save(List<NewsInfoDTO> list) {
      
      for(NewsInfoDTO item: list) {
          try {
              save(item);
          }catch(Exception ex) {
              log.error(ex.getMessage(), ex);
          }
      } 
       
  }
  
  /**
   * 
   * @author : bong 
   * @date : 2022.04.16 
   * @description : 뉴스 상세 조회  
   *
   */
  public NewsInfoDTO getNewsById(Long newsId) {
      
      NewsInfo news = newsInfoRepository.getById(newsId);
      NewsInfoDTO newsInfo = null;
      log.info("news==>" + news);
      if(news != null) {
         newsInfo  = modelMapper.map(news, NewsInfoDTO.class);          
      }
      
      return newsInfo;
  }
  
  
  
  public List<NewsMetaTagDTO> getCurrentNewsInfoMeta(List<String> mataCodeList) {
      return newsInfoRepository.getCurrentNewsInfoMeta(Arrays.asList("N01520","N01530"));
      
  }
  
  
  
  
   
  
}
