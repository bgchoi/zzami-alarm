package com.zzami.alarm.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressInfoDTO {

    @ApiModelProperty(value = "주소아이디")
    private Long addId;
    @ApiModelProperty(value = "주소코드")
    private String addCd;
    @ApiModelProperty(value = "주소명")
    private String addNm;
    @ApiModelProperty(value = "위도")
    private Float lat;
    @ApiModelProperty(value = "경도")
    private Float lon;
    @ApiModelProperty(value = "nx")
    private Float nx;
    @ApiModelProperty(value = "ny")
    private Float ny;
    @ApiModelProperty(value = "관측소명")
    private String stationNm;
    
}
