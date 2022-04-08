package com.zzami.alarm.api.dto;

import java.util.List;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
public class ReportDTO {

    @ApiModelProperty(value = "주소정보")
    private AddressInfoDTO address;

    @ApiModelProperty(value = "날씨정보")
    private WeatherInfoDTO weather;

    @ApiModelProperty(value = "미세먼지정보")
    private DustInfoDTO dust;

    @ApiModelProperty(value = "뉴스리스트")
    List<NewsMetaTagDTO> newsList;
  
}
