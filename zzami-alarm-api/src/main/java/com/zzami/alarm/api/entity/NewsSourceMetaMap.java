package com.zzami.alarm.api.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity(name="news_source_meta_map")
public class NewsSourceMetaMap {
    
    @EmbeddedId
    private Pk pk;
    
    @MapsId("metaCd")
    @ManyToOne
    @JoinColumn(name="meta_cd")
    @ToString.Exclude
    @NotFound(action = NotFoundAction.IGNORE)
    private MetaCode meta;
    
    @MapsId("newsSourceId")
    @ManyToOne
    @JoinColumn(name="news_source_id")
    @ToString.Exclude
    @NotFound(action = NotFoundAction.IGNORE)
    private NewsSourceInfo newsSourceInfo; 
    
    @Embeddable
    public static class Pk implements Serializable {
        private static final long serialVersionUID = 1L;

        @Column(name="meta_cd")
        private String metaCd;
        
        @Column(name="news_source_id")
        private Long newsSourceId;
    }

}
