package com.aspire.loan.service.impl;

import com.aspire.loan.enums.CommonError;
import com.aspire.loan.exceptions.ExceptionUtil;
import com.aspire.loan.model.entity.UserEntity;
import com.aspire.loan.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> userEntity = userEntityRepository.findByUserName(username);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));
        } else {
            ExceptionUtil.throwBadRequestException(
                    null, username, CommonError.RESOURCE_NOT_FOUND.getName(), null,
                    CommonError.RESOURCE_NOT_FOUND.getMessage());
        }
        return null;
    }
}

