package com.zzami.alarm.core.dto.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiResponse {
  @ApiModelProperty(value="응답코드", example = "200")
  private int code;
  
  @ApiModelProperty(value="응답메시지", example = "OK")
  private String message;
  
  public ApiResponse(ResultStatus status) {
    this.code = status.code();
    this.message = status.message();
  }
  
  public void setResponse(ResultStatus status) {
    this.code = status.code();
    this.message = status.message();
  }
  
}
