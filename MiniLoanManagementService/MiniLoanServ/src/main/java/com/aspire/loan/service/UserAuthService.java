package com.aspire.loan.service;

import com.aspire.model.LoginRequest;
import com.aspire.model.LoginResponse;
import com.aspire.model.User;

public interface UserAuthService {
    LoginResponse loginUser(LoginRequest logInRequest);
}
