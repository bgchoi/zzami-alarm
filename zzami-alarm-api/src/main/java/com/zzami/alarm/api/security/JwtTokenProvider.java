package com.zzami.alarm.api.security;


import java.util.Base64;
import java.util.Date;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.zzami.alarm.api.dto.user.UserInfoDto;
import com.zzami.alarm.api.service.AuthUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
    private static final Pattern BEARER = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
    
    @Value("${zzami.jwt.token.secret-key}")
    private String secretKey;
    
    @Value("${zzami.jwt.token.expire-second}")
    private long validityInMilliseconds;
    
    @Value("${zzami.jwt.token.refresh-second}")
    private long refreshInMilliseconds;
    
    @Value("${spring.profiles}")
    protected String profiles;
    
    @Autowired
    AuthUserDetailService userDetailService;
    
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    
    /**
     * @author : bong 
     * @date : 2022.04.21 
     * @description : 토큰 생성 
     */
    public String createToken(UserInfoDto userInfo) {
        Claims claims = Jwts.claims().setSubject(userInfo.getUserId());
        claims.put("role", userInfo.getAuthorities());
        claims.put("usn", userInfo.getUsn());
        claims.put("userNm", userInfo.getUserNm());
        claims.put("passwdUpdateDt", userInfo.getPasswordUpdateDt());
        claims.put("profiles", profiles);
        
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        //@formatter:off
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        // @formatter:on
    }
    
    /**
     * @author : bong 
     * @date : 2022.04.21 
     * @description : 토큰의 username정보로 db의 사용자 정보를 조회한다.
     *
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailService.loadUserByUsername(getUserName(token));
        log.info("roles :{} ", userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    
    /**
     * @author : bong 
     * @date : 2022.04.21 
     * @description : token으로부터 username조회 
     */
    public String getUserName(String token) {
        return getClaimsFromToken(token).getSubject();
    }
    
    /**
     * @author : bong 
     * @date : 2022.04.21 
     * @description : http요청 헤더로 부터 토큰을 조회
     *
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        
        if(bearerToken != null && BEARER.matcher(bearerToken) != null) {
            return bearerToken.substring(7);
        }
        
        return null;
    }
    
    /**
     * @author : bong 
     * @date : 2022.04.21 
     * @description : 토큰의 유효성 체크 
     *
     */
    public boolean validateToken(String jwtToken) {
        try {
            return !getClaimsFromToken(jwtToken).getExpiration().before(new Date());
        }catch (SignatureException ex) {
            log.error(ex.getMessage());
        }catch (MalformedJwtException ex) {
            log.error(ex.getMessage());
        }catch(ExpiredJwtException ex) {
            log.error(ex.getMessage());
        }catch(UnsupportedJwtException ex) {
            log.error(ex.getMessage());
        }catch(IllegalArgumentException ex) {
            log.error(ex.getMessage());
        }
        
        return false;
    }
    
    /**
     * @author : bong 
     * @date : 2022.04.21 
     * @description : 토큰으로 부터 만료시간 조회  
     *
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }
    
    /**
     * @author : bong 
     * @date : 2022.04.21 
     * @description : 토큰이 만료되었는지 여부 조회 
     *
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    /**
     * @author : bong 
     * @date : 2022.04.21 
     * @description : 토큰을 갱신해야 하는지 여부 조회
     *
     */
    public Boolean isRefreshToken(String token) {
        long expirationTimeMilliseconds = getExpirationDateFromToken(token).getTime();
        if(expirationTimeMilliseconds > 0) {
            long remainTime = expirationTimeMilliseconds - System.currentTimeMillis();
            return remainTime < refreshInMilliseconds;
        }
        
        return false;
    }
    
    /**
     * @author : bong 
     * @date : 2022.04.21 
     * @description : 토큰의 패스워드와 db의 패스워드 업데이트 시간을 비교하여 비밀번호 변경여부 확인
     *
     */
    public Boolean isTokenPasswdUpdateDt(String token, Date dbPasswordUpdateDt) {
        Date passwdUpdateDt = getClaimsFromToken(token).get("passwdUpdateDt", Date.class);
        log.info(" compare to Token Password Update Date {} : {}", passwdUpdateDt, dbPasswordUpdateDt);
        int compare = passwdUpdateDt.compareTo(passwdUpdateDt);
        if(compare == 0) {
            return true;
        }
        
        return false;
    }
    
    /**
     * @author : bong 
     * @date : 2022.04.21 
     * @description : 토큰으로 부터 JWT Claims를 생성후 조회 
     *
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
    
    
}
