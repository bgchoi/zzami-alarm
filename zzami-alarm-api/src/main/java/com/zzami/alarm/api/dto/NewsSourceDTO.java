package com.zzami.alarm.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewsSourceDTO {

    @ApiModelProperty(value = "뉴스아이디")
    private Long newsSourceId;
    
    @ApiModelProperty(value = "뉴스이름")
    private String newsSourceNm;
    
    @ApiModelProperty(value = "뉴스URL")
    private String newsSourceUrl;
    
    @ApiModelProperty(value = "사용여부", hidden = true)
    private int isUse = 1;
    
    @ApiModelProperty(value = "카테고리코드")
    private String categoryCd; 
    
}
