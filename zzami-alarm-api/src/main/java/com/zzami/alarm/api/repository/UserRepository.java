package com.zzami.alarm.api.repository;

import java.util.List;
import com.zzami.alarm.api.entity.User;

public interface UserRepository extends BaseRepository<User, Long>, UserCustom {
 
    List<User> findByUsername(String username);
    
    User findFirstByUsernameOrderByUsnAsc(String username);
}
