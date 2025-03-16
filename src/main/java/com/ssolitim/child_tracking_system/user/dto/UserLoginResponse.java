package com.ssolitim.child_tracking_system.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserLoginResponse (
    @JsonProperty("access_token")
    String accessToken
) {
    public static UserLoginResponse of(String accessToken) {
        return new UserLoginResponse(accessToken);
    }
}
