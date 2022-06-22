package com.zzami.alarm.api.repository.impl;

import javax.persistence.EntityManager;
import com.zzami.alarm.api.entity.user.SysUser;
import com.zzami.alarm.api.repository.UserRepositoryCustom;


public class UserRepositoryImpl extends BaseRepositoryImpl<SysUser, Long>
        implements UserRepositoryCustom{

    public UserRepositoryImpl(EntityManager em) {
        super(SysUser.class, em);
    }

    @Override
    public Object getOne(String addCd) {
        return null;


    }
    



}
