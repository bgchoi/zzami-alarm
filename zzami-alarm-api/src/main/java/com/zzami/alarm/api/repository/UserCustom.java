package com.zzami.alarm.api.repository;

import com.zzami.alarm.api.dto.DustInfoDTO;

public interface UserCustom  {
 
    DustInfoDTO getOne(String username);
}
