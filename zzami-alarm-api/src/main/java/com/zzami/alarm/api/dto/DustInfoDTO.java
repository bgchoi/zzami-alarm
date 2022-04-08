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
@AllArgsConstructor
@NoArgsConstructor
public class DustInfoDTO {

    //@ApiModelProperty(value = "주소아이디", hidden=true)
    //private Long addId;
    
    @ApiModelProperty(value = "주소코드")
    private String addCd;
    
    @JsonIgnore
    @ApiModelProperty(value = "주소명", hidden=true)
    private String addNm; 
    
    @ApiModelProperty(value = "관측소명")
    private String stationNm;
    
    @ApiModelProperty(value = "미세먼지")
    private Float pm10;
    
    @ApiModelProperty(value = "초미세먼지")
    private Float pm25;
    
    @ApiModelProperty(value = "예보시간")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fcastDt;
    
    @ApiModelProperty(value = "등록일")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDt;
    
    
}
