package com.zzami.alarm.api.repository;

import com.zzami.alarm.api.entity.user.SysUser;

public interface UserRepository extends BaseRepository<SysUser, Long>, UserRepositoryCustom {
 
    SysUser findByUserId(String userId);
    
    SysUser findFirstByUsernameOrderByUsnAsc(String username);
    
    SysUser findByEncrytedPassword(String enString);
 
     
}
