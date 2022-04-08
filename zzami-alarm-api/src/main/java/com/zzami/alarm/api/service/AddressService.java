package com.zzami.alarm.api.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzami.alarm.api.dto.AddressInfoDTO;
import com.zzami.alarm.api.entity.AddressInfo;
import com.zzami.alarm.api.repository.impl.AddressInfoRepositoryImpl;


@Service
@Transactional
public class AddressService {

  @Autowired
  AddressInfoRepositoryImpl addressInfoRepo;
  
  @Autowired
  ModelMapper modelMapper;
  
  
  /**
   * @author : bong 
   * @date : 2022.04.05 
   * @description : 주소 아이디로 주소정보 조회 
   *
   */
  public AddressInfoDTO getAddressInfoById(long id) {
      Optional<AddressInfo> ret =  addressInfoRepo.findById(id);
      
      if(!ret.isPresent())
          return null;
       
      return modelMapper.map(ret.get(), AddressInfoDTO.class); 
  }
  
  /** 
   * @author : bong 
   * @date : 2022.04.01 
   * @description : 주소 코드로 주소정보 조회 
   */
  public AddressInfoDTO findAddressInfoByAddcd(String addcd) {
      Optional<AddressInfo> ret = addressInfoRepo.findAddressInfoByAddcd(addcd);
      
      if(!ret.isPresent())
          return null;
       
      return modelMapper.map(ret.get(), AddressInfoDTO.class); 
  }
  
  
  
  public List<AddressInfo> getAddressInfo() {
         return addressInfoRepo.getAddressInfo();
  }
  
  /**
   * @author : bong 
   * @date : 2022.04.01 
   * @description : 주소 저장 
   */
  public AddressInfo saveAddress(AddressInfoDTO addrDto) {
      AddressInfo addr = modelMapper.map(addrDto, AddressInfo.class);
      addr.setIsUse(1);
      return addressInfoRepo.save( addr );
  }
  
  /**
   * @author : bong 
   * @date : 2022.04.01 
   * @description : 주소 삭제 
   */
  public void removeAddress(Long addId) {
      addressInfoRepo.deleteById(addId);
  }
  
  
 
  
}
