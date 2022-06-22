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

    @ApiModelProperty(value = "뉴스리스트")
    List<NewsMetaTagDTO> newsList;
  
}
