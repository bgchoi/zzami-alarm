package com.zzami.alarm.api.repository.impl;

import javax.persistence.EntityManager;
import com.zzami.alarm.api.entity.Role;


public class RoleRepositoryImpl extends BaseRepositoryImpl<Role, Long> {

    public RoleRepositoryImpl(EntityManager em) {
        super(Role.class, em);
    }
 
}
