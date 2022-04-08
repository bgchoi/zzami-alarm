package com.zzami.alarm.api.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzami.alarm.core.dto.result.ResultMap;
import com.zzami.alarm.core.dto.result.ResultStatus;
import lombok.extern.slf4j.Slf4j;

/**
* @packageName : com.zzami.alarm.api.security 
* @fileName : EntryPointUnAuthorizedHandler.java 
* @author : bong 
* @date : 2022.03.31 
* @description : 
*/

@Slf4j
@Component
public class EntryPointUnAuthorizedHandler  implements AuthenticationEntryPoint {

  @Autowired
  ObjectMapper om;
  
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    
    
    log.info("authentication failure ... ");
    
    ResultMap resultMap = ResultMap.builder()
          .code(ResultStatus.UNAUTHORIZED.code())
          .message(ResultStatus.UNAUTHORIZED.message())
          .build();
    
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setCharacterEncoding("UTF-8");
    response.setHeader("content-type", "application/json");
    response.getWriter().write(om.writeValueAsString(resultMap));
    response.getWriter().flush();
    response.getWriter().close();
    
  }

}
