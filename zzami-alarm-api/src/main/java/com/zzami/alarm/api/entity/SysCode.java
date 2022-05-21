package com.zzami.alarm.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="sys_code")
public class SysCode {
    
    @Id
    @Column(name="code", length = 6, columnDefinition = "char(6)")
    private String code;
    
    @Column(name="name", length = 100)
    private String name;

    @Column(name="p_code", length = 6, columnDefinition = "char(6)")
    private String pCode;
    
    @Column(name="is_use")
    private int isUse;
    
}
