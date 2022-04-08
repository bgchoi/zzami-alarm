package com.zzami.alarm.core.dto.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseBody<T> extends ApiResponse {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T result;
  
  public ApiResponseBody() {
    super(ResultStatus.OK);
  }
  
  public ApiResponseBody(ResultStatus status) {
    super(status);
  }

}
