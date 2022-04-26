package com.zzami.alarm.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zzami.alarm.api.entity.User;
import com.zzami.alarm.api.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
 
    public List<UserRole> getByUser(User user);
}
