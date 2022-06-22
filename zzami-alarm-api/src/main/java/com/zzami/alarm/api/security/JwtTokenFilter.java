package com.zzami.alarm.api.security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzami.alarm.api.dto.user.UserInfoDto;
import com.zzami.alarm.core.dto.result.ResultMap;
import com.zzami.alarm.core.dto.result.ResultStatus;
import com.zzami.alarm.core.exception.ZmAuthEntryPointException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private String profiles;

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.profiles = jwtTokenProvider.profiles;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 토큰을 파싱해서 인증정보를 생성하고 SecurityContextHolder에 추가한다.
        
        String token = jwtTokenProvider.resolveToken(request);
        log.info("token=" +  token);
        try {
            if(token != null && jwtTokenProvider.validateToken(token)) {
                log.info("token validate ..");
                Claims claims = jwtTokenProvider.getClaimsFromToken(token);
                String userId = claims.getSubject();
                int usn = claims.get("usn", Integer.class);
                String userNm = claims.get("userNm", String.class);
                String tokenProfiles = claims.get("profiles", String.class);
                Date passwdUpdateDt = claims.get("passwdUpdateDt", Date.class);
                List<HashMap<String, String>> roles = claims.get("role", List.class);
                
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                if(roles != null) {
                    for (HashMap<String, String> role : roles) { 
                        GrantedAuthority authority = new SimpleGrantedAuthority(role.get("authority"));
                        authorities.add(authority);
                    }
                }
                
                log.info("authorities=" +  authorities);
                
                if(!profiles.equals(tokenProfiles)) {
                    throw new ZmAuthEntryPointException("profile이 일치하지 않습니다. \n다시 로그인 바랍니다.");
                }
                 
                UserInfoDto userInfo = new UserInfoDto();
                userInfo.setUserId(userId);
                userInfo.setUsn(usn);
                userInfo.setUserNm(userNm);
                userInfo.setPasswordUpdateDt(passwdUpdateDt);
                userInfo.setAuthorities(authorities);
                AuthUserDetails userDetails  = new AuthUserDetails(userInfo);
                
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userInfo, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                request.setAttribute("roles", authorities);
                  
            }else {
                request.setAttribute("roles", null);
            }
        }catch(ZmAuthEntryPointException ex) {
            log.error(ex.getMessage(), ex);
            SecurityContextHolder.clearContext(); 
            ObjectMapper om = new ObjectMapper();
            // @formatter:off
            ResultMap resultMap = ResultMap.builder()
                    .code(ResultStatus.UNAUTHORIZED.code())
                    .message(ResultStatus.UNAUTHORIZED.message())
                    .build();
            // @formatter:on 
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "application/json");
            response.getWriter().write(om.writeValueAsString(resultMap));
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }catch(Exception ex) {
            log.error(ex.getMessage(),ex);
        }

        filterChain.doFilter(request, response);

    }

}
