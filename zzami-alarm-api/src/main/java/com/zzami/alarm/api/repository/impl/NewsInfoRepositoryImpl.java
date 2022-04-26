package com.zzami.alarm.api.repository.impl;

import static com.querydsl.core.group.GroupBy.list;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.zzami.alarm.api.dto.MetaCdDTO;
import com.zzami.alarm.api.dto.NewsMetaTagDTO;
import com.zzami.alarm.api.dto.QNewsMetaTagDTO;
import com.zzami.alarm.api.entity.NewsInfo;
import com.zzami.alarm.api.repository.NewsInfoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewsInfoRepositoryImpl extends BaseRepositoryImpl<NewsInfo, Long> implements NewsInfoRepository {

    public NewsInfoRepositoryImpl(EntityManager em) {
        super(NewsInfo.class, em);
    }

    @Override
    public List<NewsMetaTagDTO> getCurrentNewsInfoMeta(List<String> metaCodeList) {
          
        
//      Map<Long, NewsMetaTagDTO> map = jpaQueryFactory 
//                  .from(newsSourceMetaMap)
//                  .innerJoin(newsSourceMetaMap.newsSourceInfo, newsSourceInfo)
//                  .innerJoin(newsSourceInfo.newsInfoList, newsInfo)
//                  .innerJoin(newsSourceMetaMap.meta, mataCode)
//                  .where(newsSourceMetaMap.meta.in( 
//                          // subQuery
//                          JPAExpressions
//                          .select(mataCode)
//                          .from(mataCode)
//                          .where(mataCode.metaCd.in(metaCodeList))
//                  ).and(newsInfo.isUse.eq(1))) 
//                  .orderBy(newsInfo.pubDt.desc()) 
//                  .transform(GroupBy.groupBy(newsInfo.newsId)
//                          .as(new QNewsMetaTagDTO(
//                                  newsInfo.newsId,
//                                  newsSourceInfo.newsSourceId,
//                                  newsInfo.title,
//                                  newsInfo.content,
//                                  newsInfo.url,
//                                  list(new QMetaCdDTO(mataCode.metaCd, mataCode.name)),
//                                  list(mataCode.name)
//                                  
//                           ))); 
         
        
        
        
        try {
            
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            String nowStr = fmt.format(new Date());
            Date now = fmt.parse(nowStr);
            
         
            Map<Long, NewsMetaTagDTO> map = jpaQueryFactory 
                  .from(newsSourceMetaMap)
                  .innerJoin(newsSourceMetaMap.newsSourceInfo, newsSourceInfo)
                  .innerJoin(newsSourceInfo.newsInfoList, newsInfo)
                  .innerJoin(newsSourceMetaMap.meta, mataCode)
                  .where(newsSourceMetaMap.meta.in( 
                          // subQuery
                          JPAExpressions
                          .select(mataCode)
                          .from(mataCode)
                          .where(mataCode.metaCd.in(metaCodeList))
                  ).and(newsInfo.isUse.eq(1))
                   .and(newsInfo.pubDt.goe(now))
                  ) 
                  .orderBy(newsInfo.pubDt.desc()) 
                  .transform(GroupBy.groupBy(newsInfo.newsId)
                          .as(new QNewsMetaTagDTO(
                                  newsInfo.newsId,
                                  newsSourceInfo.newsSourceId,
                                  newsInfo.title,
                                  newsInfo.description,
                                  newsInfo.link,
                                  newsInfo.pubDt,
                                  list(Projections.constructor(MetaCdDTO.class, mataCode.metaCd, mataCode.name)),
                                  list(mataCode.name)
                                  
                           ))); 
             
             return map.values().stream().collect(Collectors.toList());
             
        }catch (Exception e) {
           log.error(e.getMessage(), e);
        }
      
        return null;
     
                
    }
}
