package com.zzami.alarm.api.dto.user;

import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"passwd"})
public class UserInfoDto  {
 
    private String userId;
    @JsonIgnore
    private String passwd;
    private long usn;
    private String userNm;
    private Date passwordUpdateDt;
    private boolean isEnabled;
    private Collection<? extends GrantedAuthority> authorities;
    
}
