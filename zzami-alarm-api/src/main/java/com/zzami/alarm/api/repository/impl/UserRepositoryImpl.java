package com.zzami.alarm.api.repository.impl;

import javax.persistence.EntityManager;
import com.querydsl.core.types.Projections;
import com.zzami.alarm.api.dto.DustInfoDTO;
import com.zzami.alarm.api.entity.user.SysUser;
import com.zzami.alarm.api.repository.UserRepositoryCustom;


public class UserRepositoryImpl extends BaseRepositoryImpl<SysUser, Long>
        implements UserRepositoryCustom{

    public UserRepositoryImpl(EntityManager em) {
        super(SysUser.class, em);
    }

    @Override
    public DustInfoDTO getOne(String addCd) {
        //@formatter:off
        return jpaQueryFactory 
                .from(addressInfo)
                .leftJoin(addressInfo.dustInfoList, dustInfo)
                .where(addressInfo.addCd.eq(addCd))
                .orderBy(dustInfo.fcastDt.desc())
                .limit(1)
                .select(Projections.constructor(DustInfoDTO.class, 
                        addressInfo.addCd,
                        addressInfo.addNm,
                        addressInfo.stationNm,
                        dustInfo.pm10,
                        dustInfo.pm25,
                        dustInfo.fcastDt,
                        dustInfo.createDt)) 
                .fetchOne();
        // @formatter:on


    }
    



}
