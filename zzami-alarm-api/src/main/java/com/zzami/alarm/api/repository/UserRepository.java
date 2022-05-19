package com.zzami.alarm.api.repository;

import com.zzami.alarm.api.entity.User;

public interface UserRepository extends BaseRepository<User, Long>, UserRepositoryCustom {
 
    User findByUserId(String userId);
    
    User findFirstByUsernameOrderByUsnAsc(String username);
    
    User findByEncrytedPassword(String enString);
 
     
}
