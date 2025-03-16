package com.ssolitim.child_tracking_system.user.dto;


import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserLoginRequest(
    @Schema(description = "아이디", example = "ssolitim", requiredMode = REQUIRED)
    @NotBlank(message = "아이디를 입력해주세요.")
    String username,

    @Schema(description = "비밀번호", example = "ssolitim0925", requiredMode = REQUIRED)
    @NotBlank(message = "비밀번호를 입력해주세요.")
    String password
) {

}
