package com.zzami.alarm.api.repository.impl;

import javax.persistence.EntityManager;
import com.zzami.alarm.api.entity.user.SysRole;


public class RoleRepositoryImpl extends BaseRepositoryImpl<SysRole, Long> {

    public RoleRepositoryImpl(EntityManager em) {
        super(SysRole.class, em);
    }
 
}
