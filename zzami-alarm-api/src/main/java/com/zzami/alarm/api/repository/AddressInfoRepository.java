package com.zzami.alarm.api.repository;

import java.util.List;
import java.util.Optional;
import com.zzami.alarm.api.entity.AddressInfo;


public interface AddressInfoRepository  extends BaseRepository<AddressInfo, Long> {

  public Optional<AddressInfo> findAddressInfoByAddcd(String addcd);
  
  public List<AddressInfo> getAddressInfo();
 
  
}
