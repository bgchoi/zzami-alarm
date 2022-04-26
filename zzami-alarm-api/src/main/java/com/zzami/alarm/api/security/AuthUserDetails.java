package com.zzami.alarm.api.security;

import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.zzami.alarm.api.dto.user.UserInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthUserDetails implements UserDetails {
    private static final long serialVersionUID = -7617654484786663665L;
     
    private String username; 
    private String password; 
    @Getter private long usn;
    @Getter private Date passwordUpdateDt; 
    private boolean isEnabled;
    private Collection<? extends GrantedAuthority> authorities;
    
    public AuthUserDetails(UserInfoDto userInfo) {
        this.username = userInfo.getUserId();
        this.password = userInfo.getPasswd();
        this.usn = userInfo.getUsn();
        this.passwordUpdateDt = userInfo.getPasswordUpdateDt();
        this.isEnabled = userInfo.isEnabled();
        this.authorities = userInfo.getAuthorities();
    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { 
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
