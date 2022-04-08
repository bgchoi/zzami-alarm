package com.zzami.alarm.api.service;

import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzami.alarm.api.dto.DustInfoDTO;
import com.zzami.alarm.api.entity.AddressInfo;
import com.zzami.alarm.api.entity.DustInfo;
import com.zzami.alarm.api.repository.impl.AddressInfoRepositoryImpl;
import com.zzami.alarm.api.repository.impl.DustInfoRepositoryImpl;

@Transactional
@Service
public class DustService {

  @Autowired
  private DustInfoRepositoryImpl dustInfoRepo;
  
  @Autowired
  private AddressInfoRepositoryImpl addressInfoRepo;
  
  @Autowired
  ModelMapper modelMapper;
  
  /**
   * 미세먼지 등록 
   * @param alarmWeather
   */ 
  public void insertDustInfo( DustInfoDTO dto) {
      
      Optional<AddressInfo> addressInfo = addressInfoRepo.findAddressInfoByAddcd(dto.getAddCd());
      if(addressInfo.isPresent()) {
          DustInfo dustInfo = modelMapper.map(dto, DustInfo.class);
          dustInfo.setAddressInfo(addressInfo.get());
          dustInfo.setCreateDt(new Date());
          dustInfoRepo.save(dustInfo);          
      }
      
  }
  
  /** 
   * @author : bong 
   * @date : 2022.04.01 
   * @description : 현재(최근) 미세먼지 조회 
   *
   */
  public DustInfoDTO getCurrentDustInfo( String addCd) {
      return dustInfoRepo.getCurrentDustInfo(addCd);
  }
  
  
  /** 
   * 미세먼지 상세 조회
   * @param weatherId
   * @return
   */
  @Transactional
  public DustInfo getFindAirById(Long airId) {
    return dustInfoRepo.findById(airId).orElse(null);
  }
  
  /**
   * 미세먼지 삭제 
   * @param weatherId
   */
  @Transactional
  public void deleteAirById(Long airId) {
      dustInfoRepo.deleteById(airId);
  }
}
