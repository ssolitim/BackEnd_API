package com.ssolitim.child_tracking_system.api.dto.child;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssolitim.child_tracking_system.api.model.Child;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChildResponse (
    @Schema(description = "아동 고유 번호", example = "1", requiredMode = REQUIRED)
    Integer id,

    @Schema(description = "이름", example = "김철수", requiredMode = REQUIRED)
    String name,

    @Schema(description = "나이", example = "5", requiredMode = NOT_REQUIRED)
    int age,

    @Schema(description = "성별(남:true/여:false)", example = "false", requiredMode = NOT_REQUIRED)
    boolean gender,

    @Schema(description = "보호자 연락처", example = "010-4222-8888", requiredMode = NOT_REQUIRED)
    String phone,

    @Schema(description = "아동 특이사항", example = "점심 후 감기약 챙기기", requiredMode = NOT_REQUIRED)
    String memo,

    @Schema(description = "어린이집 고유 번호", example = "2", requiredMode = REQUIRED)
    Integer daycareRoomId
) {
    public static ChildResponse from(Child child) {
        return new ChildResponse(
            child.getId(),
            child.getName(),
            child.getAge(),
            child.isGender(),
            child.getPhone(),
            child.getMemo(),
            child.getDaycareRoom().getId()
        );
    }
}
