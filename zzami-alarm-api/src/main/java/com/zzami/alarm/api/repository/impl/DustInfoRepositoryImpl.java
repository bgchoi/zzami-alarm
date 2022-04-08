package com.zzami.alarm.api.repository.impl;

import javax.persistence.EntityManager;
import com.querydsl.core.types.Projections;
import com.zzami.alarm.api.dto.DustInfoDTO;
import com.zzami.alarm.api.entity.DustInfo;
import com.zzami.alarm.api.repository.DustInfoRepository;

public class DustInfoRepositoryImpl extends BaseRepositoryImpl<DustInfo, Long>
        implements DustInfoRepository {

    public DustInfoRepositoryImpl(EntityManager em) {
        super(DustInfo.class, em);
    }

    

    @Override
    public DustInfoDTO getCurrentDustInfo(String addCd) {
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
         
    }


}
