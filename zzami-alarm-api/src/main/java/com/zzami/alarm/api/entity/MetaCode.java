package com.zzami.alarm.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name="meta_code")
public class MetaCode {

    @Id
    @Column(name="meta_cd", length = 6, columnDefinition = "char(6)")
    private String metaCd;
    
    @Column(name="name", length = 100 )
    private String name;
    
    @Column(name="p_meta_cd", length=6, columnDefinition = "char(6)")
    private String pMetaCd;
    
    @Column(name="is_use")
    private int isUse;
    
    @Column(name="create_dt")
    private Date createDt;
    
    
}
