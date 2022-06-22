package com.zzami.alarm.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zzami.alarm.api.entity.user.SysUser;
import com.zzami.alarm.api.entity.user.SysUserRole;

public interface UserRoleRepository extends JpaRepository<SysUserRole, Long>{
 
    public List<SysUserRole> getBySysUser(SysUser sysUser);
}
