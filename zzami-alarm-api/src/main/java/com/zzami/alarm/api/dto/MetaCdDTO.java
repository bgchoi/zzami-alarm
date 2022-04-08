package com.zzami.alarm.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MetaCdDTO {
 
    @ApiModelProperty(value = "메타코드")
    private String metaCd;
    
    @ApiModelProperty(value="메타코드이름")
    private String name; 
    
    @QueryProjection
    public MetaCdDTO(String metaCd, String name) {
        this.metaCd = metaCd;
        this.name = name;
    }
}
