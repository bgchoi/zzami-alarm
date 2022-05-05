package com.zzami.alarm.api.repository.impl;

import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zzami.alarm.api.entity.QAddressInfo;
import com.zzami.alarm.api.entity.QDustInfo;
import com.zzami.alarm.api.entity.QMetaCode;
import com.zzami.alarm.api.entity.QNewsInfo;
import com.zzami.alarm.api.entity.QNewsSourceInfo;
import com.zzami.alarm.api.entity.QNewsSourceMetaMap;
import com.zzami.alarm.api.entity.QSysCode;
import com.zzami.alarm.api.entity.QWeatherInfo;
import com.zzami.alarm.api.repository.BaseRepository;

public abstract class BaseRepositoryImpl<T,ID> extends SimpleJpaRepository<T, ID>  
implements BaseRepository<T, ID>{

  EntityManager em;
  JPAQueryFactory jpaQueryFactory;
  
  protected final QAddressInfo addressInfo = QAddressInfo.addressInfo;
  protected final QWeatherInfo weatherInfo = QWeatherInfo.weatherInfo;
  protected final QDustInfo dustInfo = QDustInfo.dustInfo;
  protected final QNewsSourceInfo newsSourceInfo = QNewsSourceInfo.newsSourceInfo;
  protected final QNewsInfo newsInfo = QNewsInfo.newsInfo;
  protected final QNewsSourceMetaMap newsSourceMetaMap = QNewsSourceMetaMap.newsSourceMetaMap;
  protected final QSysCode sysCode = QSysCode.sysCode; 
  protected final QMetaCode mataCode = QMetaCode.metaCode;

  
  public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
    super(domainClass, em);
    this.em = em;
    this.jpaQueryFactory = new JPAQueryFactory(em);
  }
  
  @Override
  public T findByIdMandatory(ID id) throws IllegalArgumentException {
    return findById(id).orElseThrow( () -> new IllegalArgumentException("entity not found with id"+id));
  }

}
