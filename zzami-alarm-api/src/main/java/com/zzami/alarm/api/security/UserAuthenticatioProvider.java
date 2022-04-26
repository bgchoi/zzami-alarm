package com.zzami.alarm.api.security;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.zzami.alarm.api.dto.user.UserInfoDto;
import com.zzami.alarm.api.service.AuthUserDetailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserAuthenticatioProvider implements AuthenticationProvider{

    @Autowired
    AuthUserDetailService userService;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public Authentication authenticate(Authentication authentication) 
            throws AuthenticationException {
        
        String username = (String) authentication.getName();
        String password = (String) authentication.getCredentials();
        
        Collection<? extends GrantedAuthority> authorities;
        UserDetails userDetail = userService.loadUserByUsername(username);
        
        if(userDetail == null) {
            throw new BadCredentialsException("User Not Found");
        }
        
        if(!passwordEncoder.matches(password, userDetail.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        
        authorities = userDetail.getAuthorities();
        
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
