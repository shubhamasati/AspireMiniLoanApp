package com.aspire.loan.service.impl;

import com.aspire.loan.enums.CommonError;
import com.aspire.loan.enums.UserRole;
import com.aspire.loan.exceptions.ExceptionUtil;
import com.aspire.loan.model.entity.UserEntity;
import com.aspire.loan.repository.UserEntityRepository;
import com.aspire.loan.service.UserService;
import com.aspire.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    public User registerUser(User user) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            log.warn("Username/Password can not be null/empty");
            ExceptionUtil.throwBadRequestException(user.getUsername(), "username/password",
                    CommonError.INVALID_REQUEST.getName(), null, CommonError.INVALID_REQUEST.getMessage());
        }

        String role = user.getRole();
        if (org.apache.commons.lang3.StringUtils.isEmpty(role) || !EnumUtils.isValidEnum(UserRole.class, role)) {
            log.warn("Invalid Role selected");
            ExceptionUtil.throwBadRequestException(user.getUsername(), "username/password",
                    CommonError.INVALID_REQUEST.getName(), null, CommonError.INVALID_REQUEST.getMessage());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(EnumUtils.getEnum(UserRole.class, user.getRole()));
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmailId(user.getEmailId());
        userEntity.setMobileNumber(user.getMobile());
        userEntity.setActive(true);
        UserEntity savedUser = userEntityRepository.save(userEntity);
        user.setId(savedUser.getId());
        return user;
    }

    @Override
    public UserEntity loadUserByUsername(String username) {
        Optional<UserEntity> userEntity = userEntityRepository.findByUserName(username);
        if (userEntity.isPresent()) {
            return userEntity.get();
        } else {
            ExceptionUtil.throwBadRequestException(
                    null, username, CommonError.RESOURCE_NOT_FOUND.getName(), null,
                    CommonError.RESOURCE_NOT_FOUND.getMessage());
        }
        return null;
    }
}
