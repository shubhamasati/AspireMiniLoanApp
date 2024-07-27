package com.aspire.loan.service;

import com.aspire.loan.model.entity.UserEntity;
import com.aspire.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserEntity loadUserByUsername(String username);
    public User registerUser(User user);
}
