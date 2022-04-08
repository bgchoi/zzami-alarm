package com.zzami.alarm.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewsMetaTagDTO extends NewsInfoDTO {
 
    List<String> metaTagStrList = new ArrayList<>();
    List<MetaCdDTO> metaTagInfoList = new ArrayList<>();
    
    @QueryProjection
    public NewsMetaTagDTO(Long newsId, Long newsSourceId, String title, 
            String description, String link, Date pubDt
            , List<String> metaTagList
            ) { 
        this.newsId = newsId;
        this.newsSourceId = newsSourceId;
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDt = pubDt;
        this.metaTagStrList = metaTagList;
        
    }
    
    @QueryProjection
    public NewsMetaTagDTO(Long newsId, Long newsSourceId, String title, 
            String description, String link, Date pubDt
            , List<MetaCdDTO> metaTagInfoList
            , List<String> metaTagStrList
            ) { 
        this.newsId = newsId;
        this.newsSourceId = newsSourceId;
        this.title = title;
        this.description = description;
        this.link = link; 
        this.pubDt = pubDt;
        this.metaTagInfoList = metaTagInfoList;
        this.metaTagStrList = metaTagStrList;
        
    }
    
    
   
  
}
