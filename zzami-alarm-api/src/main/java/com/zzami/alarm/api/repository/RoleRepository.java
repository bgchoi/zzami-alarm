package com.zzami.alarm.api.repository;

import com.zzami.alarm.api.entity.user.SysRole;

public interface RoleRepository extends BaseRepository<SysRole, Long> {
  
    SysRole findByRoleName(String roleName);
}
