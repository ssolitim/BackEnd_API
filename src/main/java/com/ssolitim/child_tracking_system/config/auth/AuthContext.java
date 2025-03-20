package com.ssolitim.child_tracking_system.config.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class AuthContext {

    private Integer userId;

    public Integer getUserId() {
        if (userId == null) {
            throw new IllegalArgumentException("올바르지 않은 인증 정보입니다. userId : null");
        }
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

