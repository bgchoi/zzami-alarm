package com.zzami.alarm.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zzami.alarm.api.dto.user.UserInfoDto;
import com.zzami.alarm.api.entity.SysUser;
import com.zzami.alarm.api.security.JwtTokenProvider;
import com.zzami.alarm.api.service.UserService;
import com.zzami.alarm.core.dto.result.ApiResponseBody;
import com.zzami.alarm.core.dto.result.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/o")
@Api(tags = "회원관리")
public class AuthController {
 
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserService userService;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    ModelMapper modelMapper;

    
    @ApiOperation(value = "로그인")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", required = true,  paramType = "form", value = "유저아이디"),
        @ApiImplicitParam(name = "password", required = true, paramType = "form", value = "패스워드"), 
    })
    @PostMapping(value="/signup", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<ApiResponseBody<Void>> signUp(
            @RequestParam(value="userId", required = true) String userId,
            @RequestParam(value="password", required = true) String password) {
        
        ApiResponseBody<Void> responseBody = new ApiResponseBody<>();
        responseBody.setResponse(ResultStatus.OK);

        try {
            
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, password));
            log.info("인증 결과: " + authenticate.getPrincipal());
            
            SysUser user = userService.getUser(authenticate.getName());
            
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.setUsn(user.getUsn());
            userInfoDto.setUserId(user.getUserId());
            userInfoDto.setUserNm(user.getUsername());
            userInfoDto.setPasswordUpdateDt(user.getPasswordUpdateDt());
            userInfoDto.setAuthorities(authenticate.getAuthorities());
            
            String token = jwtTokenProvider.createToken(userInfoDto);
            log.info("token =" +  token);
            
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }

        return ResponseEntity.ok().body(responseBody);

    }
    
    @ApiOperation(value = "회원가입")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", required = true,  paramType = "form", value = "유저아이디(email)"),
        @ApiImplicitParam(name = "password", required = true, paramType = "form", value = "패스워드"),
        @ApiImplicitParam(name = "username", required = false, paramType = "form", value = "이름"),
    })
    @PostMapping(value="/signin", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<ApiResponseBody<Void>> signIn(
            @RequestParam(value="userId", required = true) String userId, 
            @RequestParam(value="password", required = true) String password,
            @RequestParam(value="username", required = true) String username) {
        
        ApiResponseBody<Void> responseBody = new ApiResponseBody<>();
        responseBody.setResponse(ResultStatus.OK);

        try {
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.setUserId(userId); // 이메일 
            userInfoDto.setPasswd(password); // 패스워드 
            userInfoDto.setUserNm(username); // 이름 
            userService.saveUser(userInfoDto);
            
        } catch (Exception ex) {
            throw ex;
        }

        return ResponseEntity.ok().body(responseBody);

    }
    
   

}
