package com.zzami.alarm.api.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzami.alarm.core.dto.result.ResultMap;
import com.zzami.alarm.core.dto.result.ResultStatus;
import lombok.extern.slf4j.Slf4j;



/**
 * @packageName : com.zzami.alarm.api.security
 * @fileName : JwtAccessDeniedHandler.java
 * @author : bong
 * @date : 2022.03.30
 * @description : 인증은 성공 했지만 인가에 실패한 경우 처리
 */
@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

  @Autowired
  ObjectMapper om;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {


    log.error("AccessDenied ...");

    ResultMap resultMap = ResultMap.builder()
          .code(ResultStatus.FORBIDDEN.code())
          .message(ResultStatus.FORBIDDEN.message())
          .build();

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setCharacterEncoding("UTF-8");
    response.setHeader("content-type", "application/json");
    response.getWriter().write(om.writeValueAsString(resultMap));
    response.getWriter().flush();
    response.getWriter().close();



  }

}
