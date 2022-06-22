package com.zzami.alarm.api.service;

import java.util.Date;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.zzami.alarm.api.dto.user.UserInfoDto;
import com.zzami.alarm.api.entity.user.SysRole;
import com.zzami.alarm.api.entity.user.SysUser;
import com.zzami.alarm.api.entity.user.SysUserRole;
import com.zzami.alarm.api.repository.RoleRepository;
import com.zzami.alarm.api.repository.UserRepository;
import com.zzami.alarm.api.repository.UserRoleRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UserRoleRepository userRoleRepository;
    
    @Autowired
    ModelMapper modelMapper;
      
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Transactional
    public void saveUser(UserInfoDto userInfo) {
        
        SysUser user = new SysUser();
        user.setUserId(userInfo.getUserId());
        user.setEncrytedPassword(passwordEncoder.encode(userInfo.getPasswd()));
        user.setUsername(userInfo.getUserNm());
        user.setCreateDt(new Date());
        user.setEnabled(true);
        
        userRepository.save(user);
        
        SysRole role = roleRepository.findByRoleName("USER");
        
        SysUserRole userRole = new SysUserRole();
        userRole.setSysUser(user);
        userRole.setSysRole(role);
        
        userRoleRepository.save(userRole);
    }
    
    @Transactional
    public SysUser getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    
}