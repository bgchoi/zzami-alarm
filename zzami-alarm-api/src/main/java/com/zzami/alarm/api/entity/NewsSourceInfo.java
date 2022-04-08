package com.zzami.alarm.api.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity(name="news_source_info")
public class NewsSourceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="news_source_id")
    private Long newsSourceId; 
    
    @Column(name="news_source_name")
    private String newsSourceNm;
    
    
    @Column(name="news_source_url", length = 500)
    private String newsSourceUrl;
    
    @Column(name="is_use")
    private int isUse = 1;
    
    @Column(name="category_cd")
    private String categoryCd;
    
    @Column(name="create_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDt;
    
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "newsSourceInfo", targetEntity = NewsInfo.class)
    private Set<NewsInfo> newsInfoList = new HashSet<>();
    
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy="newsSourceInfo", targetEntity=NewsSourceMetaMap.class)
    private Set<NewsSourceMetaMap> metaMapList = new HashSet<>();
}
