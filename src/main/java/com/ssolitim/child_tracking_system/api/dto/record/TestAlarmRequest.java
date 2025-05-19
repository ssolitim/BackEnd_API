package com.ssolitim.child_tracking_system.api.dto.record;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TestAlarmRequest (
    @Schema(description = "디바이스 토큰", example = "ExponentPushToken[CJ-6dbFJNUbf9kaqF55iaG]", requiredMode = REQUIRED)
    @NotBlank(message = "디바이스 토큰을 입력해주세요.")
    String token
){
}
