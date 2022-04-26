package com.zzami.alarm.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    
    @ApiOperation(value = "로그인")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", required = true,  paramType = "form", value = "유저아이디"),
        @ApiImplicitParam(name = "password", required = true, paramType = "form", value = "패스워드"), 
    })
    @PostMapping(value="/signin", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<ApiResponseBody<Void>> saveAddr(
            @RequestParam(value="username", required = true) String username,
            @RequestParam(value="password", required = true) String password) {
        
        ApiResponseBody<Void> responseBody = new ApiResponseBody<>();
        responseBody.setResponse(ResultStatus.OK);

        try {
            
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.info("인증 결과: " + authenticate.getPrincipal());
            
        } catch (Exception ex) {
            throw ex;
        }

        return ResponseEntity.ok().body(responseBody);

    }
    
   

}
