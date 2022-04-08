package com.zzami.alarm.api.repository;

import com.zzami.alarm.api.dto.DustInfoDTO;
import com.zzami.alarm.api.entity.DustInfo; 

 
public interface DustInfoRepository  extends BaseRepository<DustInfo, Long> {

    public DustInfoDTO getCurrentDustInfo(String addCd);
}
