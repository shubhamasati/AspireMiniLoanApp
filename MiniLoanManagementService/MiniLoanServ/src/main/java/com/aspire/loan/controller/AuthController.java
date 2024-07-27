package com.aspire.loan.controller;

import com.aspire.api.UserAuthApiApi;
import com.aspire.loan.service.UserAuthService;
import com.aspire.loan.service.UserService;
import com.aspire.model.LoginRequest;
import com.aspire.model.LoginResponse;
import com.aspire.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements UserAuthApiApi {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<LoginResponse> userLogin(LoginRequest request) {
        LoginResponse loginResponse = userAuthService.loginUser(request);
        return ResponseEntity.ok(loginResponse);
    }

    @Override
    public ResponseEntity<User> userSignUp(User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }
}
