package com.zzami.alarm.api.service;

import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.zzami.alarm.api.dto.user.UserInfoDto;
import com.zzami.alarm.api.entity.Role;
import com.zzami.alarm.api.entity.User;
import com.zzami.alarm.api.entity.UserRole;
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
        
        User user = new User();
        user.setUserId(userInfo.getUserId());
        user.setEncrytedPassword(passwordEncoder.encode(userInfo.getPasswd()));
        user.setUsername(userInfo.getUserNm());
        
        userRepository.save(user);
        
        Role role = roleRepository.findByRoleName("USER");
        
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        
        userRoleRepository.save(userRole);
    }
    
    @Transactional
    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    
}