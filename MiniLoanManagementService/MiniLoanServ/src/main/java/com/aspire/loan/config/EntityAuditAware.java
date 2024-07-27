package com.aspire.loan.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class EntityAuditAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String loggedName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (StringUtils.isEmpty(loggedName) || loggedName.equals("anonymousUser")) {
            loggedName = "System";
        }
        return Optional.of(loggedName);
    }

}
