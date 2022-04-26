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
import com.zzami.alarm.api.entity.User;
import com.zzami.alarm.api.entity.UserRole;
import com.zzami.alarm.api.repository.UserRepository;
import com.zzami.alarm.api.repository.UserRoleRepository;
import com.zzami.alarm.api.security.AuthUserDetails;

@Service
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserRoleRepository userRoleRepository;
    
    @Autowired
    ModelMapper modelMapper;
     

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findFirstByUsernameOrderByUsnAsc(username);
        if(user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found");
        }
        
        List<UserRole> userRoleList = userRoleRepository.getByUser(user);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if(userRoleList != null) {
            for (UserRole role : userRoleList) { 
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole().getRoleName());
                grantList.add(authority);
            }
        }

        
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setPasswd(user.getEncrytedPassword());
        userInfoDto.setUserNm(user.getUsername());
        userInfoDto.setEnabled(user.isEnabled());
        userInfoDto.setAuthorities(grantList);
        userInfoDto.setUsn(user.getUsn());
        userInfoDto.setPasswordUpdateDt(user.getPasswordUpdateDt());
        
System.err.println("userInfoDto=" +  userInfoDto);
        
        UserDetails userDetails = new AuthUserDetails(userInfoDto);

         
        return userDetails;
         
        
        
        
    }

}
