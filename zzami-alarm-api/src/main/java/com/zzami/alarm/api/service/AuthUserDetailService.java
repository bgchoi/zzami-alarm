package com.zzami.alarm.api.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.zzami.alarm.api.dto.user.UserInfoDto;
import com.zzami.alarm.api.entity.user.SysUser;
import com.zzami.alarm.api.entity.user.SysUserRole;
import com.zzami.alarm.api.repository.RoleRepository;
import com.zzami.alarm.api.repository.UserRepository;
import com.zzami.alarm.api.repository.UserRoleRepository;
import com.zzami.alarm.api.security.AuthUserDetails;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UserRoleRepository userRoleRepository;
    
    @Autowired
    ModelMapper modelMapper;
      
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        
        SysUser user = userRepository.findByUserId(userId);
        if(user == null) {
            throw new UsernameNotFoundException("User " + userId + " was not found");
        }
        
        List<SysUserRole> userRoleList = userRoleRepository.getBySysUser(user);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if(userRoleList != null) {
            for (SysUserRole role : userRoleList) { 
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getSysRole().getRoleName());
                grantList.add(authority);
            }
        }
        
        log.info("grantList =" +  grantList);

        
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setPasswd(user.getEncrytedPassword());
        userInfoDto.setUserNm(user.getUsername());
        userInfoDto.setEnabled(user.isEnabled());
        userInfoDto.setAuthorities(grantList);
        userInfoDto.setUsn(user.getUsn());
        userInfoDto.setPasswordUpdateDt(user.getPasswordUpdateDt());
        
        UserDetails userDetails = new AuthUserDetails(userInfoDto);

         
        return userDetails; 
        
    }
    
}
