package com.zzami.alarm.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity(name="news_info")
@NoArgsConstructor
public class NewsInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="news_id")
    private Long newsId;
    
    @ToString.Exclude
    @JoinColumn(name="news_source_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = NewsSourceInfo.class)
    @NotFound(action = NotFoundAction.IGNORE)
    private NewsSourceInfo newsSourceInfo;
    
    @Column(name="title", nullable = false, columnDefinition = "text")
    private String title;
    
    @Column(name="description", nullable = false, columnDefinition = "text")
    private String description;
    
    @Column(name="link", length = 200)
    private String link;
    
    @Column(name="is_use")
    private int isUse = 1;
    
    @Column(name="create_dt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDt;
    
    @Column(name="pub_dt", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pubDt;
    
}
