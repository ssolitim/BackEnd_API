package com.ssolitim.child_tracking_system.api.dto.user;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserRegisterRequest (
    @Schema(description = "아이디", example = "ssolitim", requiredMode = REQUIRED)
    @NotBlank(message = "아이디를 입력해주세요.")
    String username,

    @Schema(description = "비밀번호", example = "ssolitim0925", requiredMode = REQUIRED)
    @NotBlank(message = "비밀번호를 입력해주세요.")
    String password,

    @Schema(description = "전화번호", example = "041-421-1111", requiredMode = NOT_REQUIRED)
    String phone,

    @Schema(description = "어린이집 고유 번호", example = "2", requiredMode = NOT_REQUIRED)
    Integer daycareId
) {
}
