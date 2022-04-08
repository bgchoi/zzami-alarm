package com.zzami.alarm.core.dto.result;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ResultMap {

    private int code;
    private String message;
    
    @Builder.Default
    private Map<String, Object> result = new LinkedHashMap<>();
    
    public ResultMap addValue(String key, Object value) {
      this.result.put(key, value);
      return this;
    }
    
    public ResultMap addValueAll(Map<String, Object> map) {
      this.result.putAll(map);
      return this;
    }

}
