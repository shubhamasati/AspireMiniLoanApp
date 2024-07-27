package com.aspire.loan.service.impl;

import com.aspire.loan.enums.CommonError;
import com.aspire.loan.enums.UserRole;
import com.aspire.loan.exceptions.ExceptionUtil;
import com.aspire.loan.model.entity.UserEntity;
import com.aspire.loan.service.UserAuthService;
import com.aspire.loan.service.UserService;
import com.aspire.loan.utils.JwtTokenUtil;
import com.aspire.model.LoginRequest;
import com.aspire.model.LoginResponse;
import com.aspire.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public LoginResponse loginUser(LoginRequest logInRequest) {
        LoginResponse response = new LoginResponse();
        UserEntity userDetails = userService.loadUserByUsername(logInRequest.getUsername());
        if (userDetails.getPassword().equals(logInRequest.getPassword())) {
            String token = jwtTokenUtil.generateToken(userDetails);
            response.setUsername(userDetails.getUserName());
            response.setToken(token);
            return response;
        }
        ExceptionUtil.throwBadRequestException(null, null, "LOGIN_FAILED", null, "Login failed");
        return null;
    }
}
