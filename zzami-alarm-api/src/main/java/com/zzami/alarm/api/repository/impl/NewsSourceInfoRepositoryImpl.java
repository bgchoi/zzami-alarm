package com.zzami.alarm.api.repository.impl;

import javax.persistence.EntityManager;
import com.zzami.alarm.api.entity.NewsSourceInfo;
import com.zzami.alarm.api.repository.NewsSourceInfoRepository;

public class NewsSourceInfoRepositoryImpl extends BaseRepositoryImpl<NewsSourceInfo, Long>
        implements NewsSourceInfoRepository {

    public NewsSourceInfoRepositoryImpl(EntityManager em) {
        super(NewsSourceInfo.class, em);
    }

    



}
