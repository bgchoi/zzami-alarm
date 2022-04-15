package com.zzami.alarm.api.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsInfoDTO {

    @ApiModelProperty(value="뉴스아이디")
    protected Long newsId;
    
    @ApiModelProperty(value="뉴스소스아이디", required = true)
    protected Long newsSourceId;
    
    @ApiModelProperty(value="뉴스제목", required = true)
    protected String title;
    
    @ApiModelProperty(value="뉴스내용", required = true)
    protected String description;
    
    @ApiModelProperty(value="뉴스LINK", required = true)
    protected String link;
    
    @ApiModelProperty(value="사용여부", hidden = true)
    protected int isUse;
    
    @ApiModelProperty(value="발행일(yyyy-MM-dd HH:mm:ss)", required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date pubDt;
    
    @JsonIgnore
    @ApiModelProperty(value="등록일(yyyy-MM-dd HH:mm:ss)", required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date createDt; 
    
}
