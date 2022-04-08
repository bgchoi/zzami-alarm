package com.zzami.alarm.api.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class WeatherInfoDTO {
    @ApiModelProperty(value = "주소코드(1000000000)", required =  true)
    private String addCd;
    
    @ApiModelProperty(value = "날씨아이디", required = false)
    private Long weatherId;
    
    @ApiModelProperty(value = "기온", required = true)
    private Float templature;
    
    @ApiModelProperty(value = "기온(최저)", required = true)
    private Float templatureMin;
    
    @ApiModelProperty(value = "기온(최고)", required = true)
    private Float templatureMax;
    
    @ApiModelProperty(value = "날씨 팁메시지", required = true)
    private String tipMessage;
    
    @ApiModelProperty(value = "날씨 상태(1,2,3)", required = true )
    private int status;
    
    @ApiModelProperty(value = "예보시간(yyyy-MM-dd HH:mm:ss)", required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fcastDt;
    
    @ApiModelProperty(value = "등록일(yyyy-MM-dd HH:mm:ss)", required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDt;
    
    public WeatherInfoDTO() {     
    }
    
    public WeatherInfoDTO(String addCd, Float templature) {
        this.addCd = addCd;
        this.templature = templature;
    }
}
