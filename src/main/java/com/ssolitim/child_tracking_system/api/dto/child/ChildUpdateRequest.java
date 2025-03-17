package com.ssolitim.child_tracking_system.api.dto.child;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChildUpdateRequest (
    @Schema(description = "이름", example = "김철수", requiredMode = REQUIRED)
    @NotBlank(message = "아동 이름을 입력해주세요.")
    String name,

    @Schema(description = "비밀번호", example = "ssolitim0925", requiredMode = NOT_REQUIRED)
    int age,

    @Schema(description = "성별(남:true/여:false)", example = "true", requiredMode = NOT_REQUIRED)
    boolean gender,

    @Schema(description = "보호자 연락처", example = "010-4222-8888", requiredMode = NOT_REQUIRED)
    String phone,

    @Schema(description = "아동 특이사항", example = "점심 후 감기약 챙기기", requiredMode = NOT_REQUIRED)
    String memo,

    @Schema(description = "어린이집 반 고유 번호", example = "2", requiredMode = REQUIRED)
    @NotNull(message = "어린이집 반 고유 번호를 입력해주세요.")
    Integer daycareRoomId
) {
}
