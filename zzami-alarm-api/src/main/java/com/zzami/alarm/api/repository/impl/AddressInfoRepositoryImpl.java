package com.zzami.alarm.api.repository.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import com.zzami.alarm.api.entity.AddressInfo;
import com.zzami.alarm.api.repository.AddressInfoRepository;

public class AddressInfoRepositoryImpl extends BaseRepositoryImpl<AddressInfo, Long>
        implements AddressInfoRepository {

    public AddressInfoRepositoryImpl(EntityManager em) {
        super(AddressInfo.class, em);
    }

    @Override
    public Optional<AddressInfo> findAddressInfoByAddcd(String addcd) {
        return Optional.ofNullable(
                jpaQueryFactory
                    .select(addressInfo)
                    .from(addressInfo)
                    .where(addressInfo.addCd.equalsIgnoreCase(addcd))
                    .fetchFirst()
                
                ); 
                
    }
 
    @Override
    public List<AddressInfo> getAddressInfo() { 
        return jpaQueryFactory
                   .select(addressInfo)
                   .distinct()
                   .from(addressInfo)
                   .innerJoin(addressInfo.weatherInfoList, weatherInfo)
                   .fetchJoin()
                   .fetch();
         
    } 

}
