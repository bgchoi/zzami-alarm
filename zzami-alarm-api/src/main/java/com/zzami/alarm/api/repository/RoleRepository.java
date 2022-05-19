package com.zzami.alarm.api.repository;

import com.zzami.alarm.api.entity.Role;

public interface RoleRepository extends BaseRepository<Role, Long> {
  
    Role findByRoleName(String roleName);
}
